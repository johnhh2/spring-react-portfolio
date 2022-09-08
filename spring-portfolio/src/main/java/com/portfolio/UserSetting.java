package com.portfolio;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserSetting {
    @Id
    private int userId;
    private boolean privateMode;
    private boolean darkMode;

    public UserSetting(int userId) {
        this.userId = userId;
        this.privateMode = true;
        this.darkMode = false;
    }

    public int getUserId() {
        return userId;
    }

    public boolean getPrivateMode() {
        return this.privateMode;
    }

    public void setPrivateMode(boolean val) {
        this.privateMode = val;
    }

    public boolean getDarkMode() {
        return this.darkMode;
    }

    public void setDarkMode(boolean val) {
        this.darkMode = val;
    }
}
