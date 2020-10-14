package com.voucher.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

//@JsonInclude(Include.NON_NULL)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoucherResonse {
	private String status;
	private String message;
	private Set<VoucherDto> response;
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
	 * @return the response
	 */
	public Set<VoucherDto> getResponse() {
		return response;
	}
	/**
	 * @param response the response to set
	 */
	public void setResponse(Set<VoucherDto> response) {
		this.response = response;
	}
	

}
