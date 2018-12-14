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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {

    private Project project;

    @FXML
    private TextField startWeek;

    @FXML
    private TextField cost;

    @FXML
    private RadioButton completeRadioBtn;

    @FXML
    private Button backBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField endWeek;

    @FXML
    private RadioButton notCompleteRadioBtn;

    @FXML
    void confirmTask(ActionEvent event) {
        int startWeek = Integer.parseInt(this.startWeek.getText());
        int endWeek = Integer.parseInt(this.endWeek.getText());
        double cost = Double.parseDouble(this.cost.getText());
        boolean completed;
        if (completeRadioBtn.isSelected()){
            completed = true;
        }
        else{
            completed = false;
        }
        project.createTask(startWeek,endWeek,cost,completed);
        Stage window = new Stage();
        window.setTitle("Task added!");
        window.setMinWidth(250);
        window.initModality(Modality.APPLICATION_MODAL);

        Button okBtn = new Button("OK");
        Label label = new Label("a task with no instruction added! no one knows what to do");

        okBtn.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, okBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20,20,20,20));

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
        this.startWeek.clear();
        this.endWeek.clear();
        this.cost.clear();
        this.completeRadioBtn.setSelected(false);
        this.notCompleteRadioBtn.setSelected(false);



    }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
