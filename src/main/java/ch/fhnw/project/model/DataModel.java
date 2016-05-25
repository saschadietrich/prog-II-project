package ch.fhnw.project.model;


import java.util.List;
import java.util.stream.Collectors;

public class DataModel {
    private List<Variable> variable;
    private String filename;

    public DataModel(List<String> names,String filename){
        variable = names.stream().map(Variable::new).collect(Collectors.toList());
        this.filename = filename;
    }

    public List<Variable> getVariable(){
        return variable;
    }

    public String getFilename(){
        return filename;
    }

    public void checkData()throws Exception{

        if(variable.size()<=1){
            throw new Exception("Too less variables");
        }

        for(int i=0; i<(variable.size()-1);i++){
            if(variable.get(i).getValues().size()!=variable.get(i+1).getValues().size()){
                throw new Exception("Variables have not the same amount of values");
            }
        }
    }
}
