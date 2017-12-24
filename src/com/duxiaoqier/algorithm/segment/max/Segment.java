package com.duxiaoqier.algorithm.segment.max;

public class Segment {
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    private int start;
    private int end;

    public Segment(int x, int y) {
        this.start = x;
        this.end = y;
    }
}
