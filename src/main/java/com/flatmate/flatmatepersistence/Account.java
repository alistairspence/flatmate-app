package com.flatmate.flatmatepersistence;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "flatmate_account")
public class Account {

	@Id
	@GeneratedValue
	private Long id;

	private String username;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;

	@OneToMany(mappedBy = "account")
	private Set<Transaction> transactions = new HashSet<>();

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "ACCOUNT_HOUSE",
		joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "house_id", referencedColumnName = "id"))
	private House house;

	public Account() {}

	public Account(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	// TODO(alistair): only for testing purposes - shouldn't expose something like this!
	public void setId(final Long id) {
		this.id = id;
	}

	public Long getId() { return id; }

	public String getUsername() { return username; }

	public String getPassword() { return password; }

	public Set<Transaction> getTransactions() { return transactions; }

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public void setHouse(final House house) { this.house = house; }

	public House getHouse() { return house; }

}