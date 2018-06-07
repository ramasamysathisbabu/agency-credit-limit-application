package com.clip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	
	@PostMapping(value = "/limits/eligibility")
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
