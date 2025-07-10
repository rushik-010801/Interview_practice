package com.zee.zee5app.dto;

import com.zee.zee5app.exception.InvalidAmountException;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscriptions {
	private String dateOfPurchase;
	private String status;
	private String type;
	private String paymentMode;
	private boolean autoRenewal;
	private String expiryDate;
	private String id;
	
	@Setter(value = AccessLevel.NONE)
	private int amount;
	private String regId;
	
	public void setAmount(int amount) throws InvalidAmountException {
		if(amount < 100) {
			throw new InvalidAmountException("Amount Insufficient");
		}
		this.amount = amount;
	}
	
}
