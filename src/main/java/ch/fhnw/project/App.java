package ch.fhnw.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.function.Supplier;

public final class App extends Application {

    @Override
    public void start(Stage stage) {

        Button bu1 = new Button("Test");
        bu1.setOnAction(event -> loadfenster(stage));

        Pane pane = new Pane();
        pane.getChildren().add(bu1);


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Testversion 0.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void loadfenster(Stage stage){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("test");
        String fileadress = chooser.showOpenDialog(stage).getAbsolutePath();
        //TxtData one = new TxtData(fileadress,0);//geht aus ifgendeinem grund nicht

    }
}
