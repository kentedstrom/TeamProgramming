package userInterface;

import backend.Member;
import backend.Project;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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
        int memberID = project.getHighestMemberID() + 1;
        double memberSalary = Double.parseDouble(salaryInput.getText());
        project.createMember(memberName, memberID, memberSalary);

        ObservableList<Member> currentMembers = FXCollections.observableArrayList();
        for (Member member : members) {
            currentMembers.add(member);
        }

        table.setItems(currentMembers);

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
