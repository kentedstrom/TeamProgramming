package userInterface;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

public class TaskOverviewController {

    @FXML
    private Button schedule;

    @FXML
    private Button taskOverview;

    @FXML
    private Button risks;

    @FXML
    private Button back;

    @FXML
    private PieChart taskStatus;

    @FXML
    private PieChart budgetStatus;

    @FXML
    private LineChart<?, ?> values;

    @FXML
    private LineChart<?, ?> values1;

    @FXML
    void backBtnClicked(ActionEvent event) {

    }

    @FXML
    void showRisks(ActionEvent event) {

    }

    @FXML
    void showSchedule(ActionEvent event) {

    }

    @FXML
    void showTaskOV(ActionEvent event) {

    }

}
