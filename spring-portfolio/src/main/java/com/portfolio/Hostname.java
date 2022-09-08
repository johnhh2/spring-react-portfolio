package com.portfolio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Hostname {
    @Id
    private String name;
    /*private User user;*/

    public Hostname(String name, User user) {
        super();
        this.name = name;
        /*this.user = user;*/
    }
}
