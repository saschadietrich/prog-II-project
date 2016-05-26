package ch.fhnw.project.io;


import ch.fhnw.project.model.DataModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public final class LineReader implements DataReader {

    @Override
    public DataModel creatingDataModel(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        int amountOfVariables = Integer.parseInt(scanner.nextLine());
        List<String> variableNames = new ArrayList<>();

        for (int row = 0; row < (amountOfVariables); row++) {
                variableNames.add(scanner.nextLine());
        }

        String delimiter = scanner.nextLine();
        DataModel dataModel = new DataModel(variableNames, file.getName());

        while (scanner.hasNextLine()) {
            for (int i = 0; i < (dataModel.getVariable().size()); i++) {
                List<String> lines = new ArrayList<>((Arrays.asList(scanner.nextLine().split(delimiter))));
                for (String number : lines) {
                    dataModel.getVariable().get(i).addValue(Double.parseDouble(number));
                }
            }
        }
        return dataModel;
    }
}