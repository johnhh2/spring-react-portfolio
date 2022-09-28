package com.portfolio.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditAccountRequest {
    @NotBlank
    @Size(max=35)
    private String realname;

    @NotBlank
    private String hostname;

    private boolean hostnameEnabled;
    private boolean darkMode;

    public String getRealname() { return this.realname; }
    public String getHostname() { return this.hostname; }
    public boolean getHostnameEnabled() { return this.hostnameEnabled; }
    public boolean getDarkMode() { return this.darkMode; }

    public void setRealname(String realname) { this.realname = realname; }
    public void setHostname(String hostname) { this.hostname = hostname; }
    public void setHostnameEnabled(boolean hostnameEnabled) { this.hostnameEnabled = hostnameEnabled; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }
}
