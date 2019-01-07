package userInterface;

import com.fasterxml.jackson.databind.ObjectMapper;
import backend.Controller;
import backend.Project;
import backend.Risk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class ProjectOverviewController implements Initializable{

    private Project project;
    private Navigation goBack;


    @FXML
    private Label earnedValueLabel;
    @FXML
    private Label scheduleVarianceLabel;
    @FXML
    private Label costVarianceLabel;
    @FXML
    private Button showMembersBtn;
    @FXML
    private Button showScheduleBtn;
    @FXML
    private Button optionsBtn;
    @FXML
    private Button timeSpentReg;
    @FXML
    private Button backBtn;
    @FXML
    private Label budgetLabel;
    @FXML
    private Button dashboard;


    public void initData(Project project){
        this.project = project;
        this.goBack = new Navigation();
        budgetLabel.setText(Double.toString(project.getBudget()));

    }

    @FXML
    void optionsBtnClicked(ActionEvent event) throws Exception {

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
    void backBtnClicked(ActionEvent event) throws Exception {

        ////Doesn't work at the moment////

        /* Stage confirmBox = new Stage();

        confirmBox.setTitle("Are you sure you want to exit?");
        confirmBox.setMinWidth(250);
        confirmBox.initModality(Modality.APPLICATION_MODAL);

        Button saveAndExitBtn = new Button("Save and Exit");
        Button exitBtn = new Button("Exit without saving");
        Button closeBtn = new Button("Close");

        Label label = new Label("-Choose Option-");

        VBox layout = new VBox(10);
        HBox hbox = new HBox(10);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(saveAndExitBtn,exitBtn,closeBtn);
        layout.getChildren().addAll(label, hbox);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        confirmBox.setScene(scene);
        confirmBox.showAndWait();


        exitBtn.setOnAction(e ->{
            try {
                confirmBox.close();
                Parent startScreen = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
                Scene startScreenScreen = new Scene(startScreen, 800, 500);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(startScreenScreen);
                window.show();
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
                });

        saveAndExitBtn.setOnAction(e ->{
            try {
                confirmBox.close();
                Parent startScreen = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
                Scene startScreenScreen = new Scene(startScreen, 800, 500);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(startScreenScreen);
                window.show();
            }catch (Exception exception){
                System.out.println(exception.getMessage());
            }
        });

        closeBtn.setOnAction(e -> confirmBox.close());
        */
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void getDashboard(ActionEvent event) throws IOException {

        goBack.toPlotStartPage(event,project);

/*
        stage.setTitle("Scatter Chart Sample");
        final NumberAxis xAxis = new NumberAxis(0, 10, 1);
        final NumberAxis yAxis = new NumberAxis(0, 10, 1);
        final ScatterChart<Number, Number> sc = new
                ScatterChart<Number, Number>(xAxis, yAxis);
        xAxis.setLabel("Impact");
        yAxis.setLabel("Probability");
        sc.setTitle("Risk Assessment");

        //ArrayList<XYChart.Series> allSeries = new ArrayList<>();

        Scene scene  = new Scene(sc,500,450);

        for (Risk risk : project.getRisks()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(risk.getName());
            series.getData().add(new XYChart.Data(risk.getImpact(), risk.getProbability()));
            sc.getData().add(series);
            //allSeries.add(series);
        }

        stage.setScene(scene);
        stage.show();
*/
    }

    @FXML
    void showScheduleBtnClicked(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ScheduleView.fxml")));
        Parent scheduleView = loader.load();

        Scene scheduleScene = new Scene(scheduleView, 800,500);
        scheduleScene.getStylesheets().add("/ganttChart.css");
        ScheduleController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scheduleScene);
        window.show();

    }

    @FXML
    void showMembersBtnClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ShowMembers.fxml")));
        Parent addMember = loader.load();

        Scene addMemberScene = new Scene(addMember, 800,500);
        AddMemberController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(addMemberScene);
        window.show();
    }

    @FXML
    void timeSpentRegClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/TimeRegister.fxml")));
        Parent timeRegister = loader.load();

        Scene timeRegScene = new Scene(timeRegister, 800,500);
        TimeRegisterController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(timeRegScene);
        window.show();
    }

    @FXML
    void saveProject(ActionEvent event)throws IOException{

        /*ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("C:\\Users\\tobbe\\JSON files\\Projecttest.JSON"), project);
        */
        // it saves to your TeamProgramming folder
        Controller.saveProject(project, "Projecttest.json");

    }
}