package com.example.ascend;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Phase extends RealmObject implements Serializable {
    protected RealmList<Pitch> sunday;
    protected RealmList<Pitch> monday;
    protected RealmList<Pitch> tuesday;
    protected RealmList<Pitch> wednesday;
    protected RealmList<Pitch> thursday;
    protected RealmList<Pitch> friday;
    protected RealmList<Pitch> saturday;
    protected RealmList<Pitch> all;
    protected String name;
    protected Date start;
    protected Date end;
    protected String description;
    protected boolean active;

    public Phase() {
        super();
        active = false;
        sunday = new RealmList<Pitch>();
        monday = new RealmList<Pitch>();
        tuesday = new RealmList<Pitch>();
        wednesday = new RealmList<Pitch>();
        thursday = new RealmList<Pitch>();
        friday = new RealmList<Pitch>();
        saturday = new RealmList<Pitch>();
        all = new RealmList<Pitch>();
    }

    public Phase(Date s, Date e, String description) {
        this.start = s;
        this.end = e;
        this.description = description;
        active = false;
        sunday = new RealmList<Pitch>();
        monday = new RealmList<Pitch>();
        tuesday = new RealmList<Pitch>();
        wednesday = new RealmList<Pitch>();
        thursday = new RealmList<Pitch>();
        friday = new RealmList<Pitch>();
        saturday = new RealmList<Pitch>();
        all = new RealmList<Pitch>();


    }

    public RealmList<Pitch> getPitches(int day) {
        switch(day) {
            case 0: return sunday;
            case 1: return monday;
            case 2: return tuesday;
            case 3: return wednesday;
            case 4: return thursday;
            case 5: return friday;
            case 6: return saturday;
            default: return all;
        }
    }
    public void addPitch(Pitch p) {
        int day = p.getDay();
        switch(day) {
            case 0: sunday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            case 1: monday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            case 2: tuesday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            case 3: wednesday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            case 4: thursday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            case 5: friday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            case 6: saturday.add(p);
                if(!all.contains(p)) {
                    all.add(p);
                }
                break;
            default: break;
        }
    }
    public void setActive(boolean val) {
        active = val;
    }

    public String getDescription() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }
}
