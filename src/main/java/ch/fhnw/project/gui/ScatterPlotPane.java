package ch.fhnw.project.gui;


import ch.fhnw.project.model.Variable;
import javafx.scene.layout.Pane;

public class ScatterPlotPane extends Pane {



    public void setXVariable(Variable variable) {
        for (Double aDouble : variable.getValues()) {
            System.out.println(aDouble);

        }
    }

    public void setYVariable(Variable variable){
        System.out.println("olee");
    }
}
