package userInterface;

import backend.Member;
import backend.Project;
import backend.Task;
import backend.TimeSpentTable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TimeRegisterController implements Initializable {

    private Project project;
    private ArrayList<Member> members;
    private ArrayList<Task> tasks;
    private Navigation goBack;

    @FXML
    private TableView<TimeSpentTable> table;

    @FXML
    private Button back;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private ChoiceBox<Task> taskChoice;

    @FXML
    private ChoiceBox<Member> memberChoice;

    @FXML
    private TextField timeInput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void initData(Project project){
        this.project = project;

        members = this.project.getMembers();
        for (Member member:members) {
            memberChoice.getItems().add(member);
        }

        tasks = this.project.getTasks();
        for (Task task:tasks) {
            taskChoice.getItems().add(task);
        }

        setTableView();
    }

    private void setTableView(){

        table.setEditable(true);

        // add starting info to TimeSpentTable class
        TableColumn<TimeSpentTable,String> firstCol = new TableColumn<>("Task");
        table.getColumns().add(firstCol);

        for (Member member: project.getMembers()) {
            TableColumn<TimeSpentTable,String> nameCol = new TableColumn<>(member.getName());
            table.getColumns().add(nameCol);
            nameCol.setCellValueFactory(new PropertyValueFactory<>("memberTimes"));
        }

        firstCol.setCellValueFactory(new PropertyValueFactory<>("taskName"));

        ObservableList<TimeSpentTable> currentTimeSpent = FXCollections.observableArrayList();
        for (TimeSpentTable timeSpent : project.getTimeSpentTables()) {
            currentTimeSpent.add(timeSpent);
        }

        table.setItems(currentTimeSpent);

    }

    @FXML
    void addTime(ActionEvent event) {


        Task task = taskChoice.getSelectionModel().getSelectedItem();
        Integer taskID = task.getID();
        Member member = memberChoice.getSelectionModel().getSelectedItem();
        String timeSpent = timeInput.getText();

        // add time spent on taks to member
        member.addTimeToTask(taskID,Double.parseDouble(timeSpent));

        // add the info to the project's timespenttable class that is purely for showing the data

        ArrayList<String> memberTimes = new ArrayList<>();
        for (Member currentOne: project.getMembers()) {
            memberTimes.add(String.valueOf(currentOne.getTimeSpentPerTask(taskID)));
        }

        project.addTimeSpent(task.getName(),memberTimes);

        // transfer the project's class to observablelist
        ObservableList<TimeSpentTable> timeSpentTable = FXCollections.observableArrayList();
        for (TimeSpentTable timesOfPeople : project.getTimeSpentTables()) {
            timeSpentTable.add(timesOfPeople);
        }

        table.setItems(timeSpentTable);

        timeInput.clear();


    }

    @FXML
    void deleteTime(ActionEvent event) {

    }

    @FXML
    void backPushed(ActionEvent event) {

    }

}
