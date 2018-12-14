package userInterface;

import backend.Controller;
import backend.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

    private Project project;
    private Project loadedProject;


    @FXML
    private Button newBtn;

    public void initData(Project project){
        this.project = project;
    }

    //When button is clicked instantiate a Project and open the Project overview Scene
    @FXML
        void newBtnClicked(ActionEvent event) throws Exception {

        //initializing project
        // ToDo : fix when do you create new project and what is the minimal info you need to do so
        String name = "";
        double budget = 1.0;
        project = Controller.createNewProject(name,budget);

        ////////change window to ProjectOverview and passes a project object to the ProjectOverviewController//////
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
