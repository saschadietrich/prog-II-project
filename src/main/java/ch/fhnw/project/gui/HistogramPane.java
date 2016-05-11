package ch.fhnw.project.gui;

import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;


public class HistogramPane extends Pane{

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);

    private static int numberOfPins;
    private double min, max;

    public HistogramPane(){
        barChart.setBarGap(0);
        barChart.setCategoryGap(0);
        this.getChildren().add(barChart);


    }


    public void change(Variable variable){
        barChart.setTitle(variable.toString());
        //System.out.println(numberOfPins);
        //System.out.println(variable.getMax());
        //System.out.println(variable.getMin());


    }

    private void biuldGroup(Variable variable){

    }

    private void stepsize(){

    }

    private void setMinMax(Variable variable){

    }
    public static void setPin(DataModel dataModel){
        numberOfPins = (int)Math.round(Math.sqrt(dataModel.getVariable().get(0).getValues().size()));
    }
}
