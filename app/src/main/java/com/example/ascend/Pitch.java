package com.example.ascend;

import java.io.Serializable;
import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pitch extends RealmObject implements Serializable {
    @PrimaryKey
    protected String name;
    protected int day;
    protected String plan;
    protected String start;
    protected String end;
    protected boolean complete;

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
