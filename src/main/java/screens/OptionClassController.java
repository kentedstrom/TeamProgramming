package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionClassController {

    @FXML
    private Button backBtn;

    @FXML
    private SplitMenuButton removeMemberDropdown;

    @FXML
    private MenuButton removeTaskDropdown;

    @FXML
    private Button removeMemberBtn;

    @FXML
    private Button removeTaskBtn;

    @FXML
    private TextField budgetTextfield;

    @FXML
    private Button addMemberBtn;

    @FXML
    private Button addRiskBtn;

    @FXML
    private Button addTaskBtn;

    @FXML
    private Button enterBtn;

    @FXML
    void addRisk(ActionEvent event) throws IOException {
        Parent addRisk = FXMLLoader.load(getClass().getResource("/addRisk.fxml"));
        Scene addRiskWindow = new Scene(addRisk, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addRiskWindow);
        window.show();

    }

    @FXML
    void addTask(ActionEvent event) throws IOException {
        Parent addTask = FXMLLoader.load(getClass().getResource("/addTask.fxml"));
        Scene addTaskScene = new Scene(addTask, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addTaskScene);
        window.show();

    }

    @FXML
    void addMemberClicked(ActionEvent event) throws IOException {
        Parent addMember = FXMLLoader.load(getClass().getResource("/addMember.fxml"));
        Scene addMemberScene = new Scene(addMember, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addMemberScene);
        window.show();
    }
    @FXML
    void backBtnClicked(ActionEvent event) throws Exception{
        Parent projectOverview = FXMLLoader.load(getClass().getResource("/ProjectOverview.fxml"));
        Scene projectOverviewScene = new Scene(projectOverview, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();
    }

    @FXML
    void enterBtnClicked(ActionEvent event) {


    }

    @FXML
    void removeMemberConfirm(ActionEvent event) {

    }

    @FXML
    void removeTaskConfirm(ActionEvent event) {

    }

}