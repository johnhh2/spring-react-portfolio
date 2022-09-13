package com.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Account account;

    private String username;
    private String email;
    private int age;


    protected User() {}

    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public long getId() { return this.id; }
    public Account getAccount() { return this.account; }
    public String getUsername() { return this.username; }

    public void setAccount(Account account) { this.account = account; }

    public String toString() {
        return String.format(
            "{\"id\": %s, \"username\": \"%s\", \"email\": \"%s\", \"age\": %s}",
            this.id, this.username, this.email, this.age);
    }
}
