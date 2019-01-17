package userInterface;

import backend.Calculations;
import backend.Project;
import backend.Task;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlotStartPageController implements Initializable {

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
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, String> memberColumn;

    @FXML
    private TableColumn<Task, String> statusColumn;

    @FXML
    private StackedBarChart ganttChart;

    @FXML
    private CategoryAxis yAxis;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private AreaChart totalWorkload;

    @FXML
    private CategoryAxis workloadXAxis;

    @FXML
    private NumberAxis workloiadYAxis;

    @FXML
    private AreaChart costDistribution;

    @FXML
    private CategoryAxis costLoadXAxis;

    @FXML
    private NumberAxis costLoadYAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void initData(Project project){


        this.project = project;
        // for back button to work
        this.goBack = new Navigation();
        // to get data from calculations class
        // not sure if responsibilities are right here
        this.getData = new Calculations(project.getTasks());

        // add data to the table's columns
        addTableData();

        // set up Gantt Chart
        addGanttChart();

        // set up Total Workload Chart
        addWorkloadAreaChart();

        //set cost area chart
        addCostDistributionAreaChart();

    }

    private void addTableData(){


        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        memberColumn.setCellValueFactory(new PropertyValueFactory<>("listOfMemberNames"));

        taskTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Task> currentTasks = FXCollections.observableArrayList();
        for (Task task: project.getTasks()){
            currentTasks.add(task);
        }

        taskTable.setItems(currentTasks);
    }

    private void addGanttChart(){
        yAxis.setSide(Side.LEFT);
        yAxis.setLabel("Task");
        xAxis.setSide(Side.BOTTOM);
        xAxis.setLabel("Week");


        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        // pull task name and start and end times

        for (Task task:project.getTasks()) {

            series1.getData().add(new XYChart.Data(task.getStartWeek(),task.getName()));
            series2.getData().add(new XYChart.Data(task.getEndWeek()-task.getStartWeek(),task.getName()));

        }

        // plot gantt chart data
        ganttChart.getData().addAll(series1, series2);
    }

    private void addWorkloadAreaChart(){
        workloadXAxis.setLabel("Week");
        workloiadYAxis.setLabel("Task");

        // new series for AreaChartgetTimeSpentPerTask
        XYChart.Series series3 = new XYChart.Series();

        int[] workLoadPerWeek = getData.calculateWorkLoadPerWeek();
        for (int i = 0; i < workLoadPerWeek.length; i++) {
            series3.getData().add(new XYChart.Data("week"+i,workLoadPerWeek[i]));
        }

        // plot workload per week area chart
        totalWorkload.getData().addAll(series3);
    }

    private void addCostDistributionAreaChart(){
        costLoadXAxis.setLabel("Week");
        costLoadYAxis.setLabel("Cost");

        // new series for this chart
        XYChart.Series budgetSeries = new XYChart.Series();

        double[] costPerWeek = getData.calculateCostPerWeek(project.getMembers());
        for (int i = 0; i < costPerWeek.length; i++) {
            System.out.println("The budget is " + costPerWeek[i] + " on week"+ i);
            budgetSeries.getData().add(new XYChart.Data("week"+i,costPerWeek[i]));
        }

        costDistribution.getData().setAll(budgetSeries);

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
    void showSchedule(ActionEvent event) {
        // stay here
    }

    @FXML
    void showTaskOV(ActionEvent event) throws IOException {
        goBack.toTaskOV(event,project);
    }

}
