package com.clip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clip.entities.CreditLimitEligibilityResponse;
import com.clip.repository.Person;
import com.clip.repository.PersonRepository;

@Service
public class CreditIncreaseEligibilityService {

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

}
