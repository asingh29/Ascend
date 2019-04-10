package com.example.ascend;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import io.realm.RealmObject;

public class Pitch extends RealmObject {
    private String name;
    private int day;
    private String plan;
    private Date start;
    private Date end;

    public Pitch() {
        super();
    }

    public Pitch(String n, int d, Date s, Date e) {
        this.day = d;
        this.start = s;
        this.end = e;
        this.name = n;
    }
    public int getDay() {
        return this.day;
    }
}
