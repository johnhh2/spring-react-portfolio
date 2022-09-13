package com.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Hostname {
    @Id
    private String name;

    @OneToOne
    private User user;

    @OneToOne
    private Account account;

    protected Hostname() {}

    public Hostname(String name, User user, Account account) {
        this.name = name;
        this.user = user;
        this.account = account;
    }

    public String getName() { return this.name; }
    public User getUser() { return this.user; }
    public Account getAccount() { return this.account; }

    public void setName(String name) { this.name = name; }

}
