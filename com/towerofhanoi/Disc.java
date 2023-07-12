package com.towerofhanoi;

public class Disc {

    private static int count = 0;
    private int number;

    public Disc() {
        this.number = ++Disc.count;
    }

    public static int getCount() {
        return Disc.count;
    }

    public int getNumber() {
        return this.number;
    }

    @Override
    public String toString() {
        StringBuilder representation = new StringBuilder();
        for (int i = 0; i < this.number; i++) {
            representation.append("_");
        }
        return representation.toString();
    }

}
