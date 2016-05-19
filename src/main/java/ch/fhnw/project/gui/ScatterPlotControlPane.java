package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Locale;

public class ScatterPlotControlPane extends HBox {

    public Slider slider = new Slider();
    public ColorPicker colorPicker = new ColorPicker();
    public CheckBox cb = new CheckBox();
    public CheckBox cb2 = new CheckBox();
    public ComboBox<Variable> comboBoxBubblePlt = new ComboBox<>();

    public ScatterPlotControlPane() {

        this.getChildren().addAll(slider, cb, colorPicker, cb2, comboBoxBubblePlt);

        setCheckBox();
        setSettingsScPlotCtrlPane();
        setSliderConditions();
        setColorPicker();
        comboBoxBubblePlt.setDisable(true);
    }

    private void setSliderConditions() {
        slider.setMin(2.0);
        slider.setMax(10.0);
    }

    private void setCheckBox() {
        cb.setText("Line Plot");
        cb.setPadding(new Insets(5, 5, 5, 5));
        cb2.setText("Bubble Blot");
        cb2.setPadding(new Insets(5, 5, 5, 5));
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
