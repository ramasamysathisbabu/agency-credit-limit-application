package com.clip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clip.models.CreditLimitEligibilityRequest;
import com.clip.models.CreditLimitEligibilityResponse;
import com.clip.service.CreditIncreaseEligibilityService;

@RestController
@RequestMapping("/creditcard")
public class CreditIncreaseEligibilityController {

	@Autowired
	CreditIncreaseEligibilityService creditIncreaseEligibilityService;

	@ExceptionHandler(Exception.class)
	@PostMapping(value = "/limits/eligibility")
	public ResponseEntity<CreditLimitEligibilityResponse> getCreditLineEligibilityStatus(
			@RequestHeader HttpHeaders requestHeaders, 
			@RequestBody CreditLimitEligibilityRequest creditLimitEligibilityRequest) throws Exception {

		CreditLimitEligibilityResponse creditLimitEligibilityResponse = creditIncreaseEligibilityService
				.getCreditLineEligibilityStatus(creditLimitEligibilityRequest.getSsn());
		
		return new ResponseEntity<CreditLimitEligibilityResponse>(creditLimitEligibilityResponse,
				creditIncreaseEligibilityService.generateHttpResponseHeaders(requestHeaders), HttpStatus.OK);
	}
}
