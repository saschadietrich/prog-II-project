package ch.fhnw.project;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DataModel {
    private List<Variable> variable;
    private String dateiname;

    public DataModel(List<String> names,String dateiname){
        variable = names.stream().map(Variable::new).collect(Collectors.toList());
        /*List<Variable> variable = new ArrayList<>();
        for (String name : names) {
            variable.add(new Variable(name));*/
        this.dateiname = dateiname;
    }

    public List<Variable> getVariable(){
        return variable;
    }

    public String getDateiname(){
        return dateiname;
    }
}
