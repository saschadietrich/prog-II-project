package ch.fhnw.project.gui;

import ch.fhnw.project.model.Variable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.layout.Pane;

import java.util.List;

public class HistogramPane extends Pane{

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);

    private String name;
    private int numberOfPins; // da es nur einmal gibt
    private List<Double> values;

    public HistogramPane(Variable variable){
        barChart.setBarGap(0);
        barChart.setCategoryGap(0);
        this.getChildren().add(barChart);//nummer of bins müssen nur 1mal berechnet werden!!! muee hier rein
        numberOfPins = (int)Math.round(Math.sqrt(variable.getValues().size()));


    }


    public void change(Variable variable){
        barChart.setTitle(variable.toString());
        numberOfPins(variable);
        //System.out.println(numberOfPins);

    }

    private void numberOfPins(Variable variable){
        numberOfPins = (int)Math.round(Math.sqrt(variable.getValues().size()));
    }

    private void biuldGroup(Variable variable){

    }
}
