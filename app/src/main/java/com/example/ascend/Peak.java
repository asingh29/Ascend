package com.example.ascend;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Peak extends RealmObject {
    private String name;
    private String description;
    private RealmList<Phase> phase;
    private String start;
    private String end;

    public Peak() {
        super();
        phase = new RealmList<Phase>();
    }

    public Peak(String goal, String description, String s, String e) {
        this.name = goal;
        this.description = description;
        this.start = s;
        this.end = e;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {return this.description; }
    public String getStart() {
        return this.start;
    }
    public String getEnd() {
        return end;
    }
    public RealmList<Phase> getPhase() { return this.phase; }
    public void addPhase(Phase p) {
        this.phase.add(p);
    }
}
