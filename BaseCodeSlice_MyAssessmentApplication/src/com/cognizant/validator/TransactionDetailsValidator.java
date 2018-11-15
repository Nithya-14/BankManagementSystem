package com.cognizant.validator;

import com.cognizant.vo.TransactionVO;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class TransactionDetailsValidator implements Validator {
	public boolean supports(Class<?> paramClass) {
		return TransactionVO.class.isAssignableFrom(paramClass);
	}

	public void validate(Object obj, Errors errors) {
		TransactionVO transactionDetails = (TransactionVO) obj;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNumber",
				"error.accountNumber", "AccountNumber is Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "transactionAmount",
				"error.transactionAmount", "TransactionAmount is Required");
		
		
		if (transactionDetails.getAccountNumber() != null
				&& !this.validateAcNumber(String.valueOf(transactionDetails
						.getAccountNumber()))) {
			errors.rejectValue("accountNumber", "negativeValue",
					"Invalid Account Number");
		}

		if (transactionDetails.getTransactionAmount() != null
				&& transactionDetails.getTransactionAmount() <= 0.0D) {
			errors.rejectValue("transactionAmount", "negativeValue",
					"Invalid Transaction Amount");
		}

	}

	public boolean validateAcNumber(String acNumber) {
		return acNumber.matches("\\d{16}");
	}
}