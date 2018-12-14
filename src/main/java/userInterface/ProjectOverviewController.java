package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ProjectOverviewController {

    @FXML
    private Button optionsBtn;

    @FXML
    private Button backBtn;

    @FXML
    void optionsBtnClicked(ActionEvent event) throws Exception {
        Parent optionsMenu = FXMLLoader.load(getClass().getResource("/optionMenu.fxml"));
        Scene optionsMenuScene = new Scene(optionsMenu, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(optionsMenuScene);
        window.show();

    }

    @FXML
    void backBtnClicked(ActionEvent event) throws Exception {

        Parent startScreen = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
        Scene startScreenSceen = new Scene(startScreen, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(startScreenSceen);
        window.show();
    }

}