package userInterface;

import backend.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EscapeHatch {

    void backBtnClicked(ActionEvent event, Project project) throws IOException {
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
}
