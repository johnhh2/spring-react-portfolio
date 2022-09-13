package com.portfolio;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import java.text.Normalizer;

import org.json.JSONObject;

@Entity
public class PortfolioCategory {
    private static final int MAX_SLUG_LENGTH = 256;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="account_id")
    private Account account;

    private String name;
    private String slug;
    private String googleIcon;

    protected PortfolioCategory() {}

    public PortfolioCategory(String name, String googleIcon) {
        this.name = name;
        this.slug = this.slugify(name);
        this.googleIcon = googleIcon;
    }

    public Account getAccount() { return this.account; }

    public void setAccount(Account account) { this.account = account; }

    public String toString() {
       String out = "{\"name\": \"" + this.name + "\", ";
       out += "\"slug\": \"" + this.slug + "\", ";
       out += "\"googleIcon\": \"" + this.googleIcon + "\"}";
       return out;
    }

    public JSONObject toJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", this.name);
        jsonObject.put("slug", this.slug);
        jsonObject.put("googleIcon", this.googleIcon);
        return jsonObject;
    }

    public String slugify(String input) {
        final String intermediateResult = Normalizer
            .normalize(input, Normalizer.Form.NFD)
            .replaceAll("[^\\p{ASCII}]", "")
            .replaceAll("[^-_a-zA-Z0-9]", "-").replaceAll("\\s+", "-")
            .replaceAll("[-]+", "-").replaceAll("^-", "")
            .replaceAll("-$", "").toLowerCase();
            return intermediateResult.substring(0,
                Math.min(MAX_SLUG_LENGTH, intermediateResult.length()));
    }
}
