package com.voucher.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "RECIPIENT_TBL")
public class Recipient {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "email")
	private String email;
	
	@OneToMany(mappedBy = "recipient",cascade = CascadeType.ALL,orphanRemoval = true)
	private Set<Voucher> voucher = new HashSet<>();

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the voucher
	 */
	public Set<Voucher> getVoucher() {
		return voucher;
	}

	/**
	 * @param voucher the voucher to set
	 */
	public void setVoucher(Set<Voucher> voucher) {
		this.voucher = voucher;
		/*for(Voucher b : voucher) {
	            b.setRecipient(this);
	        }*/
	}

	
	
}
