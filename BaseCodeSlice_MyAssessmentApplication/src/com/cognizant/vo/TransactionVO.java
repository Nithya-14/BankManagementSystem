package com.cognizant.vo;

import java.io.Serializable;

public class TransactionVO implements Serializable {
	private Long accountNumber;
	private Long transactionId;
//	private String customerName;
	private String transactionType;
	private Double transactionAmount;
	private String description;
	
	public void setAccountNumber(Long accountNumber){
		this.accountNumber = accountNumber;
	}
	public Long getAccountNumber(){
		return this.accountNumber;  
		}	
	
	public void setTansactionId(Long transactionId){
		this.transactionId = transactionId;
	}
	public Long getTansactionId(){
		return this.transactionId;  
	}
	/*public void setCustomerName(String customerName){
		this.customerName = customerName;
	}
	public String getCustomerName(){
		return this.customerName;  
	}*/
	public void setTransactionType(String transactionType){
		this.transactionType = transactionType;
	}
	public String getTransactionType(){
		return this.transactionType;
	}
	public void setTransactionAmount(Double transactionAmount){
		this.transactionAmount = transactionAmount;
	}
	public Double getTransactionAmount(){
		return this.transactionAmount;  
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;  
	}
  
	
	
}
