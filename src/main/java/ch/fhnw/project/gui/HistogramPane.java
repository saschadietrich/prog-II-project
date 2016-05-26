package ch.fhnw.project.gui;

import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public final class HistogramPane extends StackPane{

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
    private static int numberOfBin;

    public HistogramPane(){
        barChart.setBarGap(0);
        barChart.setCategoryGap(0);
        barChart.setLegendVisible(false);
        barChart.setTitleSide(Side.BOTTOM);
        this.getChildren().add(barChart);
        xAxis.setTickLabelsVisible(false);
    }

    protected void change(Variable variable){
        /*
        Set new histogram
         */
        barChart.setTitle(variable.toString());
        plot(buildGroup(variable), getBinNames(variable));
    }

    protected int[] buildGroup(Variable variable){
        /*
        Create a List with the size of the bins and return it
         */
        double diff = variable.getMax()-variable.getMin();
        int[] bin = new int[numberOfBin];
        List<Double> list = variable.getValues();

        for(int i = 0; i< numberOfBin; i++){
            bin[i]=0;
        }

        for (Double aDouble : list) {
            int value = (int)(((aDouble-variable.getMin())/diff) * (numberOfBin));
            int index = Math.min(value,numberOfBin-1);
            bin[index]++;
        }

        return bin;
    }

    private double getStepSize(Variable variable) {
        /*
        Calculate and return the size of the bins
         */
        return (variable.getMax()-variable.getMin())/ numberOfBin;
    }

    private void plot(int[] pins,List<String> pinName){
        /*
        Delete and create the new histogram
         */
        barChart.getData().clear();
        XYChart.Series<String, Number> dataPoints = new XYChart.Series<>();

        for (int i = 0; i< numberOfBin; i++){
            dataPoints.getData().add(new XYChart.Data<>(pinName.get(i),pins[i]));
        }
        barChart.getData().add(dataPoints);

        for (int i =0; i<numberOfBin;i++) {
            barChart.getData().get(0).getData().get(i).getNode().setStyle("-fx-background-color: blue,blue;");
        }
    }

    private List<String> getBinNames(Variable variable){
        /*
        Returns a list with the name for the bins
         */
        double step = getStepSize(variable);
        List<String> names = new ArrayList<>();

        for(int i = 1; i<= numberOfBin; i++){
            names.add(format((variable.getMin()+step*i)/2));
        }
        return names;
    }

    private String format(double input) {
        /*
        Format the text for xAxis
         */
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double toFormat = ((double)Math.round(input*100))/100;
        return decimalFormat.format(toFormat);
    }

    protected static void setBin(DataModel dataModel){
        /*
        Calculate the number of bins
         */
        numberOfBin = (int)(Math.sqrt(dataModel.getVariable().get(0).getValues().size()));
    }
}
