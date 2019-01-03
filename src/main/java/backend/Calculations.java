package backend;

import backend.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Calculations {

    private ArrayList<Task> tasks;

    public Calculations(){}

    public Calculations(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    // calculate task overlap for AreaChart
    public int[] calculateWorkLoadPerWeek(){
        // store the end of the gantt chart, that is the end of the last task
        int endOfTasks = 0;
        for (Task task: this.tasks) {
            if(task.getEndWeek() > endOfTasks){
                endOfTasks = task.getEndWeek();
            }
        }
        // loop though each week and count the number of tasks running
        int[] tasksRunningPerWeek = new int[endOfTasks+2];

        for (Task task: this.tasks) {
            for (int i = 0; i < endOfTasks+2; i++) {
                if(task.getStartWeek()<=i && task.getEndWeek()>=i){
                    tasksRunningPerWeek[i] += 1;
                }
            }
        }
        return tasksRunningPerWeek;
    }

    // calculate the total time of the tasks done so far and how much each person added to it
    public HashMap<String,Double> TimeSpentOnProjectByMember(ArrayList<Member> members){
        // calculate the time each member spent on the project and store in a hashMap
        HashMap<String,Double> timeDistribution = new HashMap<>();
        // store the total time too
        timeDistribution.put("Total",0.0);
        int total = 0;
        for (Member  member: members) {
            // store the total time in the 1st element of the array
            total += member.timeSpent();
            timeDistribution.replace("Total",Double.valueOf(total));
            // store the personal times in the next elements of the array
            timeDistribution.put(member.getName(),Double.valueOf(member.timeSpent()));
        }
        return timeDistribution;
    }

    // calculate how many tasks are finished and how many are unfinished
    public double[] TaskCompletion(){
        double[] taskCompleteness = new double[2];
        for (Task task: tasks) {
            if(task.isCompleted()){
                // if task IS completed it is stored in the first index of the array
                taskCompleteness[0] += 1;
            }else{
                // if the task IS NOT completed it stays in the second index of the array
                taskCompleteness[1] += 1;
            }
        }
        return taskCompleteness;
    }

    public double[] BudgetStatus(){
        double[] budgetStatus = new double[2];
        for (Task task: tasks) {
            if(task.isCompleted()){
                budgetStatus[0] += task.getCost();
            }else{
                budgetStatus[1] += task.getCost();
            }
        }
        return budgetStatus;
    }


    // make use of the budgetstatus function and calculates the cost of work done - cost of work scheduled
    public double ScheduleVariance(){
        double[] costs = BudgetStatus();
        return costs[0]-costs[1];
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
            // where is it expected to be written?
            System.out.println("Error: no registered tasks");
             percentageWorkDone = 0;
        }
        try {
            return percentageWorkDone * budget;

        }
        catch(ArithmeticException exception) {
            // sout to where?
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