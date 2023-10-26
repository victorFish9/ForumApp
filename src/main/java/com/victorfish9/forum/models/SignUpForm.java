package com.victorfish9.forum.models;

import jakarta.validation.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SignUpForm {
    @NotEmpty
    @Size
    private String username = "";

    @NotEmpty
    @Size
    private String password = "";

    @NotEmpty
    @Size
    private String passwordRepeat = "";

    @NotEmpty
    @Size
    private String firstname = "";

    @NotEmpty
    private String role = "USER";

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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
