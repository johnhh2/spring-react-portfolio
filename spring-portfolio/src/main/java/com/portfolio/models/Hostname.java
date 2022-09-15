package com.portfolio.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.json.JSONObject;

@Entity
public class Hostname {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private String name;

    private boolean enabled;

    protected Hostname() {}

    public Hostname(String name) {
        this.name = name;
        this.enabled = true;
    }

    public String getName() { return this.name; }
    public boolean getEnabled() { return this.enabled; }

    public void setName(String name) { this.name = name; }

    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public JSONObject toJson() {
        JSONObject object = new JSONObject();
        object.put("name", this.name);
        object.put("enabled", this.enabled);

        return object;
    }

    public String toString() {
        return String.format(
            "Hostname(id: %S, name: %s, enabled: %s)",
            this.id, this.name, this.enabled);
    }
}
