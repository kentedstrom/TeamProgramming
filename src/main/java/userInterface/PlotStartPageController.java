package userInterface;

import backend.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import java.io.IOException;

public class PlotStartPageController {

    private Project project;
    // use a class for all cases where it wants to go back to the main screen
    private EscapeHatch goBack = new EscapeHatch();

    @FXML
    private Button schedule;

    @FXML
    private Button taskOverview;

    @FXML
    private Button risks;

    @FXML
    private Button back;

    @FXML
    private TableColumn<?, ?> taskColumn;

    @FXML
    private TableColumn<?, ?> memberColumn;

    @FXML
    private TableColumn<?, ?> statusColumn;

    @FXML
    private StackedBarChart<?, ?> ganttChart;

    @FXML
    private CategoryAxis yAxis;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private AreaChart<?, ?> totalWorkload;

    @FXML
    private PieChart memberTimePie;

    @FXML
    private BarChart<?, ?> memberTimeBar;

    @FXML
    void backBtnClicked(ActionEvent event) throws IOException {
        goBack.backBtnClicked(event, project);
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
