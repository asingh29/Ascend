package com.example.ascend;

import java.time.LocalDateTime;
import java.util.Vector;

public class Peak {
    private String name;
    private Vector<Phase> phase;
    private LocalDateTime start;
    private LocalDateTime end;

    Peak(String goal, LocalDateTime s, LocalDateTime e) {
        this.name = goal;
        this.start = s;
        this.end = e;
    }
    public String getName() {
        return this.name;
    }
    public LocalDateTime getStart() {
        return this.start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
}
