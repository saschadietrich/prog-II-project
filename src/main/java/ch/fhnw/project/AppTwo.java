
/*
/**
 * Created by stefan on 28.04.16.
 */
package ch.fhnw.project;

        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.chart.NumberAxis;
        import javafx.scene.chart.ScatterChart;
        import javafx.scene.chart.XYChart;
        import javafx.scene.control.Button;
        import javafx.scene.control.ComboBox;
        import javafx.scene.layout.FlowPane;
        import javafx.scene.layout.Pane;
        import javafx.stage.FileChooser;
        import javafx.stage.Stage;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.function.Supplier;

public final class AppTwo extends Application {

    @Override
    public void start(Stage stage) { // gemacht um zu sehen ob die TXTData klasse funktioniert

        NumberAxis xAchse = new NumberAxis(-200, 5000, 100);
        NumberAxis yAchse = new NumberAxis(-200, 5000, 100);
        ScatterChart<Number, Number> sc = new ScatterChart<>(xAchse, yAchse);
        XYChart.Series series1 = new XYChart.Series();

        ComboBox<String> comboBox;

        Button bu1 = new Button("Load");
        bu1.setOnAction(event -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("test");
            String fileadress = chooser.showOpenDialog(stage).getAbsolutePath();
            System.out.println(fileadress);

            LineData data = new LineData(fileadress);

            String varOne = data.getNameOfVariables(0);
            String varTwo = data.getNameOfVariables(1);

            //System.out.println(varOne);
            //System.out.println(varTwo);

            xAchse.setLabel(varOne);
            yAchse.setLabel(varTwo);

            double[] dataOne = data.getValuesofVariable(varOne);  //Fehler in der Funktion Klasse LineData
            double[] dataTwo = data.getValuesofVariable(varTwo);

            ;
            for(int i =0; i<dataOne.length; i++){
                series1.getData().add(new XYChart.Data(dataOne[i], dataTwo[i]));
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
