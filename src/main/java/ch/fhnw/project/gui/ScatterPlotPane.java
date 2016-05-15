package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;

public class ScatterPlotPane extends VBox {

    private NumberAxis xAxis;
    private NumberAxis yAxis;
    private ScatterChart<Number,Number> scatterChart;
    private LineChart<Number,Number> lineChart;
    private StackPane stackPane = new StackPane();
    ScatterPlotControlPane scControlPane = new ScatterPlotControlPane();

    public ScatterPlotPane() {
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        xAxis.setForceZeroInRange(false);
        yAxis.setForceZeroInRange(false);

    }


    public void setUp(Variable variableX, Variable variableY) {
        //Formation of Axis:
        xAxis.setLabel(variableX.toString());
        yAxis.setLabel(variableY.toString());

        this.getChildren().clear();
        stackPane.getChildren().clear();

        scatterChart = plotScatterChart(createDataSeries(variableX,variableY));
        scatterChart.setStyle("-fx-background-color: transparent");

        scControlPane.cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(scControlPane.cb.isSelected()){
                lineChart = plotLineChart(createDataSeries(variableX,variableY));
                lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
                stackPane.getChildren().addAll(lineChart);
            }
            else {
                stackPane.getChildren().remove(lineChart);  // das Funktioniert nicht so wirklich, entweder löscht es die gemeinsamen Achsen, oder mach ganz komische Sachen wenn ich die Variabeln änder! keine Ahnung wieso!
            }
        });

        stackPane.getChildren().addAll(scatterChart);
        this.getChildren().addAll(scControlPane, stackPane);

   }

    private XYChart.Series<Number,Number> createDataSeries(Variable varX, Variable varY){
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

    private ScatterChart<Number,Number> plotScatterChart( XYChart.Series <Number,Number> data){
        /*NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();*/
        ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis,yAxis);
        sc.getData().add(data);
        return sc;
    }

    private LineChart<Number,Number> plotLineChart(XYChart.Series <Number,Number> data){
        /*NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();*/
        LineChart<Number,Number> lc = new LineChart<>(xAxis,yAxis);
        lc.getData().add(data);
        return lc;
    }

}
