package com.portfolio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class UserSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private boolean darkMode;
    @OneToMany(mappedBy="id")
    private List<PortfolioCategory> categories;

    @OneToOne
    private User user;

    public UserSetting() { assert false : "Unreachable"; }

    public UserSetting(User user) {
        this.user = user;
        this.darkMode = false;
        this.categories = new ArrayList<PortfolioCategory>();
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
