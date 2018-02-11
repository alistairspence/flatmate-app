package com.flatmate.flatmateapp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flatmate_transaction")
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;

	@JsonIgnore
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="TRANSACTION_ID")
	private Account account;

	private Integer amount;

	private Transaction() {}

	public Transaction(final Account account, final Integer amount) {
		this.account = account;
		this.amount = amount;
	}

	public Long getId() { return id; }

	public Account getAccount() { return account; }

	public Integer getAmount() { return amount; }

}