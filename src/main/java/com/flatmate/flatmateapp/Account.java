package com.flatmate.flatmateapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

	private Account() {}

	public Account(final String username, final String password) {
		this.username = username;
		this.password = password;
	}

	public Long getId() { return id; }

	public String getUsername() { return username; }

	public String getPassword() { return password; }

	public Set<Transaction> getTransactions() { return transactions; }

}