package com.solution.lab1.entity;

import java.util.Objects;

public class SolutionParameters {
    private int value1;
    private int value2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SolutionParameters that = (SolutionParameters) o;
        return value1 == that.value1 && value2 == that.value2 && interval1 == that.interval1 && interval2 == that.interval2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, interval1, interval2);
    }

    private int interval1;
    private int interval2;

    public SolutionParameters(int value1, int value2, int interval1, int interval2) {
        this.value1 = value1;
        this.value2 = value2;
        this.interval1 = interval1;
        this.interval2 = interval2;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }

    public int getValue1() {
        return value1;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public int getInterval1() {
        return interval1;
    }

    public void setInterval1(int interval1) {
        this.interval1 = interval1;
    }

    public int getInterval2() {
        return interval2;
    }

    public void setInterval2(int interval2) {
        this.interval2 = interval2;
    }
}
