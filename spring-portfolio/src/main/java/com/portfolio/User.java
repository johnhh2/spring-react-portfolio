package com.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String email;
    private int age;

    public User() {
        this.id = 0;
        this.username = "";
        this.email = "";
        this.age = 0;
    }

    public User(int id, String username, String email, int age) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.age = age;
    }

    public int getId() { return this.id; }

    public String toString() {
        return String.format(
            "{\"id\": %s, \"username\": \"%s\", \"email\": \"%s\", \"age\": %s}",
            this.id, this.username, this.email, this.age);
    }
}
