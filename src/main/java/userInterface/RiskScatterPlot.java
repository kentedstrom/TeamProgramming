package userInterface;
/*
import backend.Project;
import backend.Risk;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RiskScatterPlot extends Application{

    private Project project;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(0, 10, 1);
        final ScatterChart<Number, Number> sc = new
                ScatterChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("Impact");
        yAxis.setLabel("Probability");
        sc.setTitle("Risk Assessment");

        for (Risk risk : project.getRisks()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(risk.getName());
            series.getData().add(new XYChart.Data(risk.getImpact(),risk.getProbability()));
            sc.getData().add(series);
        }



    }


}
*/