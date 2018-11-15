package com.cognizant.dao;
import com.cognizant.controller.PerformTransactionController;
import com.cognizant.entity.TransactionDetailsVO;
import com.cognizant.vo.TransactionVO;
import com.cognizant.entity.User;
import com.cognizant.exception.BankingManagementException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;


import java.io.Serializable;
import java.sql.SQLException;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.GenericJDBCException;

 
public class PerformTransactionDAO implements Serializable {
 
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	 Session session = null;
	 private static Logger logger = Logger.getLogger(PerformTransactionController.class.getName());
		 public Session getSession(){
		return session = HibernateConnector.getInstance().getSession();
	 }
	public Double updateTransactionDetails(TransactionVO transactionVO) throws BankingManagementException {
       
       Transaction transaction = null;
       Double balance =0.0;
       String mgs = null;
        try {  
        	logger.info("Inside updateTransactionDetails()");
             transaction = getSession().beginTransaction();
           TransactionDetailsVO transactionDetailsVO = new TransactionDetailsVO();            
    		transactionDetailsVO.setTransactionDescription(transactionVO.getDescription());
    		transactionDetailsVO.setTransactionType(transactionVO.getTransactionType());
    		transactionDetailsVO.setAccountNumber(transactionVO.getAccountNumber().longValue());
    		transactionDetailsVO.setTransactionAmount(transactionVO.getTransactionAmount());   		
            session.saveOrUpdate(transactionDetailsVO);  
           
            balance = (Double)session.createQuery("select accountBalance from User where accountNumber= :accountNumber").setParameter("accountNumber", transactionVO.getAccountNumber()).uniqueResult();
            transaction.commit();                 		
        } catch (GenericJDBCException e) {
        	logger.error("SQL State :"+e.getSQLState()+" "+e.getMessage());
            transaction.rollback();
            throw new BankingManagementException(e.getMessage());
        } finally {
        	if(session != null){
        		//session.flush();
            session.close();
            }
        }
        return balance;
	} 
	
    
}
	