package backend;

import backend.Task;

import java.util.ArrayList;

public class Calculations {

    private ArrayList<Task> tasks;

    public Calculations(){

    }




    public double ScheduleVariance(){
        double completedCost = 0;
        double unCompletedCost =0;
        for (Task task : tasks){
            if (task.isCompleted()){
                completedCost=+ task.getCost();
            }
            else {
                unCompletedCost =+ task.getCost();
            }
        }
        return completedCost - unCompletedCost;
    }


    public double earnedValueCalc(double budget){
        int numOfTasks = 0;
        int completedTasks = 0;
        double percentageWorkDone;
        for (Task task : tasks){
            if (task.isCompleted()){
                completedTasks++;
            }
            numOfTasks++;

        }
        try {
            percentageWorkDone = completedTasks / numOfTasks;
        }
        catch (ArithmeticException exception){
            System.out.println("Error: no registered tasks");
             percentageWorkDone = 0;
        }
        try {
            return percentageWorkDone * budget;

        }
        catch(ArithmeticException exception) {
            System.out.println("Error: no work was done or the project does not have a budget");

        }
        return 0;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}