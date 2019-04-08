package com.example.ascend;

import java.time.LocalDateTime;
import java.util.Vector;

public class Peak {
    private String name;
    private String description;
    private Vector<Phase> phase;
    private LocalDateTime start;
    private LocalDateTime end;

    Peak(String goal, String description, LocalDateTime s, LocalDateTime e) {
        this.name = goal;
        this.description = description;
        this.start = s;
        this.end = e;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {return this.description; }
    public LocalDateTime getStart() {
        return this.start;
    }
    public LocalDateTime getEnd() {
        return end;
    }
}
