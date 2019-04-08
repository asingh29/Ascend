package com.example.ascend;

import java.util.Vector;

public class Peak {
    private String name;
    private Vector<Phase> phase;

    Peak(String goal) {
        this.name = goal;
    }
    public String getName() {
        return this.name;
    }

}
