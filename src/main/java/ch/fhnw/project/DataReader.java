package ch.fhnw.project;

import java.io.File;
import java.util.List;

public interface DataReader {

    public DataModel creatingModel(File file);

    public List<String> createVariableNameList(File file);

    public List<double[]> createValuesList(File file);

}
