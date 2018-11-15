package com.cognizant.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;



import com.cognizant.entity.TransactionDetailsVO;
import com.cognizant.exception.BankingManagementException;
import com.cognizant.vo.TransactionVO;

public class ViewTransactionDAO implements Serializable{
	private static final long serialVersionUID = 1L;
	 Session session = null;
	
	 public Session getSession(){
		return session = HibernateConnector.getInstance().getSession();
	 }
	public  List<TransactionDetailsVO> retrieveTransactionDetails(Long accountNumber, Long transactionId) throws BankingManagementException {      
      Transaction transaction = null;
     List<TransactionDetailsVO> transactionVOList = null;
       try {           
    	  
    	   String formQuery = "select TRANSACTION_ID as transactionId, TRANSACTION_DESCRIPTION as transactionDescription, TRANSACTION_TYPE as transactionType," +
	   		"ACCOUNT_NUMBER as accountNumber, TRANSACTION_AMOUNT as transactionAmount from TRANSACTION_DETAILS where ";
    	   SQLQuery query = null;
            //Criteria cr = getSession().createCriteria(TransactionDetailsVO.class);
    	   if(accountNumber != 0 && transactionId == 0){
    		   formQuery  = formQuery+"ACCOUNT_NUMBER= :accountNumber";
    	   		query = getSession().createSQLQuery(formQuery);
    	   		query.setParameter("accountNumber", accountNumber);
    	   } else if(accountNumber == 0 && transactionId != 0){
    		   	formQuery = formQuery+"TRANSACTION_ID= :transactionId";
    	   		query = getSession().createSQLQuery(formQuery);
    	   		query.setParameter("transactionId", transactionId);
    	   } else if(accountNumber != 0 && transactionId != 0){
    		   formQuery = formQuery+"ACCOUNT_NUMBER= :accountNumber and TRANSACTION_ID= :transactionId";
    		   query = getSession().createSQLQuery(formQuery);
    		   query.setParameter("accountNumber", accountNumber);
    		   query.setParameter("transactionId", transactionId);
    	   }
    	   int acc = accountNumber.intValue();
    	  
    	   query.addScalar("transactionId", StandardBasicTypes.LONG).addScalar("accountNumber",StandardBasicTypes.LONG).addScalar("transactionDescription",StandardBasicTypes.STRING)
    	   		.addScalar("transactionAmount",StandardBasicTypes.DOUBLE).addScalar("transactionType",StandardBasicTypes.STRING);
    	  
    	   
    	   
    	  query.setResultTransformer( Transformers.aliasToBean(TransactionDetailsVO.class)); 
            //cr.addScalar("num", StandardBasicTypes.LONG).list();
           // Collection<Object> list = cr.setResultTransformer(Transformers.aliasToBean(TransactionDetailsVO.class)).list();
    	   
    	   
           transactionVOList =  (List<TransactionDetailsVO>)query.list();
            /*for(final TransactionVO obj : (List<TransactionVO>)query.list()) {        	
            	transactionVOList.add((TransactionVO)obj);
            }*/
            /*while(iterator.hasNext()){
                Object[] tuple= (Object[]) iterator.next();
                TransactionVO transactionVO= new TransactionVO();
                transactionVO.setAccountNumber((Integer)tuple[0]);
                transactionVO.setTansactionId((Integer)tuple[1]);
                transactionVO.setTransactionAmount((Double)tuple[2]);
                transactionVO.setTransactionType((String)tuple[3]);
                transactionVO.setDescription((String)tuple[4]);
                TransactionVOList.add(transactionVO);
            }*/
          
           // System.out.println(result);
       } catch (GenericJDBCException e) {
           System.out.println("SQL State :"+e.getSQLState()+" "+e.getMessage());
           if(transaction != null)
        	   transaction.rollback();
           throw new BankingManagementException(e.getMessage());
       } finally {
       	if(session != null){
       		//session.flush();
           session.close();
           }
       }
       return transactionVOList;
       }
         
}
