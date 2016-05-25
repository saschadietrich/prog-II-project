package ch.fhnw.project.gui;

import ch.fhnw.project.model.Variable;
import javafx.scene.chart.*;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.util.List;

public class ScatterPlotPane extends VBox {
    private StackPane stackPane;
    private Variable variableX ;
    private Variable variableY;
    private Variable variableZ;
    private LineChart<Number,Number> lineChart;
    private ScatterChart<Number, Number> scatterChart;
    private ScatterChart<Number, Number> scatterChartBubble;
    private ScatterPlotControlPane scControlPane;

    public ScatterPlotPane( List<Variable> variables) {

        stackPane = new StackPane();
        variableY =variables.get(1);
        variableX =variables.get(0);
        variableZ =variables.get(0);

        scControlPane = new ScatterPlotControlPane();
        plotLineChart(createDataSeries());
        plotScatterChart(createDataSeries());
        scatterChartBubble = new ScatterChart<>(createAxis(variableX),createAxis(variableY));

        for (Variable var : variables) {
            scControlPane.comboBoxBubblePlt.getItems().add(var);
        }
        scControlPane.comboBoxBubblePlt.setValue(variables.get(0));

        scControlPane.chechBoxLinePlot.setOnAction(e -> {
            if(scControlPane.chechBoxLinePlot.isSelected()){
                lineChart.setVisible(true);
                scatterChart.getXAxis().setVisible(false);
                scatterChart.getYAxis().setVisible(false);
            } else {
                lineChart.setVisible(false);
                scatterChart.getXAxis().setVisible(true);
                scatterChart.getYAxis().setVisible(true);
            }
        });

        scControlPane.checkBoxBubblePlot.setOnAction(event -> {
            if(scControlPane.checkBoxBubblePlot.isSelected()) {
                variableZ = scControlPane.comboBoxBubblePlt.getValue();
                plotBubbleChart();
            } else{
                scControlPane.comboBoxBubblePlt.setDisable(true);
                scControlPane.chechBoxLinePlot.setDisable(false);
                scatterChartBubble.setVisible(false);
                scatterChart.getXAxis().setVisible(true);
                scatterChart.getYAxis().setVisible(true);
            }
        });
        scControlPane.comboBoxBubblePlt.valueProperty().addListener((observable, oldValue, newValue) -> {
            variableZ = newValue;
            plotBubbleChart();
        });

        stackPane.getChildren().addAll(scatterChart,lineChart,scatterChartBubble);
        this.getChildren().addAll(scControlPane, stackPane);
    }

    public void setUp(Variable varX, Variable varY) {
        /*
        Gets Variables from ControlPane class
        Changes Scatter-, Line- and BubblePlot to Variables changed in ControlPane class
        */

        variableX = varX;
        variableY = varY;

        stackPane.getChildren().clear();
        plotScatterChart(createDataSeries());
        plotLineChart(createDataSeries());
        scatterChartBubble = new ScatterChart<>(createAxis(varX),createAxis(varY));
        scatterChartBubble.setVisible(false);

        if(scControlPane.checkBoxBubblePlot.isSelected()){
            plotBubbleChart();
        }

        stackPane.getChildren().addAll(scatterChart,lineChart,scatterChartBubble);
    }

    private XYChart.Series<Number,Number> createDataSeries(){
        /*
        Creates DataSeries for Line and Scatter Plot
         */
        XYChart.Series <Number,Number> dataSeries = new XYChart.Series<>();
        List<Double> xValues = variableX.getValues();
        List<Double> yValues = variableY.getValues();

        for (int i = 0; i < xValues.size(); i++) {
            XYChart.Data<Number,Number> dataPoint = new XYChart.Data<>(xValues.get(i),yValues.get(i));
            Circle circle = new Circle();
            circle.radiusProperty().bind(scControlPane.slider.valueProperty());
            circle.fillProperty().bind(scControlPane.colorPicker.valueProperty());
            dataPoint.setNode(circle);
            dataSeries.getData().add(dataPoint);
        }
        return dataSeries;
    }

    private void plotScatterChart( XYChart.Series <Number,Number> data){
        /*
        Creates a ScatterPlot
         */
        scatterChart = new ScatterChart<>(createAxis(variableX),createAxis(variableY));
        scatterChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent");
        scatterChart.legendVisibleProperty().set(false);
        scatterChart.getData().add(data);
        scatterChart.getXAxis().setVisible(false);
        scatterChart.getYAxis().setVisible(false);
    }

    private void plotLineChart(XYChart.Series <Number,Number> data){
        /*
        Creates a LinePlot
         */
        lineChart = new LineChart<>(createAxis(variableX),createAxis(variableY));
        lineChart.getData().add(data);
        lineChart.setAxisSortingPolicy(LineChart.SortingPolicy.NONE);
        lineChart.legendVisibleProperty().set(false);
        lineChart.setVisible(false);
        scControlPane.chechBoxLinePlot.setSelected(false);
        lineChart.getData().get(0).getNode().setStyle("-fx-stroke:  blue ;");
    }

    private void plotBubbleChart(){
        /*
        Creates Data for BubbleChart and then creates BubblePlot and setting its conditions
         */
        scControlPane.chechBoxLinePlot.setSelected(false);
        scControlPane.comboBoxBubblePlt.setDisable(false);
        scControlPane.chechBoxLinePlot.setDisable(true);
        scatterChartBubble.getData().clear();
        scatterChartBubble.setVisible(true);
        scControlPane.slider.setValue(2.0);
        scatterChart.getXAxis().setVisible(false);
        scatterChart.getYAxis().setVisible(false);

        XYChart.Series <Number,Number> dataSeries = new XYChart.Series<>();

        for (int i = 0; i < variableX.getValues().size(); i++) {
            XYChart.Data<Number,Number> dataPoint = new XYChart.Data<>(variableX.getValues().get(i),variableY.getValues().get(i),variableZ.getValues().get(i));
            Circle circle = new Circle();
            circle.setRadius(variableZ.getValues().get(i)*0.1);
            scControlPane.slider.valueProperty().addListener((observable, oldValue, newValue) -> {
                circle.setRadius(newValue.doubleValue()/oldValue.doubleValue()*circle.getRadius());
            });
            circle.fillProperty().bind(scControlPane.colorPicker.valueProperty());
            dataPoint.setNode(circle);
            dataSeries.getData().add(dataPoint);
        }
        scatterChartBubble.getData().add(dataSeries);
        scatterChartBubble.setLegendVisible(false);
    }

    private NumberAxis createAxis(Variable var){
        /*
        creates x- or y- axis
         */
        NumberAxis axis= new NumberAxis();
        axis.setLabel(var.toString());
        axis.setForceZeroInRange(false);
        return axis;
    }
}
