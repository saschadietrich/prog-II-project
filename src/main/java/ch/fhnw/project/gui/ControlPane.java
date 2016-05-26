package ch.fhnw.project.gui;


import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public final class ControlPane extends HBox {
    private ScatterPlotPane scatterPlotPane;
    private HistogramPane histogramPane1, histogramPane2;
    private Variable variableX;
    private Variable variableY;
    private ComboBox<Variable> xChooser = new ComboBox<>();
    private ComboBox<Variable> yChooser = new ComboBox<>();

    public ControlPane(DataModel dataModel, ScatterPlotPane scatterPlotPane, HistogramPane histogramPane1, HistogramPane histogramPane2){
        this.scatterPlotPane = scatterPlotPane;
        this.histogramPane1 = histogramPane1;
        this.histogramPane2 = histogramPane2;
        Label xLabel = new Label("x-Axis");
        Label yLabel = new Label("y-Axis");

        for (Variable variable : dataModel.getVariable()) {
            xChooser.getItems().add(variable);
            yChooser.getItems().add(variable);
        }

        setComboBoxToDefault(dataModel.getVariable().get(0), dataModel.getVariable().get(1));

        xChooser.valueProperty().addListener((observable, oldValue, newValue) -> {
            setNewValueHistogram(newValue,true);
            variableX = newValue;
            scatterPlotPane.setUp(variableX,variableY);
        });

        yChooser.valueProperty().addListener((observable, oldValue, newValue) -> {
            setNewValueHistogram(newValue,false);
            variableY = newValue;
            scatterPlotPane.setUp(variableX,variableY);
        });

        this.setPadding(new Insets(5,5,5,5));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.getChildren().addAll(xLabel,xChooser,yLabel,yChooser);
    }

    private void setComboBoxToDefault(Variable x, Variable y){
        xChooser.setValue(x);
        yChooser.setValue(y);
        variableX = x;
        variableY = y;
        setNewValueHistogram(xChooser.getValue(),true);
        setNewValueHistogram(yChooser.getValue(), false);
        scatterPlotPane.setUp(variableX,variableY);
    }
    
    private void setNewValueHistogram(Variable newVariable, boolean xValue){
        if (xValue){
            histogramPane1.change(newVariable);
        }else{
            histogramPane2.change(newVariable);
        }
    }
}
