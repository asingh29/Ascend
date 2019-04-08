package com.example.ascend;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Pitch {
    private String name;
    private LocalDateTime time;
    private Calendar repeats;

    Pitch(String n) {
        this.name = n;
    }
}
