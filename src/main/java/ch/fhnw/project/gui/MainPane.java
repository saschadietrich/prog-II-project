package ch.fhnw.project.gui;

import ch.fhnw.project.model.DataModel;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

final class MainPane extends StackPane {
    MainPane(DataModel dataModel){
        ScatterPlotPane scatterPlotPane = new ScatterPlotPane(dataModel.getVariable());
        HistogramPane.setBin(dataModel);
        HistogramPane histogramPane1 = new HistogramPane();
        HistogramPane histogramPane2 = new HistogramPane();
        ControlPane controlPane = new ControlPane(dataModel, scatterPlotPane, histogramPane1, histogramPane2);
        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.getChildren().addAll(histogramPane1,histogramPane2);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(controlPane,scatterPlotPane,hBox);
        this.getChildren().add(vBox);
    }
}
