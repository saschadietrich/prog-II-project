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

public class ScatterPlotPane3 extends VBox {

    private StackPane stackPane;
    private LineChart<Number,Number> lineChart;
    private ScatterChart<Number, Number> scatterChart;
    private ScatterPlotControlPane scControlPane = new ScatterPlotControlPane();

    public ScatterPlotPane3(Variable varX, Variable varY) {

        stackPane = new StackPane();

        lineChart = plotLineChart(createDataSeries(varX,varY),varX,varY);  // lineChart = ... muss man gar nicht schreiben weil das liefert die Funktion eig schon

        scatterChart = plotScatterChart(createDataSeries(varX,varY),varX,varY); // siehe oben

        scControlPane.chechBoxLinePlot.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(scControlPane.chechBoxLinePlot.isSelected()){
                lineChart.setVisible(true);
                scatterChart.getXAxis().setVisible(false);
                scatterChart.getYAxis().setVisible(false);
            }
            else {
                lineChart.setVisible(false);
                scatterChart.getXAxis().setVisible(true);
                scatterChart.getYAxis().setVisible(true);
            }
        });
        stackPane.getChildren().addAll(scatterChart,lineChart);
        this.getChildren().addAll(scControlPane, stackPane);
    }

    public void setUp(Variable variableX, Variable variableY) {
        /*
        Gets Variables from ControlPane class
        displays Scatter and LineChart via Checkbox selection (Listener to CheckBox in ScatterPlotControlPane class
        */

        stackPane.getChildren().clear();

        scatterChart = plotScatterChart(createDataSeries(variableX, variableY),variableX,variableY);
        lineChart = plotLineChart(createDataSeries(variableX,variableY),variableX,variableY);

        stackPane.getChildren().addAll(scatterChart,lineChart);
   }

    private XYChart.Series<Number,Number> createDataSeries(Variable varX, Variable varY){
        /*
        Creates DataSeries for Line and Scatter Plot
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

    private ScatterChart <Number,Number> plotScatterChart( XYChart.Series <Number,Number> data, Variable x, Variable y){
        /*
        Creates a ScatterPlot
         */
        NumberAxis xAxis = createXAxis(x);
        NumberAxis yAxis = createYAxis(y);
        scatterChart = new ScatterChart<>(xAxis,yAxis);
        scatterChart.setStyle("-fx-background-color: transparent");
        scatterChart.legendVisibleProperty().set(false);
        scatterChart.getData().add(data);
        return scatterChart;
    }

    private LineChart<Number,Number> plotLineChart(XYChart.Series <Number,Number> data,Variable x, Variable y){
        /*
        Creates a LinePlot
         */
        NumberAxis xAxis= createXAxis(x);
        NumberAxis yAxis = createYAxis(y);
        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.getData().add(data);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.legendVisibleProperty().set(false);
        lineChart.setVisible(false);
        scControlPane.chechBoxLinePlot.setSelected(false);
        return lineChart;
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
