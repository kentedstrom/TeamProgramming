package userInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import backend.Controller;
import backend.Project;
import backend.Risk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ProjectOverviewController implements Initializable{

    private Project project;
    private Navigation goBack;

    boolean SAVEANDCLOSE;
    Boolean CLOSE;


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
    private Button timeRegister;
    @FXML
    private Button backBtn;
    @FXML
    private Label budgetLabel;
    @FXML
    private Button dashboard;


    public void initData(Project project){
        this.project = project;
        this.goBack = new Navigation();

    }

    @FXML
    void optionsBtnClicked(ActionEvent event) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/optionMenu.fxml")));
        Parent optionMenu = loader.load();

        Scene optionMenuScene = new Scene(optionMenu, 1080,700);
        OptionClassController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(optionMenuScene);
        window.show();

    }

    @FXML
    void backBtnClicked(ActionEvent event) throws Exception {


        boolean save = ConfirmBox.save();

        if (save){

            Controller.saveProject(project, "Projecttest.json" );
        }
        else if (!save){

        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/StartScreen.fxml")));
        Parent startScreen = loader.load();

        Scene startScreenScene = new Scene(startScreen, 1080,700);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(startScreenScene);
        window.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void getDashboard(ActionEvent event) throws IOException {

        goBack.toPlotStartPage(event,project);

    }

    @FXML
    void timeRegisterClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/TimeRegister.fxml")));
        Parent regTime = loader.load();

        Scene regTimeScene = new Scene(regTime, 1080,700);
        TimeRegisterController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(regTimeScene);
        window.show();
    }

    @FXML
    void showScheduleBtnClicked(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ScheduleView.fxml")));
        Parent scheduleView = loader.load();

        Scene scheduleScene = new Scene(scheduleView, 1080,700);
        scheduleScene.getStylesheets().add("/ganttChart.css");
        ScheduleController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scheduleScene);
        window.show();

    }

    @FXML
    void showMembersBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ShowMembers.fxml")));
        Parent addMember = loader.load();

        Scene addMemberScene = new Scene(addMember, 1080,700);
        AddMemberController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addMemberScene);
        window.show();
    }

    @FXML
    void timeSpentRegClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/TimeRegister.fxml")));
        Parent timeRegister = loader.load();

        Scene timeRegScene = new Scene(timeRegister, 800,500);
        TimeRegisterController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(timeRegScene);
        window.show();
    }

    @FXML
    void saveProject(ActionEvent event)throws IOException{

        Controller.saveProject(project, "Projecttest.json");

    }
}