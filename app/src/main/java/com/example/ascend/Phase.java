package com.example.ascend;

import java.time.LocalDateTime;
import java.util.Vector;

public class Phase {
    private Vector<Vector<Pitch>> weeklyPitches;
    private LocalDateTime start;
    private LocalDateTime end;

    Phase(LocalDateTime s, LocalDateTime e) {
        this.weeklyPitches = new Vector<Vector<Pitch>>();
        Vector<Pitch> cur;
        for (int i = 0; i < 7; i++) {
            cur = new Vector<Pitch>();
            this.weeklyPitches.add(cur);
        }
        this.start = s;
        this.end = e;
    }

    public Vector<Pitch> getPitches(int day) {
        return weeklyPitches.get(day - 1);
    }
    public void addPitch(Pitch p) {

        Vector<Pitch> pitch = weeklyPitches.get(p.getDay());
        this.weeklyPitches.add(pitch);
    }
}
