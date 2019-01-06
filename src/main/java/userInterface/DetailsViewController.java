package userInterface;

import backend.Member;
import backend.Project;
import backend.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class DetailsViewController implements Initializable {

    private Project project;
    private Task task;

    @FXML
    private TableView<Member> memberTable;
    @FXML
    private TableColumn<Member, String> IDColumn;
    @FXML
    private TableColumn<Member, String> nameColumn;
    @FXML
    private Label startWeekLabel;

    @FXML
    private Label budgetLabel;

    @FXML
    private Button changeStatusBtn;

    @FXML
    private RadioButton inProgressBtn;

    @FXML
    private RadioButton completedBtn;

    @FXML
    private ChoiceBox<Member> addMemberMenu;

    @FXML
    private Label endWeekLabel;

    @FXML
    private Button confirmBtn;

    @FXML
    private Label taskLabel;

    @FXML
    private Label statusLabel;

    @FXML
    void changeStatus(ActionEvent event) {
        if (completedBtn.isSelected()){
            this.task.setCompleted(true);
        }
        if (inProgressBtn.isSelected()){
            this.task.setCompleted(false);
        }
        statusLabel.setText(task.getStatus());
    }

    @FXML
    void addMember(ActionEvent event) {

        Member memberToAdd = addMemberMenu.getSelectionModel().getSelectedItem();
        task.addMember(memberToAdd.getID());
        memberToAdd.addTask(this.task.getID());

        ObservableList<Member> currentMembers = FXCollections.observableArrayList();
        for (int memberID : task.getListOfMemberIDs()) {
            Member member = project.searchMember(memberID);
            currentMembers.add(member);
        }
        memberTable.setItems(currentMembers);


    }
    @FXML
    void removeBtnClicked(ActionEvent event){
        Member memberToRemove;
        memberToRemove = memberTable.getSelectionModel().getSelectedItem();
        task.removeMember(memberToRemove.getID());

        ObservableList<Member> memberSelected, allMembers;
        allMembers = memberTable.getItems();
        memberSelected = memberTable.getSelectionModel().getSelectedItems();

        task.getListOfMemberIDs().remove(memberTable.getSelectionModel().getSelectedItem());

        memberSelected.forEach(allMembers::remove);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    void initData(Task task, Project project){
        this.task = task;
        this.project = project;

        try {
            taskLabel.setText(task.getName());
            startWeekLabel.setText(String.valueOf(task.getStartWeek()));
            endWeekLabel.setText(String.valueOf(task.getEndWeek()));
            budgetLabel.setText(String.valueOf(task.getBudget()));
            statusLabel.setText(task.getStatus());

            ToggleGroup group = new ToggleGroup();

            completedBtn.setToggleGroup(group);
            inProgressBtn.setToggleGroup(group);

            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

            ObservableList<Member> currentMembers = FXCollections.observableArrayList();

            for (int memberID : task.getListOfMemberIDs()) {
                Member member = project.searchMember(memberID);
                currentMembers.add(member);
            }


            memberTable.setItems(currentMembers);

            for (Member member:project.getMembers()) {

                addMemberMenu.getItems().add(member);

            }


        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }





    }

}
