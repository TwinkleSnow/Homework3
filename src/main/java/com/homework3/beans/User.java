package com.homework3.beans;

/**
 * user -- bean
 */
public class User {
    private int userid;
    private String username;
    private String password;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

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

    public User() {
        //
    }

    public User(int para_id, String para_name, String para_pass) {
        this.userid = para_id;
        this.username = para_name;
        this.password = para_pass;
    }
}
