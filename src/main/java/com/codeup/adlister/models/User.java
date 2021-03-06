package com.codeup.adlister.models;

import com.codeup.adlister.util.Password;

public class User {
    private long id;
    private String username;
    private String email;
    private String password;
    private int isAdmin;

    public User() {}

    // this constructor is used to insert a new user /register
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password); // encrypt before insert
    }

    // this constructor is used when EDITing user settings
    // can change email and password only
    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        setPassword(password); // encrypt before update
    }

    // this constructor creates a User object from a users record (from DB)
    public User(long id, String username, String email, String password, int isAdmin) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password; // already encrypted in DB
        this.isAdmin = isAdmin;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = Password.hash(password); }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) { this.isAdmin = isAdmin; }

}
