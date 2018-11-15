<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Perform Transaction</title>
     <style><%@include file="../css/styles.css"%></style>     
   </head>
   <body> 
      <h2>Transaction Details</h2>
      <form:form method = "POST" action = "initiateTransaction" commandName="TransactionDetails">
         <table>
            <tr>                       
               <td><form:label path = "accountNumber">Account Number</form:label></td>
               <td><form:input type="number" path = "accountNumber" size = "16"/>
               <form:errors path="accountNumber" cssClass="error"/></td>              
            </tr>
            <tr > 
               <td><form:label path = "transactionType">Transaction Type</form:label></td>
               <td><form:select path = "transactionType">
               <form:option value ="Deposit" label = "Deposit"/>
               <form:option value ="Withdrawal" label = "Withdrawal"/></form:select>
               <form:errors path="transactionType" cssClass="error"/>
               </td>
            </tr>
            <tr >
               <td><form:label path = "transactionAmount">Transaction Amount</form:label></td>
               <td><form:input path = "transactionAmount" />
               <form:errors path="transactionAmount" cssClass="error"/></td>
            </tr>
            <tr>
               <td><form:label path = "description">Description</form:label></td>
               <td><form:textarea path = "description" size = "50"/>
               <form:errors path="transactionAmount" cssClass="error"/></td>
            </tr>            
            <tr>
               <td colspan = "2">
                  <input type = "submit" value = "Submit"/>
               </td>
            </tr>
         </table> 
        
      </form:form>
      <a href="${pageContext.request.contextPath}/index.jsp" >Home</a>
   </body>   
</html>