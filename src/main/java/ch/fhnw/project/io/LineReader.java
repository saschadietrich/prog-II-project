package ch.fhnw.project.io;


import ch.fhnw.project.DataModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LineReader implements DataReader {


    @Override
    public DataModel creatingModel(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        int amountofVariables = Integer.parseInt(scanner.nextLine());
        List<String> variableNames = new ArrayList<>();

        for (int row = amountofVariables; row <= (amountofVariables+1); row++) { //Scannt bis zum Delimiter
                variableNames.add(scanner.nextLine());
            }

        String delimiter = scanner.nextLine();

        DataModel dataModel = new DataModel(variableNames, file.getName());

        //Hinzufügen der Werte zu den Variablen-Objekten, Scannt alle Linien nach dem Delimiter
        while (scanner.hasNextLine()) {
            for (int i = 0; i < (dataModel.getVariable().size()); i++) {    //dataModel.getVariable().size() --> ruft erst die liste der Variabeln und ab und bestimmt dann die Grösse!
                List<String> lines = new ArrayList<>((Arrays.asList(scanner.nextLine().split(delimiter)))); //Weisst du warum das Orange ist? // Liest eine Linie un Speichert sie als String -Liste
                for (String number : lines) {
                    dataModel.getVariable().get(i).addValue(Double.parseDouble(number));                   //Hinzufügen der eingelesen werte zu den Variabeln
                }
            }
        }
        return dataModel;
    }


    /*/ muss dann wieder raus
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File ("/home/stefan/IdeaProjects/prog-II-project/src/main/resources/reference-data.lin");
        DataModel object= creatingModel(file);
        List <Variable> test = object.getVariable();
        System.out.println("Filename: " + object.getFilename());
        System.out.println("Variables: " + object.getVariable());
        for (Variable variable : test) {
            System.out.println("Value: " + variable.getValues());
        }

    }*/
}