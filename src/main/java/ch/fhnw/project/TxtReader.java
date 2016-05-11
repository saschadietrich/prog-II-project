package ch.fhnw.project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class TxtReader {


    //@Override// sobald inferface funktioniert wieder ranmachen
    public static DataModel creatingModel(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        DataModel dataStructure = new DataModel(Arrays.asList(scanner.nextLine().split("\\t")),file.getName());
        int numberOfVariable = (dataStructure.getVariable()).size();
        List<Variable> variable = dataStructure.getVariable();
        while (scanner.hasNextLine()){
            String[] lineArray = (scanner.nextLine().split("\\t"));
            for(int i=0; i<numberOfVariable; i++){
                (variable.get(i)).addValue(Double.parseDouble(lineArray[i]));
            }
        }
        return dataStructure;
    }
}
