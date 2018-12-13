package screens;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AddRiskController {

    @FXML
    private Button backBtn;

    @FXML
    private Button addRiskBtn;

    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
        Parent optionsMenu = FXMLLoader.load(getClass().getResource("/optionMenu.fxml"));
        Scene optionsMenuScene = new Scene(optionsMenu, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(optionsMenuScene);
        window.show();

    }

    @FXML
    void addRiskClicked(ActionEvent event) {

    }

}