package ch.fhnw.project.gui;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Locale;

public class ScatterPlotControlPane extends HBox {

//    private VBox vbox = new VBox();
//    private HBox scatterBox = new HBox();
//    private HBox scatterPanelBox = new HBox();

    public Slider slider = new Slider();
    public ColorPicker colorPicker = new ColorPicker();
    private ToggleGroup toggleGroup = new ToggleGroup();
    private RadioButton scatterRB = new RadioButton("Scatter Plot");
    private RadioButton lineRB = new RadioButton("Line Plot");
    private RadioButton bubbleRB = new RadioButton("Bubble Plot");


    public ScatterPlotControlPane(){

        this.getChildren().addAll(slider,scatterRB,lineRB, bubbleRB, colorPicker);

        setToggleGroup();
        setSettingsScPlotCtrlPane();
        setSliderConditions();
        setColorPicker();
    }

    private void setSliderConditions(){
        slider.setMin(2.0);
        slider.setMax(10.0);
    }

    private void setToggleGroup(){
        scatterRB.setToggleGroup(toggleGroup);
        lineRB.setToggleGroup(toggleGroup);
        bubbleRB.setToggleGroup(toggleGroup);
        scatterRB.setSelected(true);
    }

    private void setSettingsScPlotCtrlPane(){
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(5,5,5,5));
    }

    private void setColorPicker(){
        Locale.setDefault(Locale.ENGLISH);
        colorPicker.setValue(Color.BLACK);
        // man muss iwie die Custom Color Choice ausschalten können --> bei mir hängt sich das Programm dann immer auf, vllt auch ein Linux Problem
    }

}
