package com.clip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clip.entities.CreditLimitEligibilityRequest;
import com.clip.entities.CreditLimitEligibilityResponse;
import com.clip.service.CreditIncreaseEligibilityService;

@RestController
public class CreditIncreaseEligibilityController {

	@Autowired
	CreditIncreaseEligibilityService creditIncreaseEligibilityService;
	
	@RequestMapping(value = "transunion/credit/eligibility", method = RequestMethod.POST)
	public ResponseEntity<CreditLimitEligibilityResponse> getCreditLineEligibilityStatus(@RequestHeader HttpHeaders requestHeaders, @RequestBody CreditLimitEligibilityRequest creditLimitEligibilityRequest){
		
		CreditLimitEligibilityResponse creditLimitEligibilityResponse = null;
		
		if(!isCallerAuthorized(requestHeaders)){
			creditLimitEligibilityResponse = new CreditLimitEligibilityResponse();
			creditLimitEligibilityResponse.setError("Unauthorized Request. Caller is not authorized.");
			return new ResponseEntity<CreditLimitEligibilityResponse>(creditLimitEligibilityResponse, creditIncreaseEligibilityService.generateHttpResponseHeaders(requestHeaders), HttpStatus.UNAUTHORIZED);			
		}
		
		creditLimitEligibilityResponse = creditIncreaseEligibilityService.getCreditLineEligibilityStatus(creditLimitEligibilityRequest.getSsn());
		
		return new ResponseEntity<CreditLimitEligibilityResponse>(creditLimitEligibilityResponse, creditIncreaseEligibilityService.generateHttpResponseHeaders(requestHeaders), HttpStatus.OK);
	}
	
	private boolean isCallerAuthorized(HttpHeaders headers){
			return headers.get(HttpHeaders.AUTHORIZATION).get(0).equals("Bearer PNC-Auth-12345");
	}
	
}
