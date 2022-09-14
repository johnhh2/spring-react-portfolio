package com.portfolio.models;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;

@Entity
public class Hostname {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
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

    public String toString() {
        return String.format(
            "Hostname(id: %S, name: %s, user: %s, account: %s)",
            this.id, this.name, this.user.toString(), this.account.toString());
    }
}
