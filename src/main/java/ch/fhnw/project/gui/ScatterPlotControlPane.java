package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.Locale;

public final class ScatterPlotControlPane extends HBox {

    protected Slider slider;
    protected ColorPicker colorPicker;
    protected CheckBox checkBoxLinePlot;
    protected CheckBox checkBoxBubblePlot;

    protected ComboBox<Variable> comboBoxBubblePlt;

    public ScatterPlotControlPane() {


        slider = new Slider();
        setSliderConditions();
        Label sliderLabel = new Label("Change dot size: ");
        VBox vBoxSliderAndLabel = new VBox( sliderLabel, slider);
        vBoxSliderAndLabel.setSpacing(5);

        colorPicker = new ColorPicker();
        setColorPicker();
        Label colorPickerLabel = new Label("Change dot color: ");
        VBox colorPickerAndLabel = new VBox(colorPickerLabel, colorPicker);
        colorPickerAndLabel.setSpacing(5);

        checkBoxLinePlot = new CheckBox("Line plot");
        checkBoxBubblePlot = new CheckBox("Bubble plot");
        setCheckBoxes();
        VBox checkBoxVbox = new VBox(checkBoxLinePlot,checkBoxBubblePlot);

        comboBoxBubblePlt = new ComboBox<>();
        comboBoxBubblePlt.setDisable(true);
        Label comboBoxBubblePltLabel = new Label("Choose your third variable: ");
        VBox comboBoxAndLabel = new VBox(comboBoxBubblePltLabel, comboBoxBubblePlt);
        comboBoxAndLabel.setSpacing(5);

        this.getChildren().addAll(vBoxSliderAndLabel,colorPickerAndLabel,checkBoxVbox,comboBoxAndLabel);
        setSettingsScPlotCtrlPane();
    }

    private void setSliderConditions() {
        slider.setMin(2.0);
        slider.setMax(20.0);
    }

    private void setCheckBoxes() {
        checkBoxLinePlot.setPadding(new Insets(5, 5, 5, 5));
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
