package test.java;
 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cognizant.vo.TransactionVO;
 
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="file:WebContent/WEB-INF/BaseCodeSlice_MyAssessmentApplication-servlet.xml")
@WebAppConfiguration
public class PerformTransactionContollerTest {
 
                @Autowired
                private WebApplicationContext ctx;
 
                private MockMvc mvc;
 
                @Before
                public void setup() {
                                mvc = MockMvcBuilders.webAppContextSetup(ctx).build();
                }
                @Test
        	    public void performTransactionTest() throws Exception {
        	    	TransactionVO transactionVO = new TransactionVO();
        	    	transactionVO.setAccountNumber(1234567812345676L);
        	    	transactionVO.setDescription("Testing via Spring Test");  
        	    	transactionVO.setTransactionAmount(30000.00);
        	    	transactionVO.setTransactionType("Deposit");        	               
        	      
        	 
        	    	mvc.perform(post("/initiateTransaction").requestAttr("TransactionDetails", transactionVO));
                }
 
                
                public void performTransaction() throws Exception {
                                
                                mvc.perform(post("/performTransaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                                .param("description", "Spring Test").param("transactionType", "deposit").param("accountNumber", "1234567812345675")
                                                                .param("transactionAmount", "5000"));
                }
 /*
                @Test
                public void performTransactionWithError() throws Exception {
                                
                                mvc.perform(get("/performTransaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                                .param("description", "Test").param("transactionType", "Withdraw").param("accountNumber", "91949598424")
                                                                .param("transactionAmount", "5000"));
                }
 
                @Test
                public void performTransactionInsufficient() throws Exception {
                               
                                mvc.perform(get("/performTransaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                                .param("description", "Test").param("transactionType", "Withdraw").param("accountNumber", "1234567812345675")
                                                                .param("transactionAmount", "5000000000"));
                }
 
                @Test
                public void performTransactionMinimum() throws Exception {
                                
                                mvc.perform(get("/performTransaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                                .param("description", "Test").param("transactionType", "Withdraw").param("accountNumber", "9194959842402398")
                                                                .param("transactionAmount", "4000"));
                }
 
                @Test
                public void performTransactionSalary() throws Exception {
                                
                                mvc.perform(get("/performTransaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                                .param("description", "Test").param("transactionType", "Withdraw").param("accountNumber", "9194959842402397")
                                                                .param("transactionAmount", "4000"));
                }
 
                @Test
                public void performTransactionDeposit() throws Exception {
                           
                                mvc.perform(get("/performTransaction").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                                .param("description", "Test").param("transactionType", "Deposit").param("accountNumber", "1234567812345675")
                                                                .param("transactionAmount", "50000"));
                }*/
}