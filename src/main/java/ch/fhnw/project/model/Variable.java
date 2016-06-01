package ch.fhnw.project.model;

import java.util.ArrayList;
import java.util.List;

public final class Variable {
    private String name;
    private List<Double> values = new ArrayList<>();
    private double min, max;

    public Variable(String name) {
        this.name = name;
    }

    public void addValue(double value) {
        /*
        Adds new value to the list and sets max or min value
         */
        if (values.size() < 1) {
            min = value;
            max = value;
        }
        if (max < value) {
            max = value;

        }
        if (min > value) {
            min = value;
        }
        values.add(value);
    }

    public List<Double> getValues() {
        return values;
    }

    @Override
    public String toString() {
        return name;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}

