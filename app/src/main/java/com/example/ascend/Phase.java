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
    private String start;
    private String end;

    public Phase() {
        super();
    }

    public Phase(String s, String e) {

        this.start = s;
        this.end = e;
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
            case 1: monday.add(p);
            case 2: tuesday.add(p);
            case 3: wednesday.add(p);
            case 4: thursday.add(p);
            case 5: friday.add(p);
            case 6: saturday.add(p);
            default: break;
        }
    }
}
