package com.portfolio.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    // Account Settings
    @OneToOne(cascade=CascadeType.ALL)
    private User user;
    private String realname;
    private boolean darkMode;
    
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="hostname_id", referencedColumnName="id")
    private Hostname hostname;

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
        this.realname = "";
        this.darkMode = false;
        this.categories = new ArrayList<PortfolioCategory>();
        this.hostname = new Hostname("localhost");
    }

    public Account(User user, String realname, boolean darkMode) {
        this.user = user;
        this.user.setAccount(this);
        this.realname = realname;
        this.darkMode = darkMode;
        this.categories = new ArrayList<PortfolioCategory>();
        this.hostname = new Hostname("localhost");
    }

    public User getUser() { return this.user; }
    public String getRealname() { return this.realname; }
    public boolean getDarkMode() { return this.darkMode; }
    public List<PortfolioCategory> getCategories() { return this.categories; }
    public Hostname getHostname() { return this.hostname; }

    public void setRealname(String realname) { this.realname = realname; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }
    public void setHostname(Hostname hostname) { this.hostname = hostname; }

    public void addCategory(PortfolioCategory category) {
        category.setAccount(this);
        this.categories.add(category);
    }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("id", this.id);
        object.put("realname", this.realname);
        object.put("darkMode", this.darkMode);
        object.put("hostname", this.hostname.toJson());
        JSONArray categories = new JSONArray();
        for (PortfolioCategory category : this.categories)
            categories.put(category);
        object.put("categories", categories);
        return object;
    }

    private String categoriesToString() {
        String out = "";
        for (PortfolioCategory category : this.categories)
            out += category.toString() + ", ";
        if (out.length() > 0)
            out = out.substring(0, out.length() - 2);
        return out;
    }

    public String toString() {
        return String.format(
            "Account(id: %s, user: %s, realname: %s, darkMode: %s, categories: [%s])",
            this.id, this.user.toString(), this.realname, this.darkMode, this.categoriesToString());
    }
}
