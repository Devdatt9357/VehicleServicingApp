package com.example.servii;

public class address {
    private String Userbuilding;
    private String Usercolony;
    private String Userlandmark;
    private String Useradditional;


    public address(){}

    public address(String userbuilding, String usercolony, String userlandmark, String useradditional) {
        Userbuilding = userbuilding;
        Usercolony = usercolony;
        Userlandmark = userlandmark;
        Useradditional = useradditional;
    }

    public String getUserbuilding() {
        return Userbuilding;
    }

    public void setUserbuilding(String userbuilding) {
        Userbuilding = userbuilding;
    }

    public String getUsercolony() {
        return Usercolony;
    }

    public void setUsercolony(String usercolony) {
        Usercolony = usercolony;
    }

    public String getUserlandmark() {
        return Userlandmark;
    }

    public void setUserlandmark(String userlandmark) {
        Userlandmark = userlandmark;
    }

    public String getUseradditional() {
        return Useradditional;
    }

    public void setUseradditional(String useradditional) {
        Useradditional = useradditional;
    }
}
