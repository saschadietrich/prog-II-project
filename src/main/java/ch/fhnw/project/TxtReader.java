package ch.fhnw.project;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TxtReader implements DataReader  {

    @Override
    public DataModel creatingModel(File file) throws FileNotFoundException {

        List<String> names = createVariableNameList(file);

        List<double[]> valueList = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        int length = (Arrays.asList(scanner.nextLine().split("\\t"))).size();

        for(int i=0; i<length;i++){
            valueList.add(createValuesList(file,i));
        }
        return null;

    }


    public List<String> createVariableNameList(File file) throws FileNotFoundException {

        Scanner scanner = new Scanner(file);
        return Arrays.asList(scanner.nextLine().split("\\t"));

    }


    public double[] createValuesList(File file, int index) throws FileNotFoundException {
        List<Double> list = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        int length = (Arrays.asList(scanner.nextLine().split("\\t"))).size(); // überspirngt erste zeile
        while (scanner.hasNext()){
            double string = Double.parseDouble((scanner.nextLine().split("\\t"))[index]);
            list.add(string);
        }
        double[] values = new double[list.size()];

        return null;
    }
}
