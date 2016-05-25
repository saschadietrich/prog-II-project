package ch.fhnw.project.gui;

import ch.fhnw.project.io.LineReader;
import ch.fhnw.project.io.TxtReader;
import ch.fhnw.project.model.DataModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.util.Optional;

public final class App extends Application {
    private DataModel dataModel;

    @Override
    public void start(Stage stage) {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Choose your Data");
        chooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All","*.txt","*.lin"),
                new FileChooser.ExtensionFilter("Text Files","*.txt"),
                new FileChooser.ExtensionFilter("Line Files","*.lin"));
        File file = chooser.showOpenDialog(stage);

        if (file == null){
            System.exit(0);
        }

        try {
            if(file.getName().endsWith(".txt")) {
                dataModel = new TxtReader().creatingModel(file);
            } else if (file.getName().endsWith(".lin")){
                dataModel = new LineReader().creatingModel(file);
            }
            dataModel.checkData();
            MainPane mainpane = new MainPane(dataModel);
            Scene scene = new Scene(mainpane);
            stage.setScene(scene);
            stage.setTitle(dataModel.getFilename());
            stage.show();
        } catch (Exception e) {
            System.out.println("Error!!: " + e.toString());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText(e.toString());
            alert.setContentText("Choose your Option");
            ButtonType buttonTypeOne = new ButtonType("Choose new File");
            ButtonType buttonTypeCancel = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
            e.printStackTrace(); // muss wieder weg

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get()== buttonTypeOne){
                start(stage);
            }
        }


    }

    public static void main(String[] args) {
        launch(args);
    }

}
