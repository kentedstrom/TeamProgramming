package userInterface;

import backend.Member;
import backend.Project;
import backend.Task;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<String> table;

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


    @FXML
    void addTime(ActionEvent event) {

        Task task = taskChoice.getSelectionModel().getSelectedItem();
        Integer taskID = task.getID();
        Member member = memberChoice.getSelectionModel().getSelectedItem();
        String timeSpent = timeInput.getText();

        // add time spent on taks to member
        member.addTimeToTask(taskID,Double.parseDouble(timeSpent));

        ObservableList<String> memberTimes = FXCollections.observableArrayList();

        memberTimes.add(task.getName());

        for (Member eachMember : project.getMembers()) {
            memberTimes.add(String.valueOf(eachMember.getTimeSpentPerTask(taskID)));
        }


        table.setItems(memberTimes);



        timeInput.clear();


    }

    @FXML
    void deleteTime(ActionEvent event) {

    }

    @FXML
    void backPushed(ActionEvent event) {

    }

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

        //table.getColumns().add(new TableColumn("Task"));



        for (Member member: project.getMembers()) {
            TableColumn<String,String> column = new TableColumn<>(member.getName());
            column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()));
            table.getColumns().add(column);
        }




    }

    /*
    @JsonIgnore
    private TableColumn getTableColumnByName(String name){
        for (Object col: table.getColumns()) {
            TableColumn column = (TableColumn) col;
            if(column.getText().equals(name)){
                return column;
            }
        }
        return null;
    }
    */
}
