package userInterface;

import backend.Calculations;
import backend.Project;
import backend.Risk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RiskController implements Initializable {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private Navigation goBack;
    private Calculations getData;


    @FXML
    private Button schedule;

    @FXML
    private Button taskOverview;

    @FXML
    private Button risks;

    @FXML
    private Button back;

    @FXML
    private ScatterChart riskScatter;

    @FXML
    private CategoryAxis scatterX;

    @FXML
    private NumberAxis scatterY;

    @FXML
    private BarChart riskBar;

    @FXML
    private CategoryAxis barX;

    @FXML
    private NumberAxis barY;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Project project) {
        this.project = project;
        this.goBack = new Navigation();
        this.getData = new Calculations(this.project.getTasks());

        showRiskScatterPlot();
        showRiskBarPlot();

    }


    // show risk scatter plot: impact vs probability
    private void showRiskScatterPlot(){
        scatterX.setLabel("Probability");
        scatterY.setLabel("Impact");

        for (Risk risk : project.getRisks()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(risk.getName());
            series.getData().add(new XYChart.Data(String.valueOf(risk.getImpact()), risk.getProbability()));
            riskScatter.getData().add(series);
        }
    }

    // show risk bar chart
    // responsibilities may be not ok here, calculation should be in calculation class
    private void showRiskBarPlot(){
        barY.setLabel("risk level");

        XYChart.Series series = new XYChart.Series();

        for (Risk risk: project.getRisks()) {
            series.getData().add(new XYChart.Data(risk.getName(),risk.getProbability()*risk.getImpact()));
        }
        riskBar.getData().addAll(series);
    }


    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
        goBack.toProjectOverview(event, project);
    }

    @FXML
    void showRisks(ActionEvent event) {
        // do nothing, stay on this page
    }

    @FXML
    void showSchedule(ActionEvent event) throws IOException {
        goBack.toPlotStartPage(event,project);
    }

    @FXML
    void showTaskOV(ActionEvent event) throws IOException {
        goBack.toTaskOV(event,project);
    }

}
