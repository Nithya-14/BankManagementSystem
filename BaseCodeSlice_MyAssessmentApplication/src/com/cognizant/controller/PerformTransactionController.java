package com.cognizant.controller;


import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.ui.ModelMap;

import com.cognizant.entity.TransactionDetailsVO;
import com.cognizant.exception.BankingManagementException;
import com.cognizant.service.PerformTransactionService;
import com.cognizant.validator.TransactionDetailsValidator;
import com.cognizant.vo.TransactionVO;
import javax.validation.Valid;
@Controller
public class PerformTransactionController {  
 static Logger logger = Logger.getLogger(PerformTransactionController.class);
   @Autowired TransactionDetailsValidator transactionDetailsValidator;	

	
	 @RequestMapping(value = "/performTransaction", method = RequestMethod.GET)
   public ModelAndView performTransaction(ModelMap model) {
		 logger.info("Inside performTransaction");
	   return new ModelAndView("performTransaction.jsp", "TransactionDetails", new TransactionVO());
         }
   @RequestMapping(value = "/initiateTransaction", method = RequestMethod.POST)
   public ModelAndView initiateTransaction(@Valid @ModelAttribute("TransactionDetails") TransactionVO transactionVO,BindingResult bindingResult, 
ModelMap model,SessionStatus status) {
	   String message = null ;
	   transactionDetailsValidator.validate(transactionVO, bindingResult);
	  	   if (bindingResult.hasErrors()) {	  		 
	  		   logger.info("Returning performTransaction.jsp page");
	  		 model.addAttribute("error", "true");;	  		
	  		// model.addAttribute("command",transactionVO);
			return new ModelAndView("performTransaction.jsp","TransactionDetails",transactionVO); 
		   
		}else{
   model.addAttribute("accountNumber", transactionVO.getAccountNumber());   
   model.addAttribute("transactionType", transactionVO.getTransactionType());
   model.addAttribute("transactionAmount", transactionVO.getTransactionAmount());
   model.addAttribute("description", transactionVO.getDescription());
    PerformTransactionService performTransactionService = new PerformTransactionService();
    try{
    Double balance = performTransactionService.updateTransactionDetails(transactionVO);
    message = "Transaction Successfully Completed. The balance available in your Account is : " +balance;
    logger.info(message);
    model.addAttribute("Message",message);
    status.setComplete();
    }catch(BankingManagementException exception){    	
    	model.addAttribute("Message",exception.getMessage());
    	logger.error("Exception while updating TransactionDetails"+exception.getStackTrace());
    }
		}
   return new ModelAndView("initiateTransaction.jsp");   
}
}