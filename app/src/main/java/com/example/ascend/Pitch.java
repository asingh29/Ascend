package com.example.ascend;

import java.util.Date;
import io.realm.RealmObject;

public class Pitch extends RealmObject {
    private String name;
    private int day;
    private String plan;
    private String start;
    private String end;
    private boolean complete;

    public Pitch() {
        super();
        complete = false;
    }

    public Pitch(String n, int d, String s, String e) {
        this.day = d;
        this.start = s;
        this.end = e;
        this.name = n;
        complete = false;
    }
    public int getDay() {
        return this.day;
    }

    public String getName() {
        return name;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
