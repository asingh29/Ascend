package com.example.ascend;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Phase extends RealmObject {
    private RealmList<Pitch> sunday;
    private RealmList<Pitch> monday;
    private RealmList<Pitch> tuesday;
    private RealmList<Pitch> wednesday;
    private RealmList<Pitch> thursday;
    private RealmList<Pitch> friday;
    private RealmList<Pitch> saturday;
    private Date start;
    private Date end;
    private boolean active;

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
    }

    public Phase(Date s, Date e) {
        this.start = s;
        this.end = e;
        active = false;
        sunday = new RealmList<Pitch>();
        monday = new RealmList<Pitch>();
        tuesday = new RealmList<Pitch>();
        wednesday = new RealmList<Pitch>();
        thursday = new RealmList<Pitch>();
        friday = new RealmList<Pitch>();
        saturday = new RealmList<Pitch>();
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
            default: return null;
        }
    }
    public void addPitch(Pitch p) {
        int day = p.getDay();
        switch(day) {
            case 0: sunday.add(p);
                break;
            case 1: monday.add(p);
                break;
            case 2: tuesday.add(p);
                break;
            case 3: wednesday.add(p);
                break;
            case 4: thursday.add(p);
                break;
            case 5: friday.add(p);
                break;
            case 6: saturday.add(p);
                break;
            default: break;
        }
    }
    public void setActive(boolean val) {
        active = val;
    }
}
