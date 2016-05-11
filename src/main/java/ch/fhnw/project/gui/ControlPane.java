package ch.fhnw.project.gui;


import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class ControlPane extends HBox {
    private DataModel dataModel;
    private ScatterPlotPane scatterPlotPane;
    private HistogramPane histogramPane1, histogramPane2;

    public ControlPane(DataModel dataModel, ScatterPlotPane scatterPlotPane, HistogramPane histogramPane1, HistogramPane histogramPane2){
        this.dataModel = dataModel;
        this.scatterPlotPane = scatterPlotPane;
        this.histogramPane1 = histogramPane1;
        this.histogramPane2 = histogramPane2;

        ComboBox<Variable> xChooser = new ComboBox<>();
        ComboBox<Variable> yChooser = new ComboBox<>();

        for (Variable variable : dataModel.getVariable()) {
            xChooser.getItems().add(variable);
            yChooser.getItems().add(variable);
        }

        xChooser.setValue(dataModel.getVariable().get(0));
        yChooser.setValue(dataModel.getVariable().get(1));
        setNewValue(xChooser.getValue(),true);// muss noch mit start funktion ersetzt werden
        setNewValue(yChooser.getValue(),false);

        xChooser.valueProperty().addListener((observable, oldValue, newValue) -> {
            setNewValue(newValue,true);
        });

        yChooser.valueProperty().addListener((observable, oldValue, newValue) -> {
            setNewValue(newValue,false);
        });

        this.getChildren().addAll(xChooser,yChooser);
    }

    private void setNewValue(Variable newVariable, boolean xValue){

        if (xValue){
            scatterPlotPane.setXVariable(newVariable);
            histogramPane1.change(newVariable);
        }else{
            scatterPlotPane.setYVariable(newVariable);
            histogramPane2.change(newVariable);
        }


    }

}
