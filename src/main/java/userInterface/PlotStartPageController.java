package userInterface;

import backend.Calculations;
import backend.Project;
import backend.Task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PlotStartPageController implements Initializable {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private Navigation goBack = new Navigation();
    // to get data from calculations class
    // not sure if responsibilities are right here
    private Calculations getData = new Calculations(project.getTasks());

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
    private PieChart memberTimePie;

    @FXML
    private BarChart memberTimeBar;

    @FXML
    private CategoryAxis bcXAxis;

    @FXML
    private NumberAxis bcYAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void initData(Project project){


        this.project = project;

        // add data to the table's columns
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        memberColumn.setCellValueFactory(new PropertyValueFactory<>("members"));

        taskTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Task> currentTasks = FXCollections.observableArrayList();
        for (Task task: project.getTasks()){
            currentTasks.add(task);
        }

        taskTable.setItems(currentTasks);

        // set up Gantt Chart

        yAxis.setLabel("Task");
        xAxis.setLabel("Week");


        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        // pull task name and start and end times

        for (Task task:project.getTasks()) {

            series1.getData().add(new XYChart.Data(task.getStartWeek(), task.getName()));
            series2.getData().add(new XYChart.Data((task.getEndWeek()-task.getStartWeek()), task.getName()));

        }

        // plot gantt chart data
        ganttChart.getData().addAll(series1, series2);


        // set up Total Workload Chart

        workloadXAxis.setLabel("Task");
        workloiadYAxis.setLabel("Week");

        // new series for AreaChart
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();

        int[] workLoadPerWeek = getData.calculateWorkLoadPerWeek();
        for (int i = 0; i < workLoadPerWeek.length; i++) {
            series3.getData().add(new XYChart.Data(i,"week"+i));
            series4.getData().add(new XYChart.Data(workLoadPerWeek[i],"week"+i));
        }

        // plot workload per week area chart
        totalWorkload.getData().addAll(series3,series4);


        // set up time spent by member on the project Pie Chart...
        memberTimePie = new PieChart();

        // ... and at the same time plot the time spent by member on a BarChart

        bcXAxis.setLabel("Member");
        bcYAxis.setLabel("Time");

        XYChart.Series series5 = new XYChart.Series();

        // retrieve calculated time spent
        HashMap<String,Double> timeSpentByMember = getData.TimeSpentOnProjectByMember(project.getMembers());

        for (String key: timeSpentByMember.keySet()) {
            // not counting the total number in the PieChart
            if(!key.equals("Total")) {
                memberTimePie.getData().add(new PieChart.Data(key, timeSpentByMember.get(key)));
            }
            series5.getData().add(new XYChart.Data(timeSpentByMember.get(key), key));
        }

        // plot the bar chart
        memberTimeBar.getData().addAll(series5);


    }


    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
        goBack.toProjectOverview(event, project);
    }

    @FXML
    void showRisks(ActionEvent event) {

    }

    @FXML
    void showSchedule(ActionEvent event) {

    }

    @FXML
    void showTaskOV(ActionEvent event) {

    }

}
