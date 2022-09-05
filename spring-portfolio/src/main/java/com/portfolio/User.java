package com.portfolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class User {

    private int key;
    private String username;
    private String email;
    private boolean dark_mode;
    private int age;

    public User(int key, String username, String email,
                            boolean dark_mode, int age) {
        this.key = key;
        this.username = username;
        this.email = email;
        this.dark_mode = dark_mode;
        this.age = age;
    }

    public String toString() {
        return String.format(
            "{\"key\": %s, \"username\": \"%s\", \"email\": \"%s\", \"dark_mode\": %s, \"age\": %s}",
            this.key, this.username, this.email, this.dark_mode, this.age);
    }
}

