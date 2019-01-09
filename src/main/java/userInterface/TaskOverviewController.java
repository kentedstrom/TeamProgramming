package userInterface;

import backend.Calculations;
import backend.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class TaskOverviewController implements Initializable {

    private Project project;
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
    private PieChart taskStatus;

    @FXML
    private PieChart budgetStatus;

    @FXML
    private LineChart TimeStatus;

    @FXML
    private LineChart CostStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void initData(Project project) {

        this.project = project;
        // for back button to work
        this.goBack = new Navigation();
        // to get data from calculations class
        // not sure if responsibilities are right here
        this.getData = new Calculations(project.getTasks());

        // set up task status pie chart
        plotTaskCompletenessPie();

        // set up budget status pie chart
        plotBudgetAvailability();
    }

    // plot the number of the completed tasks vs the time of the incomplete tasks
    private void plotTaskCompletenessPie(){

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        double[] taskCompleteness = getData.TaskCompletion();

        pieChartData.add(new PieChart.Data("completed",taskCompleteness[0]));
        pieChartData.add(new PieChart.Data("unfinished",taskCompleteness[1]));

        taskStatus.setData(pieChartData);
        taskStatus.setLabelsVisible(false);

    }

    //plot the budget spent vs the budget still available
    private void plotBudgetAvailability(){

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        double[] budgetAvailability = getData.BudgetStatus();

        pieChartData.add(new PieChart.Data("spent",budgetAvailability[0]));
        // the cost planned on an uncompleted task is not shown here
        pieChartData.add(new PieChart.Data("available",this.project.getBudget() - budgetAvailability[0]));

        budgetStatus.setData(pieChartData);
        budgetStatus.setLabelsVisible(false);

    }


        @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
        goBack.toProjectOverview(event, project);
    }

    @FXML
    void showRisks(ActionEvent event) throws IOException {
        goBack.toRiskPlots(event,project);
    }

    @FXML
    void showSchedule(ActionEvent event) throws IOException {
        goBack.toPlotStartPage(event,project);
    }

    @FXML
    void showTaskOV(ActionEvent event) {
        // do nothing, stay here
    }

}
