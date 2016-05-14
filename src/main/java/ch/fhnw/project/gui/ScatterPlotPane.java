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

    private NumberAxis xAxis = new NumberAxis();
    private NumberAxis yAxis = new NumberAxis();
    private ScatterChart<Number,Number> scatterChart = new ScatterChart<>(xAxis,yAxis);
    private XYChart.Series <Number,Number> dataSeries = new XYChart.Series<>();

    //Eigene Klasse für das ControlPanel für den Scatter Plot erstellen!
    VBox vbox = new VBox();
    HBox scatterBox = new HBox();
    HBox scatterPanelBox = new HBox();

    Slider slider = new Slider();
    ToggleGroup toggleGroup = new ToggleGroup();
    RadioButton scatterRB = new RadioButton("Scatter Plot");
    RadioButton lineRB = new RadioButton("Line Plot");
    RadioButton bubbleRB = new RadioButton("Bubble Plot");


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

        this.getChildren().clear();

        //Eigene Klasse für ScatterPanel erstellen!
        scatterRB.setToggleGroup(toggleGroup);
        lineRB.setToggleGroup(toggleGroup);
        bubbleRB.setToggleGroup(toggleGroup);
        scatterRB.setSelected(true);

        scatterPanelBox.getChildren().addAll(slider,scatterRB,lineRB,bubbleRB);
        scatterBox.getChildren().add(scatterChart);
        vbox.getChildren().addAll(scatterPanelBox,scatterBox);  //Vbox ist zu Klein noch anpassen!!
        this.getChildren().addAll(vbox);


    }


}
