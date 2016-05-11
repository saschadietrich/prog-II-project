package ch.fhnw.project.model;


import java.util.ArrayList;
import java.util.List;

public class Variable {
    private String name;
    private List<Double> values = new ArrayList<>();
    private double min, max;

    public Variable(String name){
        this.name = name;
    }

    public void addValue(double value){
        if (values.size()<1){
            min = value;
            max = value;
        }
        if (max < value){
            max = value;

        }
        if (min > value){
            min = value;
        }
        values.add(value);

    }

    /*public String getName(){
        return name;
    }*/

    public List<Double> getValues(){
        return values;
    }
    // sollte noch min und max werte zurückgeben

    @Override
    public String toString(){
        return name;
    }

    public double getMin(){
        return min;
    }

    public double getMax(){
        return max;
    }
}

