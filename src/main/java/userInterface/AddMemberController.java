package userInterface;

import backend.Member;
import backend.Project;
import backend.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private Navigation goBack = new Navigation();

    @FXML
    private TableView<Member> table;

    @FXML
    private Button backBtn;

    @FXML
    private TextField IDInput;

    @FXML
    private TextField salaryInput;

    @FXML
    private TextField nameInput;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TableColumn<Member, String> nameColumn;

    @FXML
    private TableColumn<Member, String> IDColumn;

    @FXML
    private TableColumn<Member, Double> salaryColumn;

    private ArrayList<Member> members;

    @FXML
    void addMember(ActionEvent event) {

        String memberName = nameInput.getText();
        // create member ID
        String memberID = IDInput.getText();
        double memberSalary = Double.parseDouble(salaryInput.getText());
        project.createMember(memberName, memberID, memberSalary);

        ObservableList<Member> currentMembers = FXCollections.observableArrayList();
        for (Member member : members) {
            currentMembers.add(member);
        }

        table.setItems(currentMembers);

        IDInput.clear();
        nameInput.clear();
        salaryInput.clear();


    }

    @FXML
    void deleteMember(ActionEvent event) {
        try {
            ObservableList<Member> memberSelected, allMembers;
            allMembers = table.getItems();
            memberSelected = table.getSelectionModel().getSelectedItems();

            members.remove(table.getSelectionModel().getSelectedItem());

            memberSelected.forEach(allMembers::remove);
        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void showTimeSpent(ActionEvent event){
        try {
            Member memberToShow;
            memberToShow = table.getSelectionModel().getSelectedItem();

            String tasksCompleted = " ";
            String tasksInProgress = " ";

            for (Integer taskID : memberToShow.getTasks()) {
                Task task = project.searchTask(taskID);
                if (task.getCompleted()) {
                    tasksCompleted += " " + task.getName() + ",";
                } else if (!task.getCompleted()) {
                    tasksInProgress += " " + task.getName() + ",";
                }
            }

            tasksCompleted = tasksCompleted.substring(0, tasksCompleted.length() -1) + "";
            tasksInProgress = tasksInProgress.substring(0, tasksInProgress.length()-1) + "";



        Stage timeSpentWindow = new Stage();
            timeSpentWindow.setTitle("Work done/in progress");

        VBox vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER_LEFT);
        vBox.setPadding(new Insets(30,30,30,30));


        Label tasksCompletedLabel = new Label("Tasks completed: " + tasksCompleted);
        Label tasksInProgressLabel = new Label("Tasks in progress: " + tasksInProgress);
        Label timeSpentLabel = new Label("Time spent on project:  " + memberToShow.timeSpent() + " weeks");
        Button okBtn = new Button("OK");

        okBtn.setOnAction(e -> timeSpentWindow.close());

        vBox.getChildren().setAll(tasksCompletedLabel, tasksInProgressLabel, timeSpentLabel, okBtn);
        Scene scene = new Scene(vBox, 350,200);
        timeSpentWindow.setScene(scene);
        timeSpentWindow.showAndWait();

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    public void initData(Project project){
        try {
            this.project = project;

            members = new ArrayList<>();
            members = project.getMembers();


            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
            salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ObservableList<Member> currentMembers = FXCollections.observableArrayList();
                for (Member member : members) {
                    currentMembers.add(member);
                }

                table.setItems(currentMembers);

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }

    @FXML
    void backBtnClicked(ActionEvent event) throws IOException{
        goBack.toProjectOverview(event, project);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
