package com.resellerapp.model;

import javax.validation.constraints.Size;

public class UserLoginBindingModel {

    @Size(min = 3, max = 20)
    private String username;

    @Size(min = 3, max = 20)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
