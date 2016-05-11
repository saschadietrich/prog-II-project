package ch.fhnw.project.io;

import ch.fhnw.project.model.DataModel;
import java.io.File;
import java.io.FileNotFoundException;

public interface DataReader {

    DataModel creatingModel(File file) throws FileNotFoundException;

}
