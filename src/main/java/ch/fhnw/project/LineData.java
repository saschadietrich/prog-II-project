package ch.fhnw.project;

import java.io.File;
import java.util.*;
public class LineData {

    // Fields:
    String delimiter = "";
    private List<String> variableNames = new ArrayList<>();
    private Map<String, List<Double>> dataset = new HashMap<>();

    //Constructor:

    public LineData(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            int amountofVariables = Integer.parseInt(fileScanner.nextLine());
            int maxAmountofLines = 2 * (amountofVariables + 1);

            List<List> valueList = new ArrayList<>();

            for (int row = amountofVariables; row <= maxAmountofLines; row++) {
                if (row <= (amountofVariables + 1)) {
                    variableNames.add(fileScanner.nextLine());
                } else if (row == amountofVariables + 2) {
                    delimiter = fileScanner.nextLine();
                    fileScanner.useDelimiter(delimiter);
                } else {
                    while (fileScanner.hasNextLine()) {
                        List<String> lines = new ArrayList(Arrays.asList(fileScanner.nextLine().split(delimiter)));
                        List<Double> arrayLine = new ArrayList<>();
                        for(String line:lines){
                            arrayLine.add(Double.parseDouble(line));
                        }
                        valueList.add(arrayLine);
                    }
                }

            }
            for (int i = 0; i < variableNames.size(); i++) {
                dataset.put(variableNames.get(i), valueList.get(i));
            }

        } catch (Exception e) {
            System.out.println("File not found!");
        }

    }

    public String getNameOfVariables(int y) {
        return variableNames.get(y);
    }

    public double[] getValuesofVariable(String key) {
        List<Double> arrayList = dataset.get(key);

        double [] valueList = new double [arrayList.size()];
        for (int i = 0; i < valueList.length; i++){
            //System.out.println(arrayList.get(i));
            valueList[i]= arrayList.get(i);    // hier kann es iwie nicht umwandeln
            //System.out.println(valueList[i]);
        }

        return valueList;
        }



    /*public static void main(String[] args) {
        LineData data = new LineData("/home/stefan/IdeaProjects/prog-II-project/src/main/resources/reference-data.lin");
        for (String word : data.variableNames) {
            System.out.println(word);
            //System.out.println();
        }

        for (Map.Entry<String, List<Double>> entry : data.dataset.entrySet()) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }
        data.getValuesofVariable(data.getNameOfVariables(0));

    }*/
}

