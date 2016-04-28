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
    List<String> valueList = new ArrayList<>();
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
                    valueList.add(fileScanner.nextLine());
                }

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
        for (String element: data.valueList){
            System.out.println(element);
        }
        System.out.println(data.delimiter);
    }
}
