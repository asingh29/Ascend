package com.example.ascend;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Pitch {
    private String name;
    private int day;
    private String plan;
    private LocalTime start;
    private LocalTime end;

    Pitch(String n, int d, LocalTime s, LocalTime e) {
        this.day = d;
        this.start = s;
        this.end = e;
        this.name = n;
    }
    public int getDay() {
        return this.day;
    }
}
