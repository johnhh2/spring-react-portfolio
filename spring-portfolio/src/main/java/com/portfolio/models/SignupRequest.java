package com.portfolio.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignupRequest {
    @NotBlank
    @Size(min=3, max=20)
    private String username;

    @NotBlank
    @Size(max=50)
    @Email
    private String email;

    private boolean darkMode;

    @NotBlank
    @Size(min=6, max=40)
    private String password;

    public String getUsername() { return this.username; }
    public String getEmail() { return this.email; }
    public boolean getDarkMode() { return this.darkMode; }
    public String getPassword() { return this.password; }

    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }
    public void setPassword(String password) { this.password = password; }
}
