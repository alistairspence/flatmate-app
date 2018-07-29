package com.flatmate.persistence;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "flatmate_account")
public class Account {

	// TODO(alistair): replace these with uuids
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Pattern(regexp = "^[A-Za-z0-9]+$")
	private String username;

	// TODO(alistair): what sort of password validation should be done?
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

	public Long getId() { return id; }

	public void setId(final Long id) { this.id = id; }

	public String getUsername() { return username; }

	public String getPassword() { return password; }

	public Set<Transaction> getTransactions() { return transactions; }

	public void setTransactions(final Set<Transaction> transactions) {
		this.transactions = transactions;
	}

	public House getHouse() { return house; }

	public void setHouse(final House house) { this.house = house; }

}