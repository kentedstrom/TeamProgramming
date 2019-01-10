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
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;

public class TaskOverviewController implements Initializable {

    private Project project;
    private Navigation goBack;
    private Calculations getData;
    private Calendar calendar;

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
    private LineChart TimeAndCostStatus;

    @FXML
    private CategoryAxis time;

    @FXML
    private NumberAxis money;

    @FXML
    private LineChart SVCV;

    @FXML
    private CategoryAxis time1;

    @FXML
    private NumberAxis money1;


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
        this.calendar = new GregorianCalendar();

        // set up task status pie chart
        plotTaskCompletenessPie();

        // set up budget status pie chart
        plotBudgetAvailability();

        // set up Time and Cost and SV and CV plots
        plotCostChart();

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

        double[] budgetAvailability = getData.BudgetStatus(project.getMembers());

        double spentMoney = budgetAvailability[0]+budgetAvailability[1];
        pieChartData.add(new PieChart.Data("spent",spentMoney));
        // the cost planned on an uncompleted task is not shown here
        pieChartData.add(new PieChart.Data("available",this.project.getBudget() - spentMoney));

        budgetStatus.setData(pieChartData);
        budgetStatus.setLabelsVisible(false);

    }

    public void plotCostChart(){
        // add planned value
        time.setLabel("Time (Weeks)");
        money.setLabel("Money (SEK)");

        // planned value
        XYChart.Series PVseries = new XYChart.Series();
        PVseries.setName("Planned Value");

        double[] PV = getData.calculatePlannedValue(project.getMembers());
        for (int i = 0; i < PV.length; i++) {
            PVseries.getData().add(new XYChart.Data("week"+i,PV[i]));
        }

        // actual value
        XYChart.Series AVseries = new XYChart.Series();
        AVseries.setName("Actual Cost");

        double[] AV = getData.calculateActualValue(project.getMembers());
        for (int i = 0; i < AV.length; i++) {
            AVseries.getData().add(new XYChart.Data("week"+i,AV[i]));
        }

        // add earned value
        XYChart.Series EVseries = new XYChart.Series();
        EVseries.setName("Earned Value");

        double EV = getData.earnedValueCalc(project.getBudget());
        EVseries.getData().add(new XYChart.Data( "week" + String.valueOf(project.adjustWeek(calendar.WEEK_OF_YEAR)),EV));

        TimeAndCostStatus.getData().setAll(PVseries,AVseries,EVseries);



    }

    public void plotSVCVChart(double EarnedValue, double ActualCost, double PlannedValue){

        // add schedule variance for one time point
        double SV = getData.ScheduleVariance(EarnedValue,PlannedValue);

        XYChart.Series SVseries = new XYChart.Series();
        SVseries.setName("Schedule Variance");
        SVseries.getData().add(new XYChart.Data( "week" + String.valueOf(project.adjustWeek(calendar.WEEK_OF_YEAR)),SV));


        // add cost variance for one time point
        double CV = getData.CostVariance(EarnedValue,ActualCost);
        XYChart.Series CVseries = new XYChart.Series();
        CVseries.setName("Cost Variance");
        CVseries.getData().add(new XYChart.Data( "week" + String.valueOf(project.adjustWeek(calendar.WEEK_OF_YEAR)),CV));

        SVCV.getData().addAll(SVseries,CVseries);

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
