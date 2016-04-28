package ch.fhnw.project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.util.function.Supplier;

public final class App extends Application {

    @Override
    public void start(Stage stage) { // gemacht um zu sehen ob die TXTData klasse funktioniert

        NumberAxis xAchse = new NumberAxis(-2, 4, 1);
        NumberAxis yAchse = new NumberAxis(-2, 4, 1);
        ScatterChart<Number, Number> sc = new ScatterChart<>(xAchse, yAchse);
        XYChart.Series series1 = new XYChart.Series();

        Button bu1 = new Button("Load");
        bu1.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("test");
            String fileadress = chooser.showOpenDialog(stage).getAbsolutePath();
            System.out.println(fileadress);
            TxtData one = new TxtData(fileadress, 0);
            TxtData two = new TxtData(fileadress, 1);
            xAchse.setLabel(one.getName());
            yAchse.setLabel(two.getName());
            Double[] onea = new Double[one.getVar().size()];
            one.getVar().toArray(onea);
            Double[] twoa = new Double[two.getVar().size()];
            two.getVar().toArray(twoa);
            for(int i =0; i<twoa.length; i++){
                series1.getData().add(new XYChart.Data(onea[i], twoa[i]));
            }
            sc.getData().addAll(series1);
            });


        Pane pane = new FlowPane();
        pane.getChildren().addAll(bu1, sc);


        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Testversion 0.0");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
