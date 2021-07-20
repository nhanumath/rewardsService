package com.charter.rewardsService.vo;

import java.util.Date;

public class CustomerTransaction {

	
	private Long transactionId;
	private Long customerId;
	private Date transactionDate;
	private Double amountSpent;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Double getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(Double amountSpent) {
		this.amountSpent = amountSpent;
	}
	
	
	
	
}
