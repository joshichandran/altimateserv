package com.altimetrik.mate.endpoint.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.altimetrik.mate.endpoint.CandidateDetails;
import com.altimetrik.mate.model.Candidate;
import com.altimetrik.mate.model.Experience;
import com.altimetrik.mate.model.Link;
import com.altimetrik.mate.model.ScheduleDetail;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TODO: Describe purpose and behavior of CandidateDetailsImpl
 */
@Component
@Scope("request")
public class CandidateDetailsImpl implements CandidateDetails {
	

	public static final String ALTI_API_PREFIX = "/v1/manage/candidate/get?id=";

	private static List<Candidate> listCandidate = new ArrayList<>();

	static {
		Candidate candidate1 = new Candidate();
		candidate1.setId(System.currentTimeMillis());
		candidate1.setName("Naveen");
		candidate1.setEmail("naveen@gmail.com");
		candidate1.setAge(25);
		candidate1.setPhone("9991828929");
		candidate1.setExperience(new Experience(5, 3));
		listCandidate.add(candidate1);

		Candidate candidate2 = new Candidate();
		candidate2.setId(System.currentTimeMillis());
		candidate2.setName("Lakeshman");
		candidate2.setEmail("lak@gmail.com");
		candidate2.setAge(25);
		candidate2.setPhone("9991828929");
		candidate2.setExperience(new Experience(5, 3));
		listCandidate.add(candidate2);

		Candidate candidate3 = new Candidate();
		candidate3.setId(System.currentTimeMillis());
		candidate3.setName("arun");
		candidate3.setEmail("arun@gmail.com");
		candidate3.setAge(25);
		candidate3.setPhone("9991828929");
		candidate3.setExperience(new Experience(5, 3));
		listCandidate.add(candidate3);

		Candidate candidate4 = new Candidate();
		candidate4.setId(System.currentTimeMillis());
		candidate4.setName("joshi");
		candidate4.setEmail("joshi@gmail.com");
		candidate4.setAge(25);
		candidate4.setPhone("9991828929");
		candidate4.setExperience(new Experience(5, 3));
		listCandidate.add(candidate4);

		Candidate candidate5 = new Candidate();
		candidate5.setId(System.currentTimeMillis());
		candidate5.setName("aarthi");
		candidate5.setEmail("aarthi@gmail.com");
		candidate5.setAge(25);
		candidate5.setPhone("9991828929");
		candidate5.setExperience(new Experience(5, 3));
		listCandidate.add(candidate5);

	}

	@Override
	public Response add(Candidate c) {
		c.setId(System.currentTimeMillis());
		listCandidate.add(c);
		Link link = new Link();
		link.setHref(ALTI_API_PREFIX + c.getId());
		Response build = Response.ok(link).build();
		return build;
	}

	@Override
	public Response search(String email, String phone, String id) throws JsonProcessingException {
		for (Candidate candidate : listCandidate) {
			if (email.equals(candidate.getEmail())) {
				return Response.ok(candidate).build();
			}
		}
		return Response.status(404).build();
	}

	@Override
	public Response get(String id) throws JsonProcessingException {
		for (Candidate candidate : listCandidate) {
			if (String.valueOf(candidate.getId()).equals(id)) {
				return Response.ok(candidate).build();
			}
		}
		return Response.status(404).build();
	}

	@Override
	public Response getAll() throws JsonProcessingException {
		//sendCabRequestMail(null);
		List<Candidate> getAllCandidate = new ArrayList<>();
		for (Candidate candidate : listCandidate) {
			getAllCandidate.add(candidate);
		}
		return Response.ok(getAllCandidate).build();
	}
	
	

}
