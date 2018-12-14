package userInterface;

import backend.Project;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddRiskController implements Initializable {

    private Project project;

    @FXML
    private Button backBtn;

    @FXML
    private Button addRiskBtn;

    @FXML
    private TextField riskProb;

    @FXML
    private TextField riskName;

    @FXML
    private TextField riskImpact;

    public void initData(Project project){
        this.project = project;
    }

    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
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
    public void addRiskClicked(ActionEvent event){
        String name = riskName.getText();
        double impact = Double.parseDouble(riskImpact.getText());
        double probability = Double.parseDouble(riskProb.getText());

        project.createRisk(name, probability, impact);

        Stage window = new Stage();
        window.setTitle("Risk Added!");
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL);

        Button okBtn = new Button("OK");
        Label label = new Label("You added the risk '" + riskName.getText() + "' to your project!");

        okBtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

        riskName.clear();
        riskImpact.clear();
        riskProb.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}