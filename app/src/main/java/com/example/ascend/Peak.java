package com.example.ascend;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.Vector;

import io.realm.RealmObject;

public class Peak extends RealmObject {
    private String name;
    private String description;
    private Vector<Phase> phase;
    private GregorianCalendar start;
    private GregorianCalendar end;

    Peak(String goal, String description, GregorianCalendar s, GregorianCalendar e) {
        this.name = goal;
        this.description = description;
        this.start = s;
        this.end = e;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {return this.description; }
    public GregorianCalendar getStart() {
        return this.start;
    }
    public GregorianCalendar getEnd() {
        return end;
    }
    public Vector phaseVector() { return this.phase; }
    public void addPhase(Phase p) {
        this.phase.add(p);
    }
}
