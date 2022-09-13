package com.portfolio;

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
    private int id;

    @Column(unique=true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Hostname() { assert false : "Unreachable"; }

    public Hostname(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public User getUser() { return this.user; }

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

}
