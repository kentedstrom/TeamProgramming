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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartScreenController implements Initializable {

    private Project project;


    @FXML
    private Button newBtn;

    //When button is clicked instantiate a Project and open the Create New Project Screen
    @FXML
        void newBtnClicked(ActionEvent event) throws Exception {

        ////////change window to ProjectOverview and passes a project object to the ProjectOverviewController//////
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/CreateNewProject.fxml")));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 1080,700);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();

    }

    @FXML
    void openProject(ActionEvent event) {
        try {
            /*
            ObjectMapper objectMapper = new ObjectMapper();
            this.project = objectMapper.readValue(new File("C:\\Users\\tobbe\\JSON files\\Projecttest.JSON"), Project.class);
            */

            //imports from TeamProgramming folder the same as it saves
            this.project = Controller.importProject("Projecttest.json");


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ProjectOverview.fxml")));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 1080,700);
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
