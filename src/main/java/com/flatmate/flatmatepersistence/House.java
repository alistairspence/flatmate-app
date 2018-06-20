package com.flatmate.flatmatepersistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "flatmate_house")
public class House {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "house")
    private Set<Account> accounts = new HashSet<>();

    public House() {}

    public House(final String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public void setId(final Long id) { this.id = id; }

    public String getName() { return name; }

    public Set<Account> getAccounts() { return accounts; }

    public void addAccount(final Account account) { accounts.add(account); }

    public void removeAccount(final Account account) { accounts.remove(account); }

    public void setAccounts(final Set<Account> accounts) { this.accounts = accounts; }

}
