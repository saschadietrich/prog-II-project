package ch.fhnw.project;


import java.util.List;

public class DataModel {
    private List<String> names;
    private List<double[]> valueList;

    public DataModel(List<String> names, List<double[]> valueList){
        this.names = names;
        this.valueList =valueList;
    }

    public String getName(int index){
        return names.get(index);
    }

    public double[] getValues(int index){
        return valueList.get(index);
    }

    public List<String> getallName(){
        return names;
    }
}
