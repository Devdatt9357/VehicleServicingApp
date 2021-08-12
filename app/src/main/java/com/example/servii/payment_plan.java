package com.example.servii;

public class payment_plan {
    private String days;

    public payment_plan()
    {

    }

    public payment_plan(String days) {
        this.days = days;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
