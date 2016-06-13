package com.thenairn.dataisbeautiful.model;

import com.google.firebase.database.IgnoreExtraProperties;

import lombok.Data;

@IgnoreExtraProperties
@Data
public class User {

    private String username;
    private String email;

    public User() {
    }


    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}