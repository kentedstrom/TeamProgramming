package userInterface;

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
import java.util.ResourceBundle;

public class PlotStartPageController implements Initializable {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private Navigation goBack = new Navigation();

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void initData(Project project){


        this.project = project;

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

        // plot data
        ganttChart.getData().addAll(series1, series2);

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

        // set up Total Workload Chart

        workloadXAxis.setLabel("Task");
        workloiadYAxis.setLabel("Week");



        // TODO: calculate overlap : backend task

        // ToDO: calculate time spent by member (%) to show on Pie Chart and the same as absolute value to show on Bar Plot


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
