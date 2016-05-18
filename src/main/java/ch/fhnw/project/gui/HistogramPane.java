package ch.fhnw.project.gui;

import ch.fhnw.project.model.DataModel;
import ch.fhnw.project.model.Variable;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.StackPane;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class HistogramPane extends StackPane{

    private CategoryAxis xAxis = new CategoryAxis();
    private NumberAxis yAxis = new NumberAxis();
    private BarChart<String,Number> barChart = new BarChart<>(xAxis, yAxis);
    private static int numberOfBin;

    public HistogramPane(){
        barChart.setBarGap(0);
        barChart.setCategoryGap(0);
        barChart.setLegendVisible(false);
        this.getChildren().add(barChart);
    }

    public void change(Variable variable){
        //funktion die aufgerufen wird um die Histogramme zu erzeugen

        barChart.setTitle(variable.toString());
        plot(biuldGroup(variable), getBinNames(variable));

    }

    private int[] biuldGroup(Variable variable){
        //kreiert die bins und auch ihre grössen

        double differenz = variable.getMax()-variable.getMin();
        int[] bin = new int[numberOfBin];
        List<Double> list = variable.getValues();

        for(int i = 0; i< numberOfBin; i++){
            bin[i]=0;
        }

        for (Double aDouble : list) {
            int value = (int)(((aDouble-variable.getMin())/differenz) * (numberOfBin));
            int index = Math.min(value,numberOfBin-1);// verschiebt werte die genau an der Bin grenze ist -->
            bin[index]++;
        }

//        System.out.println("Pins: " +numberOfBin);
//        System.out.println("Pins: " +numberOfBin);
//        int total=0;
//        for (int pin : bin) {
//            total += pin;
//            System.out.println(pin);
//        }
//        System.out.println("Size: "+ variable.getValues().size() + " Sum of Pinssize: "+ total);

        return bin;
    }

    private double getStepSize(Variable variable) {
        //berrechnet die benötigten bins
        return (variable.getMax()-variable.getMin())/ numberOfBin;
    }

    private void plot(int[] pins,List<String> pinName){
        //löscht und zeichnet das neue Histogramm

        barChart.getData().clear();
        XYChart.Series<String, Number> dataPoints = new XYChart.Series<>();

        for (int i = 0; i< numberOfBin; i++){
            dataPoints.getData().add(new XYChart.Data<>(pinName.get(i),pins[i]));

        }
        barChart.getData().add(dataPoints);
    }

    private List<String> getBinNames(Variable variable){
        // kreirt eine Liste mit den Bins bezeichnungen

        double step = getStepSize(variable);
        List<String> names = new ArrayList<>();

        for(int i = 1; i<= numberOfBin; i++){
            names.add(format((variable.getMin()+step*i)/2));
        }
        return names;
    }

    private String format(double input) {
        // formatiert zahlen auf 2 Kommastellen
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        double toFormat = ((double)Math.round(input*100))/100;
        return decimalFormat.format(toFormat);
    }

    public static void setBin(DataModel dataModel){
        //berrechnet einmalig die benötigten bins --> gilt für alle Datensaötz einer Datei
        numberOfBin = (int)(Math.sqrt(dataModel.getVariable().get(0).getValues().size()));
    }
}
