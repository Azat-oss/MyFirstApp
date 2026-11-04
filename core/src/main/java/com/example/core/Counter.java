package com.example.core;

public class Counter {
    private int count;
    private int clickPower;

    public Counter(int startCount, int startClickPower) {
        this.count = startCount;
        this.clickPower = startClickPower;
    }

    public void increment() {
        count += clickPower;
    }

    public void reset() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public int getClickPower() {
        return clickPower;
    }
}
