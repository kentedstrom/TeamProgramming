package userInterface;

import backend.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectOverviewController implements Initializable{

    private Project project;


    @FXML
    private Label earnedValueLabel;
    @FXML
    private Label scheduleVarianceLabel;
    @FXML
    private Label costVarianceLabel;
    @FXML
    private Button showMembersBtn;
    @FXML
    private Button showScheduleBtn;
    @FXML
    private Button optionsBtn;
    @FXML
    private Button backBtn;
    @FXML
    private Label budgetLabel;

    public void initData(Project project){
        this.project = project;
        budgetLabel.setText(Double.toString(project.getBudget()));

    }

    @FXML
    void optionsBtnClicked(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/optionMenu.fxml")));
        Parent optionMenu = loader.load();

        Scene optionMenuScene = new Scene(optionMenu, 800,500);
        OptionClassController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(optionMenuScene);
        window.show();

    }

    @FXML
    void backBtnClicked(ActionEvent event) throws Exception {

        Parent startScreen = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
        Scene startScreenScreen = new Scene(startScreen, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(startScreenScreen);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void showScheduleBtnClicked(ActionEvent event) {
    }

    @FXML
    void showMembersBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ShowMembers.fxml")));
        Parent addMember = loader.load();

        Scene addMemberScene = new Scene(addMember, 800,500);
        AddMemberController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addMemberScene);
        window.show();
    }
}