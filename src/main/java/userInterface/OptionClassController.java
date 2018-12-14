package userInterface;

import backend.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class OptionClassController implements Initializable {

    private Project project;

    @FXML
    private Button backBtn;

    @FXML
    private SplitMenuButton removeMemberDropdown;

    @FXML
    private MenuButton removeTaskDropdown;

    @FXML
    private Button removeMemberBtn;

    @FXML
    private Button removeTaskBtn;

    @FXML
    private TextField budgetTextfield;

    @FXML
    private Button addMemberBtn;

    @FXML
    private Button addRiskBtn;

    @FXML
    private Button addTaskBtn;

    @FXML
    private Button enterBtn;

    public void initData(Project project){
        this.project = project;
    }
    @FXML
    void addRisk(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/addRisk.fxml")));
        Parent addRisk = loader.load();

        Scene addRiskScene = new Scene(addRisk, 800,500);
        AddRiskController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addRiskScene);
        window.show();

    }

    @FXML
    void addTask(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/addTask.fxml")));
        Parent addTask = loader.load();

        Scene addTaskScene = new Scene(addTask, 800,500);
        AddTaskController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addTaskScene);
        window.show();

    }

    @FXML
    void backBtnClicked(ActionEvent event) throws Exception{
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
    void enterBtnClicked(ActionEvent event) {
        project.setBudget(Double.parseDouble(budgetTextfield.getText()));
        Stage window = new Stage();
        window.setTitle("Budget Changed!");
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL);

        Button okBtn = new Button("OK");
        Label label = new Label("Budget is now: " + project.getBudget() + " Riksdaler!");

        okBtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        budgetTextfield.clear();



    }

    @FXML
    void removeMemberConfirm(ActionEvent event) {

    }

    @FXML
    void removeTaskConfirm(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}