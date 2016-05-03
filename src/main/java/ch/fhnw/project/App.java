package ch.fhnw.project;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

        //knöpfe und änliches
        Label labelComboBoxX = new Label("x-Achse");
        Label labelComboBoxY = new Label("y-Achse");
        Label labelslider = new Label("Pointsize");
        Label labelBubbel = new Label("Bubble Chart");
        Label labelRB = new Label("Choose Chartversion");
        ComboBox<String> xChooser = new ComboBox<>();
        ComboBox<String> yChooser = new ComboBox<>();
        ComboBox<String> bubbleChooser = new ComboBox<>();
        Slider slider = new Slider();
        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton scatterRB = new RadioButton("Scatter Plot");
        RadioButton lineRB = new RadioButton("Line Plot");
        RadioButton bubbleRB = new RadioButton("Bubble Plot");
        scatterRB.setToggleGroup(toggleGroup);
        lineRB.setToggleGroup(toggleGroup);
        bubbleRB.setToggleGroup(toggleGroup);
        scatterRB.setSelected(true);

        GridPane controlPane = new GridPane(); // für knöpfe
        controlPane.add(labelComboBoxX,0,0);
        controlPane.add(xChooser,0,1);
        controlPane.add(labelComboBoxY,0,2);
        controlPane.add(yChooser,0,3);
        controlPane.add(labelslider,0,4);
        controlPane.add(slider,0,5);
        controlPane.add(labelBubbel,0,6);
        controlPane.add(bubbleChooser,0,7);
        controlPane.add(labelRB,0,8);
        controlPane.add(scatterRB,0,9);
        controlPane.add(lineRB,0,10);
        controlPane.add(bubbleRB,0,11);
        controlPane.setStyle("-fx-background-color: pink;");
        controlPane.setPrefWidth(150);

        StackPane graphPane = new StackPane(); // für die 3 charts
        graphPane.getChildren().add(sc);

        HBox histogrammBox = new HBox(); //histogramm box
        histogrammBox.getChildren().addAll(histogrammX,histogrammy);
        histogrammBox.setStyle("-fx-background-color: orange;");
        histogrammBox.setAlignment(Pos.CENTER);

        VBox graphBox = new VBox(); // kommt Plot und Histogrammbox rein
        graphBox.getChildren().addAll(graphPane,histogrammBox);
        graphBox.setStyle("-fx-background-color: blue;");

        HBox mainBox = new HBox();
        mainBox.getChildren().addAll(controlPane,graphBox);
        mainBox.setStyle("-fx-background-color: green;");
        mainBox.setPrefSize(500,500);

        Pane pane = new Pane();
        pane.getChildren().add(mainBox);
        Scene scene = new Scene(pane, 500,500,Color.VIOLET);
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //mainBox.setPrefWidth(newSize(newValue,oldValue,mainBox.getWidth()));
                mainBox.setPrefWidth((double)newValue);
                graphBox.setPrefWidth((double)newValue-150);
                histogrammBox.setPrefWidth((double)newValue);
                graphPane.setPrefWidth((double)newValue);
            }
        });
        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                //mainBox.setPrefHeight(newSize(newValue,oldValue,mainBox.getHeight()));
                mainBox.setPrefHeight((double)newValue);
                //graphBox.setPrefHeight((double)newValue);
                histogrammBox.setPrefHeight(((double)newValue)*0.4);
                graphPane.setPrefHeight(((double)newValue)*0.6);
            }
        });
        stage.setScene(scene);
        stage.setTitle("Testversion 0.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private double newSize(Number newValue, Number oldValue, double size){
        return ((double)newValue)/((double)oldValue)*size;
    }
}
