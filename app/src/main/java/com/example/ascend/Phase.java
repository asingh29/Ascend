package com.example.ascend;

import java.time.LocalDateTime;
import java.util.Vector;

public class Phase {
    private Vector<Pitch> pitches;
    private LocalDateTime start;
    private LocalDateTime end;

    Phase(LocalDateTime s, LocalDateTime e) {
        this.pitches = new Vector<Pitch>();
        this.start = s;
        this.end = e;
    }


}
