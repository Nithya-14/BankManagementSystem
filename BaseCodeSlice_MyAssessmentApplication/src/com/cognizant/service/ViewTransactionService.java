package com.cognizant.service;

import java.util.List;

import com.cognizant.dao.PerformTransactionDAO;
import com.cognizant.dao.ViewTransactionDAO;
import com.cognizant.entity.TransactionDetailsVO;
import com.cognizant.exception.BankingManagementException;
import com.cognizant.vo.TransactionVO;

public class ViewTransactionService  {
	public List<TransactionDetailsVO> retrieveTransactionDetails(Long accountNumber, Long transactionId)throws BankingManagementException{
		ViewTransactionDAO viewTransactionDAO= new ViewTransactionDAO();		
		List<TransactionDetailsVO>  transactionVO = viewTransactionDAO.retrieveTransactionDetails(accountNumber, transactionId);
		return transactionVO;
	}
}