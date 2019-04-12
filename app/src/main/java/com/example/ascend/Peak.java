package com.example.ascend;

import java.io.Serializable;
import java.nio.file.attribute.DosFileAttributes;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Peak extends RealmObject implements Serializable {
    protected String name;
    protected String description;
    protected RealmList<Phase> phase;
    protected Date start;
    protected Date end;

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
    public void addPhase(Phase p) {
        this.phase.add(p);
    }
}
