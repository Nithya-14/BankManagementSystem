package com.cognizant.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.entity.TransactionDetailsVO;
import com.cognizant.exception.BankingManagementException;
import com.cognizant.service.ViewTransactionService;
import com.cognizant.vo.TransactionVO;

//import org.apache.log4j.Logger;

@Controller

public class ViewTransactionController extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger(ViewTransactionController.class.getName());
	
	
	 @RequestMapping(value = "/viewTransaction", method = RequestMethod.GET)
   public ModelAndView viewTransaction(ModelMap model) {
		 logger.debug("Inside viewTransaction");
	   return new ModelAndView("viewTransaction.html", "command", new TransactionVO());
	   }
	 

	 @RequestMapping(value = "ViewTransactionDetails", method = RequestMethod.POST)
	 @Produces(MediaType.APPLICATION_JSON)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public ModelAndView SearchUserTransaction(
		   //@FormParam ("transId") Long transId,
		   //@FormParam ("accNum") Long accNum
		   HttpServletRequest request, HttpServletResponse response) {
		 
		 JSONArray array = new JSONArray();
		 String transactionId = request.getParameter("transId");
		 String accountNumber = request.getParameter("accNum");
		 Long transId = 0L;
		 Long accNum = 0L;
		 if(!(transactionId == ""))
			 transId = Long.parseLong(request.getParameter("transId"));
		 if(!(accountNumber == ""))
			 accNum  = Long.parseLong(request.getParameter("accNum"));
		 ModelAndView mav = new ModelAndView();
		 try{
			 ViewTransactionService viewTransactionService = new ViewTransactionService();
			 List<TransactionDetailsVO> results = (List<TransactionDetailsVO>)viewTransactionService.retrieveTransactionDetails(accNum,transId);
			 if(results == null || results.size()==0){	 
				 
				 mav.addObject("Message","No Records Found");
				 mav.setViewName("initiateTransaction.jsp");
			 }else{
			 for(TransactionDetailsVO transactionVO :  results){
				 JSONObject obj= new JSONObject();
				 obj.put("TansactionId", transactionVO.getTransactionId());
				 obj.put("AccountNumber", transactionVO.getAccountNumber());
				 obj.put("TransactionType", transactionVO.getTransactionType());
				 obj.put("TransactionAmount", transactionVO.getTransactionAmount());
				 obj.put("Description", transactionVO.getTransactionDescription());
				 array.add(obj);
			 }			 
			 
			mav.addObject("response", array);
			mav.setViewName("viewTransactionDetails.jsp");
			 }
		 }catch(BankingManagementException exception){
			 logger.error("Exception while viewing viewTransactionDetails"+exception.getStackTrace());
		 }
	   return mav;
         }
}
