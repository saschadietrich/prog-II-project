package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.Locale;

public class ScatterPlotControlPane extends HBox {

    public Slider slider;
    public ColorPicker colorPicker;
    public CheckBox chechBoxLinePlot;
    public CheckBox checkBoxBubblePlot;

    public ComboBox<Variable> comboBoxBubblePlt;

    public ScatterPlotControlPane() {


        slider = new Slider();
        setSliderConditions();
        Label sliderLabel = new Label("Change dot size: ");
        VBox vBoxSilderandLabel = new VBox( sliderLabel, slider);
        vBoxSilderandLabel.setSpacing(5);

        colorPicker = new ColorPicker();
        setColorPicker();
        Label colorPickerLabel = new Label("Change dot color: ");
        VBox colorPickerandLabel = new VBox(colorPickerLabel, colorPicker);
        colorPickerandLabel.setSpacing(5);

        chechBoxLinePlot = new CheckBox("Line plot");
        checkBoxBubblePlot = new CheckBox("Bubble plot");
        setCheckBoxes();
        VBox checkBoxVbox = new VBox(chechBoxLinePlot,checkBoxBubblePlot);

        comboBoxBubblePlt = new ComboBox<>();
        comboBoxBubblePlt.setDisable(true);
        Label combooBoxBubblePltLabel = new Label("Choose your third variable: ");
        VBox comboBoxandLable = new VBox(combooBoxBubblePltLabel, comboBoxBubblePlt);
        comboBoxandLable.setSpacing(5);

        this.getChildren().addAll(vBoxSilderandLabel,colorPickerandLabel,checkBoxVbox,comboBoxandLable);
        setSettingsScPlotCtrlPane();
    }

    private void setSliderConditions() {
        slider.setMin(2.0);
        slider.setMax(10.0);
    }

    private void setCheckBoxes() {
        chechBoxLinePlot.setPadding(new Insets(5, 5, 5, 5));
        checkBoxBubblePlot.setPadding(new Insets(5, 5, 5, 5));
    }

    private void setSettingsScPlotCtrlPane() {
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
        this.setPadding(new Insets(5, 5, 5, 5));
    }

    private void setColorPicker() {
        Locale.setDefault(Locale.ENGLISH);
        colorPicker.setValue(Color.BLACK);
    }
}
