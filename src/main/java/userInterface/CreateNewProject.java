package userInterface;

import backend.Controller;
import backend.Project;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateNewProject implements Initializable {

    private Project project;
    Navigation goBack;


    @FXML
    private TextField typeProjectName;

    @FXML
    private TextField typeProjectBudget;

    @FXML
    private TextField typeStartWeek;

    @FXML
    private Button createProject;

    @FXML
    private Button backToStart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void initData(Project project){
        this.project = project;
        this.goBack = new Navigation();

    }


    @FXML
    void backToStart(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/StartScreen.fxml"));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 800,500);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();
    }

    @FXML
    void createProject(ActionEvent event) throws IOException {

        String name = typeProjectName.getText();
        double budget = Double.parseDouble(typeProjectBudget.getText());
        int startWeek = Integer.parseInt(typeStartWeek.getText());
       // create a new project with name and budget through controller class
        this.project = Controller.createNewProject(name,budget,startWeek);

        ////////change window to ProjectOverview and passes a project object to the ProjectOverviewController//////
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ProjectOverview.fxml")));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 1080,800);
        ProjectOverviewController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();
    }

}

