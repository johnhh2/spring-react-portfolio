package com.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int key;
    private String username;
    private String email;
    private int age;

    public User(int key, String username, String email, int age) {
        this.key = key;
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public String toString() {
        return String.format(
            "{\"key\": %s, \"username\": \"%s\", \"email\": \"%s\", \"age\": %s}",
            this.key, this.username, this.email, this.age);
    }
}

