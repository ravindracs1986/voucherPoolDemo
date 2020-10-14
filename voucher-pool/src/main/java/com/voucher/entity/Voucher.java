package com.voucher.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "VOUCHER_TBL")
public class Voucher {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "voucherCode")
	private String voucherCode;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "recipient_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Recipient recipient;
	
	@Column(name = "expDate")
	private Date expDate;
	
	@Column(name = "isUsed")
	private String isUsed;
	
	@Column(name = "usedDate")
	private Date usedDate;
	
	@Column(name = "offerName")
	private String offerName;
	/**
	 * @return the voucherCode
	 */
	public String getVoucherCode() {
		return voucherCode;
	}

	/**
	 * @param voucherCode the voucherCode to set
	 */
	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	/**
	 * @return the recipient
	 */
	public Recipient getRecipient() {
		return recipient;
	}

	/**
	 * @param recipient the recipient to set
	 */
	public void setRecipient(Recipient recipient) {
		this.recipient = recipient;
	}

	/**
	 * @return the expDate
	 */
	public Date getExpDate() {
		return expDate;
	}

	/**
	 * @param expDate the expDate to set
	 */
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	/**
	 * @return the isUsed
	 */
	public String getIsUsed() {
		return isUsed;
	}

	/**
	 * @param isUsed the isUsed to set
	 */
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	/**
	 * @return the usedDate
	 */
	public Date getUsedDate() {
		return usedDate;
	}

	/**
	 * @param usedDate the usedDate to set
	 */
	public void setUsedDate(Date usedDate) {
		this.usedDate = usedDate;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the offerName
	 */
	public String getOfferName() {
		return offerName;
	}

	/**
	 * @param offerName the offerName to set
	 */
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	
}
