package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.List;

public class ScatterPlotPane extends StackPane {

    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ScatterChart<Number,Number> scatterChart;
    private XYChart.Series <Number,Number> dataSeries;

    private ScatterPlotControlPane scControlPane;
    private VBox vbox;

    public ScatterPlotPane() {
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        scatterChart = new ScatterChart<>(xAxis,yAxis);
        dataSeries = new XYChart.Series<>();
        scControlPane = new ScatterPlotControlPane();
        vbox = new VBox();

        vbox.getChildren().addAll(scControlPane,scatterChart);
        this.getChildren().addAll(vbox);
    }


    public void createDataSeries(Variable variableX, Variable variableY) {

       //Formation of Axis:
       xAxis.setForceZeroInRange(false);
       yAxis.setForceZeroInRange(false);
       xAxis.setLabel(variableX.toString());
       yAxis.setLabel(variableY.toString());

       //Resets previous Dataset, otherwise nasty erros
       dataSeries.getData().clear();
       scatterChart.getData().clear();

       //Value Lists:
       List<Double> xValues = variableX.getValues();
       List<Double> yValues = variableY.getValues();

       //Creates Data points, with sizable Circles
       for (int i = 0; i < xValues.size(); i++) {   // Problem --> Was ist wenn X und Y nicht gleich gross sind!
           XYChart.Data<Number,Number> dataPoint = new XYChart.Data<>(xValues.get(i),yValues.get(i));
           Circle circle = new Circle();
           circle.setRadius(5);
           circle.setFill(Color.BLACK);
           dataPoint.setNode(circle);
           dataSeries.getData().add(dataPoint);
       }
       createPlot(dataSeries);
   }

    private void createPlot(XYChart.Series<Number,Number> data){

        scatterChart.getData().add(data);
        //this.getChildren().clear();
        //vbox.getChildren().clear();
    }


}
