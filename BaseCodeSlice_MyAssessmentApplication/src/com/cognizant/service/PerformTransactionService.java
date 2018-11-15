package com.cognizant.service;

import com.cognizant.dao.PerformTransactionDAO;
import com.cognizant.entity.TransactionDetailsVO;
import com.cognizant.exception.BankingManagementException;
import com.cognizant.vo.TransactionVO;

public class PerformTransactionService  {
	public Double updateTransactionDetails(TransactionVO transactionVO)throws BankingManagementException{
		PerformTransactionDAO performTransactionDAO= new PerformTransactionDAO();		
		double balance = performTransactionDAO.updateTransactionDetails(transactionVO);
		return balance;
	}
}
