package com.clip.models;

public class CreditLimitEligibilityResponse {
	private String eligibilityStatus;
	private String newCreditLimitAmount;
	private String error;

	public String getEligibilityStatus() {
		return eligibilityStatus;
	}

	public void setEligibilityStatus(String eligibilityStatus) {
		this.eligibilityStatus = eligibilityStatus;
	}

	public String getNewCreditLimitAmount() {
		return newCreditLimitAmount;
	}

	public void setNewCreditLimitAmount(String newCreditLimitAmount) {
		this.newCreditLimitAmount = newCreditLimitAmount;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "CreditLimitEligibilityResponse [eligibilityStatus=" + eligibilityStatus + ", newCreditLimitAmount="
				+ newCreditLimitAmount + ", error=" + error + "]";
	}
}
