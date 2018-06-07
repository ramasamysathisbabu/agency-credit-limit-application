package com.clip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.clip.models.CreditLimitEligibilityResponse;
import com.clip.repository.Person;
import com.clip.repository.PersonRepository;

@Service
public class CreditIncreaseEligibilityService {

	private final String X_REQUEST_ID = "X-Request-ID";
	
	@Autowired
	PersonRepository personRepository;

	public CreditLimitEligibilityResponse getCreditLineEligibilityStatus(String ssn) {
		Person person = personRepository.getCreditLineEligibilityStatus(ssn);

		CreditLimitEligibilityResponse creditLimitEligibilityResponse = new CreditLimitEligibilityResponse();

		if (person != null) {
			creditLimitEligibilityResponse.setEligibilityStatus(person.getCreditEligibiligyStatus());
			creditLimitEligibilityResponse
					.setNewCreditLimitAmount(person.getCreditEligibiligyStatus().equals("yes") ? "12000" : "0");
		} else {
			creditLimitEligibilityResponse.setEligibilityStatus("no");
			creditLimitEligibilityResponse.setNewCreditLimitAmount("0");
			creditLimitEligibilityResponse.setError("Could not identify the customer");
		}

		return creditLimitEligibilityResponse;
	}
	
	public HttpHeaders generateHttpResponseHeaders(HttpHeaders requestHeaders) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(HttpHeaders.AUTHORIZATION, requestHeaders.get(HttpHeaders.AUTHORIZATION).get(0));
		responseHeaders.set(X_REQUEST_ID, requestHeaders.get(X_REQUEST_ID).get(0));
		return responseHeaders;
	}

}
