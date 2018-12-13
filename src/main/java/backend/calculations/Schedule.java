package backend.calculations;

import backend.backbone.Task;
import backend.backbone.Project;

import java.util.ArrayList;

public class Schedule {

    private ArrayList<Task> tasks;


    public Schedule(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }


    public double ScheduleVariance(Project project){
        double completedCost = 0;
        double unCompletedCost =0;
        for (Task task : project.getTasks()){
            if (task.isCompleted()){
                completedCost=+ task.getCost();
            }
            else {
                unCompletedCost =+ task.getCost();
            }
        }
        return completedCost - unCompletedCost;
    }
    //change from Project project to list of tasks
    public double earnedValueCalc(Project project){
        int numOfTasks = 0;
        int completedTasks = 0;
        double percentageWorkDone;
        for (Task task : project.getTasks()){
            if (task.isCompleted()){
                completedTasks++;
            }
            numOfTasks++;

        }
        try {
            percentageWorkDone = completedTasks / numOfTasks;
        }
        catch (ArithmeticException exception){
            System.out.println("Error, no registered tasks");
             percentageWorkDone = 0;
        }
        try {
            return percentageWorkDone * project.getBudget();

        }
        catch(ArithmeticException exception) {
            System.out.println("Error, no work was done or the project does not have a budget");

        }
        return 0;
    }

}