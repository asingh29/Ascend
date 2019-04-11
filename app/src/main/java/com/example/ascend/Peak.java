package com.example.ascend;

import java.nio.file.attribute.DosFileAttributes;
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
    private Date start;
    private Date end;

    public Peak() {
        super();
        phase = new RealmList<Phase>();
    }

    public Peak(String goal, String description, Date s, Date e) {
        this.name = goal;
        this.description = description;
        this.start = s;
        this.end = e;
        phase = new RealmList<Phase>();
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {return this.description; }
    public Date getStart() {
        return this.start;
    }
    public Date getEnd() {
        return end;
    }
    public RealmList<Phase> getPhase() { return this.phase; }
    public void addPhase(Phase p) {
        this.phase.add(p);
    }
}
