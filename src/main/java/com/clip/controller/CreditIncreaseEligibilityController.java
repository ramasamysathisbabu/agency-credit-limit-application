package com.clip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.clip.entities.CreditLimitEligibilityResponse;
import com.clip.entities.CreditLimitEligibilityRequest;
import com.clip.service.CreditIncreaseEligibilityService;

@RestController
public class CreditIncreaseEligibilityController {

	@Autowired
	CreditIncreaseEligibilityService creditIncreaseEligibilityService;
	
//	@RequestMapping(value = "transunion/credit/eligibility", method = RequestMethod.POST)
//	public CreditLimitEligibilityResponse getCreditLineEligibilityStatus(@RequestHeader(value=HttpHeaders.AUTHORIZATION) String bearerToken, @RequestBody CreditLimitEligibilityRequest creditLimitEligibilityRequest){
//		CreditLimitEligibilityResponse creditLimitEligibilityResponse = null;
//		if (!"Bearer PNC-Auth-12345".equals(bearerToken)){
//			creditLimitEligibilityResponse = new CreditLimitEligibilityResponse();
//			creditLimitEligibilityResponse.setError("Unauthorized Request");
//			return creditLimitEligibilityResponse;
//		}
//		creditLimitEligibilityResponse = creditIncreaseEligibilityService.getCreditLineEligibilityStatus(creditLimitEligibilityRequest.getSsn());
//		System.out.println("matchingSsn:" + creditLimitEligibilityResponse);
//		return creditLimitEligibilityResponse;
//	}

	@RequestMapping(value = "transunion/credit/eligibility", method = RequestMethod.POST)
	public ResponseEntity<CreditLimitEligibilityResponse> getCreditLineEligibilityStatus(@RequestHeader(value=HttpHeaders.AUTHORIZATION) String bearerToken, 
			@RequestHeader(value="X-Request-ID") String xRequestId, @RequestBody CreditLimitEligibilityRequest creditLimitEligibilityRequest){
		CreditLimitEligibilityResponse creditLimitEligibilityResponse = null;
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(HttpHeaders.AUTHORIZATION, bearerToken);
		responseHeaders.set("X-Request-ID", xRequestId);
		System.out.println("\nAgent Controller - X-Request-ID value:" + xRequestId);
		
		if (!"Bearer PNC-Auth-12345".equals(bearerToken)){
			creditLimitEligibilityResponse = new CreditLimitEligibilityResponse();
			creditLimitEligibilityResponse.setError("Unauthorized Request");
			return new ResponseEntity<CreditLimitEligibilityResponse>(creditLimitEligibilityResponse, responseHeaders, HttpStatus.UNAUTHORIZED);
		}
		creditLimitEligibilityResponse = creditIncreaseEligibilityService.getCreditLineEligibilityStatus(creditLimitEligibilityRequest.getSsn());
		System.out.println("\nAgent Controller - Service Response:" + creditLimitEligibilityResponse);
		
		return new ResponseEntity<CreditLimitEligibilityResponse>(creditLimitEligibilityResponse, responseHeaders, HttpStatus.OK);
		//return creditLimitEligibilityResponse;
	}
}