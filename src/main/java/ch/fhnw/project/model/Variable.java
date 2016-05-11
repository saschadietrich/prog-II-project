package ch.fhnw.project.model;


import java.util.ArrayList;
import java.util.List;

public class Variable {
    private String name;
    private List<Double> values = new ArrayList<>();

    public Variable(String name){
        this.name = name;
    }

    public void addValue(double value){
        values.add(value);
    }

    public String getName(){
        return name;
    }

    public List<Double> getValues(){
        return values;
    }
    // sollte noch min und max werte zurückgeben
}
