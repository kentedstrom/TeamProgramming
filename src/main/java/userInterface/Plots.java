package userInterface;

import backend.Member;
import backend.Project;
import backend.Risk;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.chart.ScatterChart;

import java.io.IOException;
import java.util.ArrayList;


public class Plots extends Application {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private EscapeHatch goBack = new EscapeHatch();

    @FXML
    private ScatterChart<Double, Double> riskAssessment;

    @FXML
    private BarChart<Double, Double> riskLevel;

    @FXML
    private void backBtn(ActionEvent event) throws IOException {
        goBack.backBtnClicked(event, this.project);
    }


    public void initData(Project project){
        this.project = project;

        // TODO: show the data upon start
        BarChart<String, Number> bc = createBarChart();
        ScatterChart<Number,Number> sc = createScatterChart();


    }

    private ScatterChart<Number,Number> createScatterChart(){
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(0, 10, 1);
        final ScatterChart<Number, Number> sc = new
                ScatterChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("Impact");
        yAxis.setLabel("Probability");
        sc.setTitle("Risk Assessment");

        for (Risk risk : this.project.getRisks()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(risk.getName());
            series.getData().add(new XYChart.Data(risk.getImpact(), risk.getProbability()));
            sc.getData().add(series);
        }

        return sc;
    }

    private BarChart<String,Number> createBarChart(){
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0,10,1);
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

        XYChart.Series series = new XYChart.Series();
        series.setName("Risk");

        for (Risk risk:project.getRisks()) {
            series.getData().add(new XYChart.Data(risk.getName(), risk.getProbability()*risk.getImpact() ));
            bc.getData().add(series);
        }

        return bc;

    }


    @Override
    public void start(Stage primaryStage) {


    }

}
