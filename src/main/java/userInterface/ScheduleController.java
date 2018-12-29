package userInterface;

import backend.Project;
import backend.Task;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScheduleController implements Initializable {

    private Project project;
    private ArrayList<Task> tasks;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, String> statusColumn;

    @FXML
    private Button backBtn;

    @FXML
    private CategoryAxis yAxis;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private StackedBarChart ganttChart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Project project){


        this.project = project;
        this.tasks = new ArrayList<>();
        this.tasks = project.getTasks();


        yAxis.setLabel("Task");
        xAxis.setLabel("Week");


        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        for (Task task:tasks) {

            series1.getData().add(new XYChart.Data(task.getStartWeek(), task.getName()));
            series2.getData().add(new XYChart.Data((task.getEndWeek()-task.getStartWeek()), task.getName()));

        }


        ganttChart.getData().addAll(series1, series2);
        taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        taskTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<Task> currentTasks = FXCollections.observableArrayList();
        for (Task task: tasks){
            currentTasks.add(task);
        }

        taskTable.setItems(currentTasks);


    }
    @FXML
    void detailsBtnClicked(ActionEvent event) throws Exception{

            Task taskSelected;
            taskSelected = taskTable.getSelectionModel().getSelectedItem();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation((getClass().getResource("/detailsView.fxml")));
            Parent detailsView = loader.load();

            Scene detailsViewScene = new Scene(detailsView, 500, 300);
            DetailsViewController controller = loader.getController();

            controller.initData(taskSelected, project);
            Stage window = new Stage();
            window.setScene(detailsViewScene);
            window.showAndWait();


    }
    @FXML
    void backBtnClicked(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ProjectOverview.fxml")));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 800,500);
        ProjectOverviewController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();
    }


}
