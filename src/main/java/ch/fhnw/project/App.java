package ch.fhnw.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.function.Supplier;

public final class App extends Application {

    @Override
    public void start(Stage stage) {

        // Graphenfenster
        NumberAxis xAxis = new NumberAxis(-10, 10, 1);
        NumberAxis yAxis = new NumberAxis(-10, 10, 1);
        ScatterChart<Number, Number> sc = new ScatterChart<>(xAxis, yAxis);

        CategoryAxis bins = new CategoryAxis();
        NumberAxis frequency = new NumberAxis();
        BarChart<String, Number> histogrammX = new BarChart<>(bins, frequency);

        CategoryAxis bins2 = new CategoryAxis();
        NumberAxis frequency2 = new NumberAxis();
        BarChart<String, Number> histogrammy = new BarChart<>(bins2, frequency2);

        //knpfe
        Button bu1 = new Button("Hello World");

        XYChart.Series data = new XYChart.Series();


        GridPane controlPane = new GridPane(); // für knöpfe
        controlPane.getChildren().add(bu1);

        HBox histogrammBox = new HBox();
        histogrammBox.getChildren().addAll(histogrammX,histogrammy);

        VBox graphBox = new VBox(); // kommt Plot und Histogrammbox rein
        graphBox.getChildren().addAll(sc,histogrammBox);

        HBox mainBox = new HBox();
        mainBox.getChildren().addAll(controlPane,graphBox);

        Pane pane = new Pane();
        pane.getChildren().add(mainBox);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Testversion 0.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
