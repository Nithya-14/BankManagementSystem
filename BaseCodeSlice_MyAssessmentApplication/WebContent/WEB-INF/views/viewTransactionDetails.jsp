<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
   <head>
      <title>Transaction Details</title>
     <!--   <link href="<c:url value="../resources/css/styles.css" />" rel="stylesheet" >-->
     
    <style><%@include file="../css/styles.css"%></style>
   </head>   
   
 <body>
<h3>Transaction Details </h3>
<table >
	<tr>
           	<th>Account Number</th>
            <th>Transaction ID</th>
            <th>Transaction Type</th>  
            <th>Description</th>    
            <th>Balance</th>
            
        </tr>
    <c:forEach items="${response}" var="trans">
        <tr>
        	<td>${trans.AccountNumber}</td> 
            <td>${trans.TansactionId}</td>
            <td>${trans.TransactionType}</td>
            <td>${trans.Description}</td>
            <td>${trans.TransactionAmount}</td>
               
           
        </tr>
    </c:forEach>
</table>
 <a href="/BaseCodeSlice_MyAssessmentApplication/WebContent/index.jsp">Home</a> 
</body>
</html>