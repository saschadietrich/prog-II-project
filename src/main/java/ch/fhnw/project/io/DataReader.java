package ch.fhnw.project.io;

import ch.fhnw.project.model.DataModel;
import java.io.File;
import java.io.FileNotFoundException;

public interface DataReader {
    DataModel creatingDataModel(File file) throws FileNotFoundException;
}

