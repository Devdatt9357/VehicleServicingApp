package com.example.servii;

public class washing_schedule {
    private String weekdays_before;
    private String weekdays_after;
    private String weekend_before;
    private String weekend_after;
    private int ref1,ref2,ref3,ref4;

    public washing_schedule(){

    }

    public washing_schedule(String weekdays_before, String weekdays_after, String weekend_before, String weekend_after, int ref1, int ref2, int ref3, int ref4) {
        this.weekdays_before = weekdays_before;
        this.weekdays_after = weekdays_after;
        this.weekend_before = weekend_before;
        this.weekend_after = weekend_after;
        this.ref1 = ref1;
        this.ref2 = ref2;
        this.ref3 = ref3;
        this.ref4 = ref4;
    }

    public String getWeekdays_before() {
        return weekdays_before;
    }

    public void setWeekdays_before(String weekdays_before) {
        this.weekdays_before = weekdays_before;
    }

    public String getWeekdays_after() {
        return weekdays_after;
    }

    public void setWeekdays_after(String weekdays_after) {
        this.weekdays_after = weekdays_after;
    }

    public String getWeekend_before() {
        return weekend_before;
    }

    public void setWeekend_before(String weekend_before) {
        this.weekend_before = weekend_before;
    }

    public String getWeekend_after() {
        return weekend_after;
    }

    public void setWeekend_after(String weekend_after) {
        this.weekend_after = weekend_after;
    }

    public int getRef1() {
        return ref1;
    }

    public void setRef1(int ref1) {
        this.ref1 = ref1;
    }

    public int getRef2() {
        return ref2;
    }

    public void setRef2(int ref2) {
        this.ref2 = ref2;
    }

    public int getRef3() {
        return ref3;
    }

    public void setRef3(int ref3) {
        this.ref3 = ref3;
    }

    public int getRef4() {
        return ref4;
    }

    public void setRef4(int ref4) {
        this.ref4 = ref4;
    }
}

