package com.example.ascend;

import java.io.Serializable;
import java.util.Date;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Pitch extends RealmObject implements Serializable {
    @PrimaryKey
    protected String name;
    protected boolean sunday;
    protected boolean monday;
    protected boolean tuesday;
    protected boolean wednesday;
    protected boolean thursday;
    protected boolean friday;
    protected boolean saturday;
    protected String plan;
    protected String start;
    protected String end;
    protected boolean complete;

    public Pitch() {
        super();
        complete = false;
    }

    public Pitch(String n, String p, String s, String e, boolean su, boolean m, boolean t, boolean w, boolean th, boolean f, boolean sa) {
        sunday = su;
        monday = m;
        tuesday = t;
        wednesday = w;
        thursday = th;
        friday = f;
        saturday = sa;
        plan = p;
        this.start = s;
        this.end = e;
        this.name = n;
        complete = false;
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
