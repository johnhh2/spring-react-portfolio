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
    private Portfolio portfolio;

    public Hostname() { assert false : "Unreachable"; }

    public Hostname(String name, User user, Portfolio portfolio) {
        this.name = name;
        this.user = user;
        this.portfolio = portfolio;
    }

    public String getName() { return this.name; }
    public User getUser() { return this.user; }
    public Portfolio getPortfolio() { return this.portfolio; }

    public void setName(String name) { this.name = name; }

}
