package ch.fhnw.project.gui;


import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScatterPlotControlPane extends HBox {

    private VBox vbox = new VBox();
    private HBox scatterBox = new HBox();
    private HBox scatterPanelBox = new HBox();

    private Slider slider = new Slider();
    private ToggleGroup toggleGroup = new ToggleGroup();
    private RadioButton scatterRB = new RadioButton("Scatter Plot");
    private RadioButton lineRB = new RadioButton("Line Plot");
    private RadioButton bubbleRB = new RadioButton("Bubble Plot");


    public ScatterPlotControlPane(){
        this.getChildren().addAll(slider,scatterRB,lineRB, bubbleRB);
    }



}
