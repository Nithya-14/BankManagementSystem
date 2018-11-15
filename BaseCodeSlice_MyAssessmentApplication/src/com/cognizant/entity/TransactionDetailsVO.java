package com.cognizant.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.NotEmpty;





@Entity
@Table(name="TRANSACTION_DETAILS")
public class TransactionDetailsVO {

	@Id
	//@Column(name = "TRANSACTION_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	
	@Column(name = "TRANSACTION_DESCRIPTION", nullable = false)
	private String transactionDescription;
	
	
	@Column(name = "TRANSACTION_TYPE", nullable = false)
	private String transactionType;
	
	
	
		
	@Column(name = "ACCOUNT_NUMBER", nullable = false)
	private Long accountNumber;
	
	
	
	@Column(name = "TRANSACTION_AMOUNT", nullable = false)
	private  Double transactionAmount;
	
	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	
}
