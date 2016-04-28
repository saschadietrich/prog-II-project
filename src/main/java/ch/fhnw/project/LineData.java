package ch.fhnw.project;

import java.io.File;
import java.util.*;

/**
 * Created by stefan on 28.04.16.
 */
public class LineData {

    // Fields:
    List<String> datalines;
    List<String> variableNames = new ArrayList<>();
    List<List> valueList = new ArrayList<>();
    Map<String,List<Double>> dataset = new HashMap<>();
    String delimiter = "";

    //Constructor:

    public LineData(String filePath, int line) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);

            datalines = new ArrayList<>();
            int amountofVariables = Integer.parseInt(fileScanner.nextLine());
            int maxAmountofLines = 2*(amountofVariables+1);
            for (int row = amountofVariables; row<=maxAmountofLines; row++ ){
                if(row <= (amountofVariables+1)){
                variableNames.add(fileScanner.nextLine());
                }
                else if( row == amountofVariables+2){
                    delimiter = fileScanner.nextLine();
                    fileScanner.useDelimiter(delimiter);
                }
                else{
                    while(fileScanner.hasNextLine()){
                        String lines = fileScanner.nextLine();
                        List<Double> arrayLine = new ArrayList(Arrays.asList(lines.split(delimiter)));
                        valueList.add(arrayLine);
                    }
                }

            }
            for (int i = 0; i< variableNames.size(); i++){
                dataset.put(variableNames.get(i), valueList.get(i));
            }

        }
        catch(Exception e){
            System.out.println("File not found!");
        }
    }

    public static void main(String[] args) {
        LineData data = new LineData("/home/stefan/IdeaProjects/prog-II-project/src/main/resources/reference-data.lin", 0);
        for (String word : data.variableNames) {
            System.out.println(word);
            //System.out.println();
        }
        for (List element: data.valueList){
            System.out.println(element);
        }
        for (Map.Entry<String,List<Double>> entry: data.dataset.entrySet()){
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }

        System.out.println(data.delimiter);
    }
}
