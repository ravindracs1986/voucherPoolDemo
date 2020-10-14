package com.voucher.model;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipientResonse {
	private String status;
	private String message;
	private String discount;
	private String expDate;
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the discount
	 */
	public String getDiscount() {
		return discount;
	}
	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	/**
	 * @return the expDate
	 */
	public String getExpDate() {
		return expDate;
	}
	/**
	 * @param expDate the expDate to set
	 */
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	
	

}
