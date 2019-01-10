package userInterface;

import backend.Calculations;
import backend.Member;
import backend.Project;
import backend.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TimeRegisterController implements Initializable {

    private Project project;
    private ArrayList<Member> members;
    private ArrayList<Task> tasks;
    private Navigation goBack;
    private Calculations getData;

    @FXML
    private Button backBtn;

    @FXML
    private Button addBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private ChoiceBox<Task> taskChoice;

    @FXML
    private ChoiceBox<Member> memberChoice;

    @FXML
    private TextField timeInput;

    @FXML
    private TextArea logField;

    @FXML
    private PieChart memberTimePie;

    @FXML
    private BarChart<Number,String> memberTimeBar;

    @FXML
    private CategoryAxis timeBarXAxis;

    @FXML
    private NumberAxis timeBarYAxis;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void initData(Project project){
        this.project = project;
        this.goBack = new Navigation();
        this.getData = new Calculations(project.getTasks());

        members = this.project.getMembers();
        for (Member member:members) {
            memberChoice.getItems().add(member);
        }

        tasks = this.project.getTasks();
        for (Task task:tasks) {
            taskChoice.getItems().add(task);
        }

        // set up member time charts
        addMemberTimeCharts();

    }

    private void addMemberTimeCharts(){
        // set up time spent by member on the project Pie Chart...
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        // ... and at the same time plot the time spent by member on a BarChart

        timeBarXAxis.setLabel("Member");
        timeBarYAxis.setLabel("Time");

        XYChart.Series<Number,String> series5 = new XYChart.Series<>();

        // retrieve calculated time spent
        HashMap<String,Double> timeSpentByMember = getData.TimeSpentOnProjectByMember(project.getMembers());

        series5.getData().add(new XYChart.Data("Total",timeSpentByMember.get("Total")));

        for (String key: timeSpentByMember.keySet()) {
            // not counting the total number in the PieChart
            if(!key.equals("Total")) {
                pieChartData.add(new PieChart.Data(key, timeSpentByMember.get(key)));
                series5.getData().add(new XYChart.Data(key,timeSpentByMember.get(key)));
            }
        }

        // add data to pieChart
        memberTimePie.setData(pieChartData);
        memberTimePie.setLabelsVisible(false);

        // plot the bar chart
        memberTimeBar.getData().addAll(series5);
    }



    @FXML
    void addTime(ActionEvent event) {

        Task task = taskChoice.getSelectionModel().getSelectedItem();
        Integer taskID = task.getID();
        Member member = memberChoice.getSelectionModel().getSelectedItem();
        String timeSpent = timeInput.getText();

        // add time spent on taks to member
        member.addTimeToTask(taskID,Double.parseDouble(timeSpent));

        // log the info on the textArea
        String toLog = member.getName() + " spent " + member.getTimeSpentPerTask(taskID) + " week(s) on " + task.getName() + "\n";
        logField.appendText(toLog);

        updatePlots(member,taskID);

        timeInput.clear();


    }

    @FXML
    void deleteTime(ActionEvent event) {
        Task task = taskChoice.getSelectionModel().getSelectedItem();
        Integer taskID = task.getID();
        Member member = memberChoice.getSelectionModel().getSelectedItem();
        String timeSpent = timeInput.getText();

        // remove the time from the member's task
        member.removeTimeFromTask(taskID,Double.parseDouble(timeSpent));

        // log to the textArea
        String toLog = timeSpent + " week(s) were removed from " + member.getName() + "'s " + task.getName() + ".\nThe time " + member.getName() + " spent on " + task.getName() + " so far is " + String.valueOf(member.getTimeSpentPerTask(taskID)) + " week(s). \n";
        logField.appendText(toLog);

        updatePlots(member,taskID);

        timeInput.clear();

    }

    public void updatePlots(Member member, Integer taskID){


        // update pieChart
        for (PieChart.Data data : memberTimePie.getData()) {
            if(data.getName().equals(member.getName())){
                data.setPieValue(Double.valueOf(member.getTimeSpentPerTask(taskID)));
            }
        }

        // update barPlot
        memberTimeBar.getData().clear();

        XYChart.Series<Number,String> series5 = new XYChart.Series<>();

        // retrieve calculated time spent
        HashMap<String,Double> timeSpentByMember = getData.TimeSpentOnProjectByMember(project.getMembers());

        series5.getData().add(new XYChart.Data("Total",timeSpentByMember.get("Total")));

        for (String key: timeSpentByMember.keySet()) {
            // not counting the total number in the PieChart
            if(!key.equals("Total")) {
                series5.getData().add(new XYChart.Data(key,timeSpentByMember.get(key)));
            }
        }
        memberTimeBar.getData().addAll(series5);

    }

    @FXML
    void backBtnPushed(ActionEvent event) throws IOException {
        goBack.toProjectOverview(event, project);
    }

}
