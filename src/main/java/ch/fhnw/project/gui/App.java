package ch.fhnw.project.gui;

import ch.fhnw.project.io.LineReader;
import ch.fhnw.project.io.TxtReader;
import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.function.Supplier;

public final class App extends Application {
    private MainPane mainpane;
    private DataModel dataModel;

    @Override
    public void start(Stage stage) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose your Data");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All","*.txt","*.lin"),
                new FileChooser.ExtensionFilter("Text Files","*.txt"),
                new FileChooser.ExtensionFilter("Line Files","*.lin"));
        File file = chooser.showOpenDialog(stage);

        try {
            if(file.getName().endsWith(".txt")) {
                dataModel = new TxtReader().creatingModel(file);
            } else if (file.getName().endsWith(".lin")){
                dataModel = new LineReader().creatingModel(file);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();}

        mainpane = new MainPane(dataModel);

        Scene scene = new Scene(mainpane);
        stage.setScene(scene);
        stage.setTitle(dataModel.getFilename());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
