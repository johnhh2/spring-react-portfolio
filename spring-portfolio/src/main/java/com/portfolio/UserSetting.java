package com.portfolio;

import java.util.Map;
import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean darkMode;
    private Map<PortfolioCategory, Boolean> categories;

    @OneToOne
    private User user;

    public UserSetting() { assert false : "Unreachable"; }

    public UserSetting(User user) {
        this.user = user;
        this.darkMode = false;
	this.categories = new HashMap<PortfolioCategory, Boolean>();
    }

    public User getUser() {
        return this.user;
    }

    public boolean getDarkMode() {
        return this.darkMode;
    }

    public void setDarkMode(boolean val) {
        this.darkMode = val;
    }
}
