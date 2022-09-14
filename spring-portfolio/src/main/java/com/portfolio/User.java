package com.portfolio;

import org.json.JSONObject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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

    public void setAccount(Account account) { this.account = account; }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("account", this.account.toJson());
        object.put("username", this.username);
        object.put("email", this.email);
        object.put("age", this.age);
        return object;
    }

    public String toString() {
        return String.format(
            "User(id: %s, username: %s, email: %s, age: %s)",
            this.id, this.username, this.email, this.age);
    }
}
