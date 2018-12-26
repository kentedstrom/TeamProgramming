package userInterface;

import backend.Project;
import backend.Risk;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

        Parent startScreen = FXMLLoader.load(getClass().getResource("/StartScreen.fxml"));
        Scene startScreenScreen = new Scene(startScreen, 800,500);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(startScreenScreen);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    @FXML
    void getRiskMatrix(ActionEvent event){
        final  String risk1 = "Risk 1";
        final  String risk2 = "Risk 2";
        final  String risk3 = "Risk 3";
        final  String risk4 = "Risk 4";
        final  String risk5 = "Risk 5";

        Stage stage = new Stage();
        stage.setTitle("Risk Matrix");

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis(0, 10, 1);
        final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);

        bc.setTitle("Risk Matrix");
        xAxis.setLabel("Risk");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Impact");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Probability");

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
    void showScheduleBtnClicked(ActionEvent event) {
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
}