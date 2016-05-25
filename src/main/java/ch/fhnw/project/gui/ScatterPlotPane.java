package ch.fhnw.project.gui;

import ch.fhnw.project.model.Variable;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;


public class ScatterPlotPane extends VBox {
    private StackPane stackPane;
    private Variable variableX = null;
    private Variable variableY = null;
    private LineChart<Number,Number> lineChart;
    private ScatterChart<Number, Number> scatterChart;
    private ScatterChart<Number, Number> scatterChartBubble;
    private ScatterPlotControlPane scControlPane = new ScatterPlotControlPane();


    public ScatterPlotPane( List<Variable> varZ) {

        stackPane = new StackPane();
        variableY =varZ.get(0);
        variableX =varZ.get(1);

        plotLineChart(createDataSeries(variableX,variableY));  // lineChart = ... muss man gar nicht schreiben weil das liefert die Funktion eig schon
        plotScatterChart(createDataSeries(variableX,variableY)); // siehe oben

        scatterChartBubble = new ScatterChart<>(createXAxis(variableX),createYAxis(variableY));

        for (Variable var : varZ) {
            scControlPane.comboBoxBubblePlt.getItems().add(var);
        }

        scControlPane.cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(scControlPane.cb.isSelected()){
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

        scControlPane.cb2.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue) {
                scControlPane.cb.setSelected(false);
                scControlPane.comboBoxBubblePlt.setDisable(false);
                scControlPane.cb.setDisable(true);


                scControlPane.comboBoxBubblePlt.valueProperty().addListener((observable1, oldValue1, newValue1) -> {
                    scatterChartBubble.getData().clear();
                    scatterChartBubble.setVisible(true);
                    scControlPane.slider.setValue(2.0);
                    scatterChartBubble = plotBubbleChart(newValue1);
                    scatterChart.getXAxis().setVisible(false);
                    scatterChart.getYAxis().setVisible(false);

                });
            }
            else{
                scControlPane.comboBoxBubblePlt.setDisable(true);
                scControlPane.cb.setDisable(false);
                scatterChartBubble.setVisible(false);
                scatterChart.getXAxis().setVisible(true);
                scatterChart.getYAxis().setVisible(true);
            }
        });
        stackPane.getChildren().addAll(scatterChartBubble,scatterChart,lineChart);
        this.getChildren().addAll(scControlPane, stackPane);
    }

    public void setUp(Variable varX, Variable varY) {
        /*
        Gets Variables from ControlPane class
        displays Scatter and LineChart via Checkbox selection (Listener to CheckBox in ScatterPlotControlPane class
        */

        variableX = varX;
        variableY = varY;

        stackPane.getChildren().clear();
        scControlPane.cb2.setSelected(false);
        scatterChart = plotScatterChart(createDataSeries(varX, varY));
        lineChart = plotLineChart(createDataSeries(varX,varY));
        scatterChartBubble = new ScatterChart<>(createXAxis(varX),createYAxis(varY));

        scatterChartBubble.setVisible(false);

        stackPane.getChildren().addAll(scatterChart,lineChart,scatterChartBubble);
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

    private ScatterChart <Number,Number> plotScatterChart( XYChart.Series <Number,Number> data){
        /*
        Creates a ScatterPlot
         */
        NumberAxis xAxis = createXAxis(variableX);
        NumberAxis yAxis = createYAxis(variableY);
        scatterChart = new ScatterChart<>(xAxis,yAxis);
        scatterChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        scatterChart.legendVisibleProperty().set(false);
        scatterChart.getData().add(data);
        return scatterChart;
    }

    private LineChart<Number,Number> plotLineChart(XYChart.Series <Number,Number> data){
        /*
        Creates a LinePlot
         */
        NumberAxis xAxis= createXAxis(variableX);
        NumberAxis yAxis = createYAxis(variableY);
        lineChart = new LineChart<>(xAxis,yAxis);
        lineChart.getData().add(data);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.legendVisibleProperty().set(false);
        lineChart.setVisible(false);
        scControlPane.cb.setSelected(false);
        return lineChart;
    }

    private ScatterChart<Number,Number> plotBubbleChart( Variable z){

        XYChart.Series <Number,Number> dataSeries = new XYChart.Series<>();

        for (int i = 0; i < variableX.getValues().size(); i++) {   // Problem --> Was ist wenn X und Y nicht gleich gross sind!
            XYChart.Data<Number,Number> dataPoint = new XYChart.Data<>(variableX.getValues().get(i),variableY.getValues().get(i),z.getValues().get(i));
            Circle circle = new Circle();
            circle.setRadius(z.getValues().get(i)*0.1);
            scControlPane.slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                circle.setRadius(newValue.doubleValue()/oldValue.doubleValue()*circle.getRadius());
            });
            circle.fillProperty().bind(scControlPane.colorPicker.valueProperty());
            dataPoint.setNode(circle);
            dataSeries.getData().add(dataPoint);
        }
        scatterChartBubble.getData().add(dataSeries);
        scatterChartBubble.setLegendVisible(false);

        return scatterChartBubble;
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
