package com.cognizant.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="User")
public class User {
	@Id
	@Column(name = "ACCOUNT_NUMBER", nullable = false)
	private Long accountNumber;
	
	@Column(name = "ACCOUNT_TYPE", nullable = false)
	private  String accountType;
	
	@Column(name = "ACCOUNT_HOLDER_NAME", nullable = false)
	private String accountHolderName;
	
	@Column(name = "ACCOUNT_BALANCE", nullable = false)
	private Double accountBalance;
	
	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
}

