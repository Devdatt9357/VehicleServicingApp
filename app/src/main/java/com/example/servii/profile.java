package com.example.servii;

public class profile {
    private String Username;
    private String Userlocality;

public profile(){

    }

    public profile(String username, String userlocality) {
        Username = username;
        Userlocality = userlocality;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getUserlocality() {
        return Userlocality;
    }

    public void setUserlocality(String userlocality) {
        Userlocality = userlocality;
    }
}
