package userInterface;

import backend.Member;
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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddMemberController implements Initializable {

    Project project;

    @FXML
    private TableView<Member> table;

    @FXML
    private TextField IDInput;

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
        String memberID = IDInput.getText();
        double memberSalary = Double.parseDouble(salaryInput.getText());

        Member newMember = new Member(memberName,memberID,memberSalary);

        project.createMember(memberName,memberID,memberSalary);

        table.getItems().add(newMember);
        nameInput.clear();
        IDInput.clear();
        salaryInput.clear();

    }

    @FXML
    void deleteMember(ActionEvent event) {
        ObservableList<Member> memberSelected, allMembers;
        allMembers = table.getItems();
        memberSelected = table.getSelectionModel().getSelectedItems();

        memberSelected.forEach(allMembers::remove);

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
    void backBtnClicked(ActionEvent event) throws IOException {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
