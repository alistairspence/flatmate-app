package com.flatmate.flatmatepersistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flatmate_transaction")
public class Transaction {

	@Id
	@GeneratedValue
	private Long id;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="TRANSACTION_ID")
	@JoinTable(name = "TRANSACTION_ACCOUNT",
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id"))
	private Account account;

	private Integer amount;

	public Transaction() {}

	public Transaction(final Account account, final Integer amount) {
		this.account = account;
		this.amount = amount;
	}

	public Long getId() { return id; }

	public Account getAccount() {
		return account;
	}

	public Integer getAmount() { return amount; }

	public void setAccount(final Account account) {
		this.account = account;
	}
}