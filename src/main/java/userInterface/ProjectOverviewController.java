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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProjectOverviewController implements Initializable{

    private Project project;


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
    private Button backBtn;
    @FXML
    private Label budgetLabel;
    @FXML
    private Button riskMatrix;

    public void initData(Project project){
        this.project = project;
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
    void getRiskMatrix(ActionEvent event){

        Stage stage = new Stage();
        stage.setTitle("Risk Matrix");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0,10,1);
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

        bc.setTitle("Risk Matrix");
        xAxis.setLabel("Risk");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Probability");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Impact");

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Risk");

        for (Risk risk:project.getRisks()) {

            series1.getData().add(new XYChart.Data(risk.getName(), risk.getProbability()));
            series2.getData().add(new XYChart.Data(risk.getName(), risk.getImpact()));
            series3.getData().add(new XYChart.Data(risk.getName(), risk.getProbability()*risk.getImpact() ));

        }


            Scene scene  = new Scene(bc,500,450);
            bc.getData().addAll(series1, series2, series3);
            stage.setScene(scene);
            stage.show();

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
    void saveProject(ActionEvent event)throws IOException{

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("C:\\Users\\tobbe\\JSON files\\Projecttest.JSON"), project);


        /* Can't get this to work

            Controller.saveProject(project, "C:\\Users\\tobbe\\JSON files");

         */
    }
}