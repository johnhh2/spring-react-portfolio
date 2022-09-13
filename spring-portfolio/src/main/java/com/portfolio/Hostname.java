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

    public Hostname() { assert false : "Unreachable"; }

    public Hostname(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public String getName() { return this.name; }
    public User getUser() { return this.user; }

    public void setName(String name) { this.name = name; }

}
