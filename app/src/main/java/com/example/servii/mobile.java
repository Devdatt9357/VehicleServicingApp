package com.example.servii;

import com.google.firebase.database.Exclude;

public class mobile {
    private String Usermobile;

    public mobile(){

    }

    public mobile(String usermobile) {
        Usermobile = usermobile;
    }

    public String getUsermobile() {
        return Usermobile;
    }

    public void setUsermobile(String usermobile) {
        Usermobile = usermobile;
    }
}
