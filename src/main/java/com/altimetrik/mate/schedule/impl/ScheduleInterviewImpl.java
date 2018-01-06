package com.altimetrik.mate.schedule.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.altimetrik.mate.model.Candidate;
import com.altimetrik.mate.model.Link;
import com.altimetrik.mate.model.ScheduleDetail;
import com.altimetrik.mate.schedule.ScheduleInterview;

@Component
@Scope("request")
public class ScheduleInterviewImpl implements ScheduleInterview {

	List<ScheduleDetail> scheduleDetailList = new ArrayList<>();

	@Autowired
	private JavaMailSender javaMailService;

	public static final String ALTI_API_PREFIX = "/v1/manage/schedule/get?id=";

	@Override
	public Response schedule(ScheduleDetail c) {
		scheduleDetailList.add(c);
		sendInterviewNotification(c);
		Link link = new Link();
		link.setHref(ALTI_API_PREFIX + c.getCandidateId());
		Response build = Response.ok(link).build();
		return build;
	}

	public void sendInterviewNotification(ScheduleDetail request) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setCc(new String[] { request.getHrId() != null ? request.getHrId() : "test-hr@altimtrik.com",
				request.getInterviewerId() != null ? request.getInterviewerId() : "test-interviewer@altimetrik.com" });
		mailMessage.setFrom("Interview-Schdule@Altimetrik.com");
		mailMessage.setTo(request.getCandidateId()!=null?request.getCandidateId():"test-canditate@altimetrik.com");
		mailMessage.setSubject("Interview Scedule Notification -");

		StringBuilder message = new StringBuilder();
		message.append("Hi").append("\n\n");
		message.append("This is confirm about your " + request.getType() + " interview schedule on " + request.getDate()
				+ "\n\n");
		message.append("Thanks & Regards\nAltimetrik HR Team");
		mailMessage.setText(message.toString());

		System.out.println(mailMessage.toString());
		javaMailService.send(mailMessage);
	}

}
