package com.portfolio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    // Account Settings
    @OneToOne(cascade=CascadeType.ALL)
    private User user;
    private String realname;
    private boolean darkMode;

    // Portfolio
    @MapKey(name="id")
    @OneToMany(mappedBy="id")
    private List<PortfolioCategory> categories;
    // TODO: Make a Project class to store projects
//    private JSONArray projects;

    protected Account() {}

    public Account(User user) {
        this.user = user;
        this.user.setAccount(this);
        this.realname = this.user.getUsername();
        this.darkMode = false;
        this.categories = new ArrayList<PortfolioCategory>();
    }

    public User getUser() { return this.user; }
    public String getRealname() { return this.realname; }
    public boolean getDarkMode() { return this.darkMode; }
    public List<PortfolioCategory> getCategories() { return this.categories; }

    public void setDarkMode(boolean val) { this.darkMode = val; }

    public void addCategory(PortfolioCategory category) {
        category.setAccount(this);
        this.categories.add(category);
    }

    public String toString() {
        String out = "{\"id\": " + this.id + ", ";
        out += "\"user\": " + this.user.toString() + ", ";
        out += "\"realname\": \"" + this.realname + "\", ";
        out += "\"darkMode\": " + this.darkMode + ", ";
        out += "\"categories\": [";
        for (PortfolioCategory category : this.categories) {
            out += category.toString() + ", ";
        }
        if (out.substring(out.length() - 2).equals(", ")) {
            out = out.substring(0, out.length() - 2); // Remove trailing ", "
        }
        out += "]}";
//        object.put("projects", this.projects);

        return out;
    }
}
