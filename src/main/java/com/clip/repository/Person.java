package com.clip.repository;

import org.springframework.stereotype.Component;

//@Component
public class Person {

	private String ssn;
	private String creditEligibiligyStatus;
	
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getCreditEligibiligyStatus() {
		return creditEligibiligyStatus;
	}
	public void setCreditEligibiligyStatus(String creditEligibiligyStatus) {
		this.creditEligibiligyStatus = creditEligibiligyStatus;
	}
	
	@Override
	public String toString() {
		return "Person [ssn=" + ssn + ", creditEligibiligyStatus=" + creditEligibiligyStatus + "]";
	}
}
