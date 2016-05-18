package ch.fhnw.project.gui;


import ch.fhnw.project.model.DataModel;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class MainPane extends StackPane {

    public MainPane(DataModel dataModel){

        ScatterPlotPane scatterPlotPane = new ScatterPlotPane(dataModel.getVariable().get(0), dataModel.getVariable().get(1));
        HistogramPane.setBin(dataModel); // da die Pin bei allen Variablen der gleichen Datei gleich ist --> static --> nur einmal machen
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
