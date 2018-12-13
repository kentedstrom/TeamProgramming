package screens;

import backend.backbone.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartScreenController {
    public Project project;

    @FXML
    private Button newBtn;

    //When button is clicked instantiate a Project and open the Project overview Scene
    @FXML
    void newBtnClicked(ActionEvent event) throws Exception {
        //initializing project

        ////////change window to ProjectOverview//////

        Parent projectOverview = FXMLLoader.load(getClass().getResource("/ProjectOverview.fxml"));
        Scene projectOverviewScene = new Scene(projectOverview, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();


    }

}
