package ch.fhnw.project;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TxtReader  {

    //@Override
    public static DataModel creatingModel(File file) throws FileNotFoundException { // sobald inferface funktioniert wieder ranmachen

        Scanner scanner = new Scanner(file);
        String dateiname = "name muss noch hin";
        DataModel datastructure = new DataModel( Arrays.asList(scanner.nextLine().split("\\t")),dateiname);
        int numberOfVariable = (datastructure.getVariable()).size();
        List<Variable> variable = datastructure.getVariable();

        while (scanner.hasNextLine()){
            String[] linearray = (scanner.nextLine().split("\\t"));
            for(int i=0; i<numberOfVariable; i++){
                (variable.get(i)).addValue(Double.parseDouble(linearray[i]));
            }
        }
        return datastructure;
    }
}
