package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;

public class ScatterPlotPane extends VBox {

    private StackPane stackPane;
    private LineChart<Number,Number> lineChart;
    private ScatterChart<Number, Number> scatterChart;
   // private NumberAxis xAxis,yAxis;
    private ScatterPlotControlPane scControlPane = new ScatterPlotControlPane();

    public ScatterPlotPane(Variable varX, Variable varY) {


        stackPane = new StackPane();
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel(varX.toString());
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel(varY.toString());

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setVisible(false);
        lineChart.legendVisibleProperty().set(false);

        scatterChart = new ScatterChart<>(xAxis, yAxis);
        scatterChart.setStyle("-fx-background-color: transparent");
        scatterChart.legendVisibleProperty().set(false);
        //lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);

        scControlPane.cb.setSelected(false);
        scControlPane.cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(scControlPane.cb.isSelected()){
                lineChart.setVisible(true);
                lineChart.legendVisibleProperty().set(false);
            }
            else {
                lineChart.setVisible(false);
            }lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        });

        stackPane.getChildren().addAll(scatterChart,lineChart);
        this.getChildren().addAll(scControlPane, stackPane);

    }

    public void setUp(Variable variableX, Variable variableY) {
        /*
        * Gets Variables from ControlPane class
        * displays Scatter and LineChart via Checkbox selection (Listener to CheckBox in ScatterPlotControlPane class
        * */
        //StackPane stackPane = new StackPane();


        //scatterChart.getData().clear();
        //lineChart.getData().clear();
        //this.getChildren().clear();
        stackPane.getChildren().clear();
        plotScatterChart(createDataSeries(variableX, variableY),variableX,variableY);
        plotLineChart(createDataSeries(variableX,variableY),variableX,variableY);
       // lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        //lineChart.setVisible(false);
       // scatterChart.setStyle("-fx-background-color: transparent");
       // scatterChart.legendVisibleProperty().set(false);

       /* scControlPane.cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(scControlPane.cb.isSelected()){
                lineChart.setVisible(true);
                lineChart.legendVisibleProperty().set(false);
            }
            else {
                lineChart.setVisible(false);
            }
        })*/;

        stackPane.getChildren().addAll(scatterChart,lineChart);
        //this.getChildren().addAll(scControlPane, stackPane);
   }

    private XYChart.Series<Number,Number> createDataSeries(Variable varX, Variable varY){
        /*
        Creates DataSeries for Line and Scatter Plot and returns it
         */
        XYChart.Series <Number,Number> dataSeries = new XYChart.Series<>();

        List<Double> xValues = varX.getValues();
        List<Double> yValues = varY.getValues();

        for (int i = 0; i < xValues.size(); i++) {   // Problem --> Was ist wenn X und Y nicht gleich gross sind!
            XYChart.Data<Number,Number> dataPoint = new XYChart.Data<>(xValues.get(i),yValues.get(i));
            Circle circle = new Circle();
            circle.radiusProperty().bind(scControlPane.slider.valueProperty());
            circle.fillProperty().bind(scControlPane.colorPicker.valueProperty());
            dataPoint.setNode(circle);
            dataSeries.getData().add(dataPoint);
        }
        return dataSeries;
    }

    private void plotScatterChart( XYChart.Series <Number,Number> data, Variable x, Variable y){
        /*
        Creates a ScatterPlot and returns it
         */
        NumberAxis xAxis = createXAxis(x);
        NumberAxis yAxis = createYAxis(y);
        scatterChart.getData().clear();
        scatterChart.getData().add(data);
        scatterChart.legendVisibleProperty().set(false);
    }

    private void /*LineChart<Number,Number>*/ plotLineChart(XYChart.Series <Number,Number> data,Variable x, Variable y){
        /*
        Creates a LinePlot and returns it
         */
        NumberAxis xAxis= createXAxis(x);
        NumberAxis yAxis = createYAxis(y);
        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.getData().clear();
        lineChart.getData().add(data);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.legendVisibleProperty().set(false);
        lineChart.setVisible(false);
        //return lc;
    }

    private NumberAxis createXAxis(Variable varX){
        /*
        creates xAxis and returns it
         */
        NumberAxis xAxis= new NumberAxis();
        xAxis.setLabel(varX.toString());
        xAxis.setForceZeroInRange(false);

        return xAxis;
    }

    private NumberAxis createYAxis(Variable varY){
        /*
        Creates yAxis and returns it
         */
        NumberAxis yAxis= new NumberAxis();
        yAxis.setLabel(varY.toString());
        yAxis.setForceZeroInRange(false);

        return yAxis;
    }

}
