package ch.fhnw.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface DataReader {

    public DataModel creatingModel(File file) throws FileNotFoundException;

}
