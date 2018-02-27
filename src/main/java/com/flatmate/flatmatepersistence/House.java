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
    private Set<Account> users = new HashSet<>();

    public House() {}

    public House(final String name) {
        this.name = name;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public Set<Account> getUsers() { return users; }

    public void addUser(final Account account) { users.add(account); }

    public void removeUser(final Account account) { users.remove(account); }

    public void setUsers(final Set<Account> users) { this.users = users; }

}
