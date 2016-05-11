package ch.fhnw.project.model;


import java.util.List;
import java.util.stream.Collectors;

public class DataModel {
    private List<Variable> variable;
    private String filename;

    public DataModel(List<String> names,String filename){
        variable = names.stream().map(Variable::new).collect(Collectors.toList());
        /*List<Variable> variable = new ArrayList<>();
        for (String name : names) {
            variable.add(new Variable(name));*/
        this.filename = filename;
    }

    public List<Variable> getVariable(){
        return variable;
    }

    public String getFilename(){
        return filename;
    }
}
