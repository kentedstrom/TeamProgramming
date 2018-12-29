package userInterface;

import backend.Controller;
import backend.Project;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
        //  Creating a project with the controller ( doesn't work right now)
        // project = Controller.createNewProject(name,budget);
        this.project = new Project();

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

    @FXML
    void openProject(ActionEvent event) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.project = objectMapper.readValue(new File("C:\\Users\\tobbe\\JSON files\\Projecttest.JSON"), Project.class);


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ProjectOverview.fxml")));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 800,500);
        ProjectOverviewController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
