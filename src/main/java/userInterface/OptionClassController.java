package userInterface;

import backend.Member;
import backend.Project;
import backend.Risk;
import backend.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OptionClassController implements Initializable {

    private Project project;

    @FXML
    private TextField startWeek;

    @FXML
    private TextField cost;

    @FXML
    private RadioButton completeRadioBtn;

    @FXML
    private Button confirmBtn;

    @FXML
    private TextField endWeek;

    @FXML
    private RadioButton notCompleteRadioBtn;

    @FXML
    private TableView<Risk> table;

    @FXML
    private TextField budgetTextfield;

    @FXML
    private Button deleteRiskBtn;

    @FXML
    private TableColumn<Risk, String> probColumn;

    @FXML
    private TextField probInput;

    @FXML
    private Button backBtn;

    @FXML
    private Button addRiskBtn;

    @FXML
    private TableColumn<Risk, Double> impactColumn;

    @FXML
    private TextField riskInput;

    @FXML
    private TextField impactInput;

    @FXML
    private Button enterBtn;

    @FXML
    private TableColumn<Risk, Double> riskColumn;

    @FXML
    private TableView<Task> taskTable;

    @FXML
    private TableColumn<Task, String> taskColumn;

    @FXML
    private TableColumn<Task, Integer> startWeekColumn;

    @FXML
    private  TableColumn<Task, Integer> endWeekColumn;

    @FXML
    private TextField taskNameInput;

    @FXML
    private Button deleteTaskBtn;

    @FXML
    private ChoiceBox<Member> memberChoice;

    private ArrayList<Risk> risks;

    private ArrayList<Task> tasks;

    private ArrayList<Member> members;

    public void initData(Project project){
        try {
            this.project = project;

            members = new ArrayList<Member>();
            members = project.getMembers();

            risks = new ArrayList<Risk>();
            risks = project.getRisks();

            tasks = new ArrayList<Task>();
            tasks = project.getTasks();

            for (Member member:members) {

                memberChoice.getItems().add(member);
                memberChoice.setId(member.getName());

            }


            riskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            impactColumn.setCellValueFactory(new PropertyValueFactory<>("impact"));
            probColumn.setCellValueFactory(new PropertyValueFactory<>("probability"));

            taskColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            startWeekColumn.setCellValueFactory(new PropertyValueFactory<>("startWeek"));
            endWeekColumn.setCellValueFactory(new PropertyValueFactory<>("endWeek"));


            taskTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

            ObservableList<Risk> currentRisks = FXCollections.observableArrayList();
            for (Risk risk : risks) {
                currentRisks.add(risk);
            }

            ObservableList<Task> currentTasks = FXCollections.observableArrayList();
            for (Task task: tasks){
                currentTasks.add(task);
            }

            taskTable.setItems(currentTasks);
            table.setItems(currentRisks);

        }catch (NullPointerException e){
            System.out.println(e.getMessage());
        }

    }
    @FXML
    void addRiskBtnClicked(ActionEvent event) throws IOException {

        String riskName = riskInput.getText();
        double prob = Double.parseDouble(probInput.getText());
        double impact = Double.parseDouble(impactInput.getText());


        project.createRisk(riskName, prob, impact);

        ObservableList<Risk> currentRisks = FXCollections.observableArrayList();
        for (Risk risk : risks) {
            currentRisks.add(risk);
        }
        table.setItems(currentRisks);
        riskInput.clear();
        probInput.clear();
        impactInput.clear();


    }
    @FXML
    void deleteRiskBtnClicked(ActionEvent event){
        try {
            ObservableList<Risk> riskSelected, allRisks;
            allRisks = table.getItems();
            riskSelected = table.getSelectionModel().getSelectedItems();

            project.removeRisk(table.getSelectionModel().getSelectedItem());

            riskSelected.forEach(allRisks::remove);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void confirmTask(ActionEvent event) {

        Member memberToAdd;
        memberToAdd = memberChoice.getSelectionModel().getSelectedItem();

        int startWeek = Integer.parseInt(this.startWeek.getText());
        int endWeek = Integer.parseInt(this.endWeek.getText());
        double cost = Double.parseDouble(this.cost.getText());
        String taskName = taskNameInput.getText();
        boolean completed;

        if (completeRadioBtn.isSelected()){
            completed = true;
        }
        else{
            completed = false;
        }
        if (memberToAdd == null) {
            project.createTask(taskName, startWeek, endWeek, cost, completed);
        }

        else{
            project.createTask(memberToAdd, taskName, startWeek, endWeek, cost, completed);
        }
        ObservableList<Task> currentTasks = FXCollections.observableArrayList();

        for (Task task: tasks){
            currentTasks.add(task);
        }

        taskTable.setItems(currentTasks);

        this.taskNameInput.clear();
        this.startWeek.clear();
        this.endWeek.clear();
        this.cost.clear();
        this.completeRadioBtn.setSelected(false);
        this.notCompleteRadioBtn.setSelected(false);

    }
    @FXML
    void deleteTask(ActionEvent event){
        try {
            ObservableList<Task> taskSelected, allTasks;
            allTasks = taskTable.getItems();
            taskSelected = taskTable.getSelectionModel().getSelectedItems();

            project.removeTask(taskTable.getSelectionModel().getSelectedItem());

            taskSelected.forEach(allTasks::remove);

        } catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}