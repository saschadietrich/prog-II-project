package ch.fhnw.project.gui;

import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class HistogramPane extends Pane{

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
    private static int numberOfBin;

    public HistogramPane(){
        barChart.setBarGap(0);
        barChart.setCategoryGap(0);
        barChart.setLegendVisible(false);

        this.getChildren().add(barChart);
    }

    public void change(Variable variable){
        barChart.setTitle(variable.toString());
        
        plot(biuldGroup(variable), getBinNames(variable));

    }

    private int[] biuldGroup(Variable variable){

        double step = getStepSize(variable);
        int[] pins = new int[numberOfBin];

        for(int i = 0; i< numberOfBin; i++){
            pins[i]=0;
        }
        for (Double aDouble : variable.getValues()) {
            for (int i = 1; i<= numberOfBin; i++){
                if (aDouble<=(variable.getMin()+step*i)){
                    pins[i-1]++;
                    break;
                }
            }
        }

//        System.out.println("Pins: " +numberOfBins);
//        System.out.println("Step: "+ step);
//        System.out.println("Pins: " +numberOfBins);
//        System.out.println("Step: "+ step);
//        int total=0;
//        for (int pin : pins) {
//            total += pin;
//            System.out.println(pin);
//        }
//        System.out.println("Size: "+ variable.getValues().size() + " Sum of Pinssize: "+ total);

        return pins;
    }

    private double getStepSize(Variable variable) {
        return (variable.getMax()-variable.getMin())/ numberOfBin;
    }

    private void plot(int[] pins,List<String> pinName){


        barChart.getData().clear();
        XYChart.Series<String, Number> dataPoints = new XYChart.Series<>();

        for (int i = 0; i< numberOfBin; i++){
            dataPoints.getData().add(new XYChart.Data<>(pinName.get(i),pins[i]));
        }
        barChart.getData().add(dataPoints);
    }

    private List<String> getBinNames(Variable variable){

        double step = getStepSize(variable);
        List<String> names = new ArrayList<>();

        for(int i = 1; i<= numberOfBin; i++){

            names.add(format((variable.getMin()+step*i)/2));
        }
        return names;
    }

    private String format(double input) {
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double toFormat = ((double)Math.round(input*100))/100;
        return decimalFormat.format(toFormat);
    }

    public static void setBin(DataModel dataModel){
        numberOfBin = (int)Math.round(Math.sqrt(dataModel.getVariable().get(0).getValues().size()));
    }
}
