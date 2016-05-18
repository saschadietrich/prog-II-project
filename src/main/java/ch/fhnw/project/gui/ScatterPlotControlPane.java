package ch.fhnw.project.gui;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
    //public ToggleGroup toggleGroup = new ToggleGroup();
    public CheckBox cb = new CheckBox();
   /* public RadioButton scatterRB = new RadioButton("Scatter Plot");
    public RadioButton lineRB = new RadioButton("Line Plot");
    private RadioButton bubbleRB = new RadioButton("Bubble Plot");
*/

    public ScatterPlotControlPane(){

        this.getChildren().addAll(slider,/*scatterRB,lineRB, bubbleRB,*/cb,colorPicker);

        //setToggleGroup();
        setCheckBox();
        setSettingsScPlotCtrlPane();
        setSliderConditions();
        setColorPicker();
    }

    private void setSliderConditions(){
        slider.setMin(2.0);
        slider.setMax(10.0);
    }

    private void setCheckBox(){
        cb.setText("Line Plot");
        cb.setPadding(new Insets(5,5,5,5));
    }

 /*   private void setToggleGroup(){
        scatterRB.setToggleGroup(toggleGroup);
        lineRB.setToggleGroup(toggleGroup);
        bubbleRB.setToggleGroup(toggleGroup);
        scatterRB.setSelected(true);
    }*/

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

    public  ColorPicker sendColorpicker() {
        return colorPicker;
    }

}
