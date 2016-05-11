package ch.fhnw.project.gui;


import ch.fhnw.project.model.DataModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class MainPane extends Pane {

    public MainPane(DataModel dataModel){

        ScatterPlotPane scatterPlotPane = new ScatterPlotPane();
        HistogramPane histogramPane1 = new HistogramPane();
        HistogramPane histogramPane2 = new HistogramPane();
        ControlPane controlPane = new ControlPane(dataModel, scatterPlotPane, histogramPane1, histogramPane2);

        VBox vBox = new VBox();
        HBox hBox = new HBox();

        hBox.getChildren().addAll(histogramPane1,histogramPane2);
        vBox.getChildren().addAll(controlPane,scatterPlotPane,hBox);
        this.getChildren().add(vBox);
    }
}
