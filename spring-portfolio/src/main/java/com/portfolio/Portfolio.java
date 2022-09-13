package com.portfolio;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

import org.json.JSONArray;
import org.json.JSONObject;

@Entity
public class Portfolio {
    @Id
    private String realname;

    @MapKey(name="id")
    @OneToMany(mappedBy="id")
    private List<PortfolioCategory> categories;
    // TODO: Make a Project class to store projects
//    private JSONArray projects;

    protected Portfolio() {}

    public Portfolio(String realname) {
        this.realname = realname;
        this.categories = new ArrayList();
    }

    public String getRealname() { return this.realname; }

    public void addCategory(PortfolioCategory category) {
        category.setPortfolio(this);
        this.categories.add(category);
    }

    public String toString() {
        String out = "{\"realname\": \"" + this.realname;
        out += "\", \"categories\": [";
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
