package com.cognizant.exception;

public class BankingManagementException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BankingManagementException(String message) {
		super(message);
	}

	public BankingManagementException(Throwable throwable) {
		super(throwable);
	}

	public BankingManagementException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
