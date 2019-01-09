package userInterface;

import backend.Project;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Navigation {

    void toProjectOverview(ActionEvent event, Project project) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/ProjectOverview.fxml")));
        Parent projectOverview = loader.load();

        Scene projectOverviewScene = new Scene(projectOverview, 1080,700);
        ProjectOverviewController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(projectOverviewScene);
        window.show();
    }

    void toRiskPlots(ActionEvent event, Project project) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/Risk.fxml")));
        Parent riskDashboard = loader.load();

        Scene riskScene = new Scene(riskDashboard, 1080,700);
        RiskController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(riskScene);
        window.show();
    }

    void toPlotStartPage(ActionEvent event, Project project) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/PlotsStartPage.fxml")));
        Parent PlotStartPage = loader.load();

        Scene PlotsScene = new Scene(PlotStartPage, 1080,700);
        PlotsScene.getStylesheets().add("/ganttChart.css");
        PlotStartPageController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(PlotsScene);
        window.show();
    }

    void toTaskOV(ActionEvent event, Project project) throws IOException {
        // go to Task Overview
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource("/TaskOverview.fxml")));
        Parent taskOV = loader.load();

        Scene taskScene = new Scene(taskOV, 1080,700);
        TaskOverviewController controller = loader.getController();

        controller.initData(project);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(taskScene);
        window.show();
    }

}
