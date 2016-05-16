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

    private LineChart<Number,Number> lineChart;
    private ScatterPlotControlPane scControlPane = new ScatterPlotControlPane();

   /* public ScatterPlotPane() {

    }*/

    public void setUp(Variable variableX, Variable variableY) {
        /*
        * Gets Variables from ControlPane class
        * displays Scatter and LineChart via Checkbox selection (Listener to CheckBox in ScatterPlotControlPane class
        * */
        StackPane stackPane = new StackPane();
        StackPane stackpane2 = new StackPane(); // Zusätzlich hinzugefügt um LinienChart besser löschen zu können, bekomme jetzt Children: duplicate children added: parent = StackPane@4e03025d Fehler

        this.getChildren().clear();
        stackPane.getChildren().clear();

        ScatterChart<Number, Number> scatterChart = plotScatterChart(createDataSeries(variableX, variableY),variableX,variableY);
        scatterChart.setStyle("-fx-background-color: transparent");
        scatterChart.legendVisibleProperty().set(false);  //  schaltet Legende aus, verhindert die komische Achsenverschiebung wenn man Panes aufeinander legt

        scControlPane.cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(scControlPane.cb.isSelected()){
                lineChart = plotLineChart(createDataSeries(variableX,variableY),variableX,variableY);
                lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
                lineChart.legendVisibleProperty().set(false);
                stackpane2.getChildren().add(lineChart);  //denke ich nicht optimal
                stackPane.getChildren().add(stackpane2);
            }
            else {
                stackpane2.getChildren().clear();
            }
        });

        stackPane.getChildren().add(scatterChart);
        this.getChildren().addAll(scControlPane, stackPane);
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

    private ScatterChart<Number,Number> plotScatterChart( XYChart.Series <Number,Number> data, Variable x, Variable y){
        /*
        Creates a ScatterPlot and returns it
         */
        NumberAxis xAxis = createXAxis(x);
        NumberAxis yAxis = createYAxis(y);
        ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis,yAxis);
        sc.getData().add(data);
        return sc;
    }

    private LineChart<Number,Number> plotLineChart(XYChart.Series <Number,Number> data,Variable x, Variable y){
        /*
        Creates a LinePlot and returns it
         */
        NumberAxis xAxis = createXAxis(x);
        NumberAxis yAxis = createYAxis(y);
        LineChart<Number,Number> lc = new LineChart<>(xAxis,yAxis);
        lc.getData().add(data);
        return lc;
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
