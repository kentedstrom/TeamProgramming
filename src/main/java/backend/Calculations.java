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
        int total = 0;
        for (Member  member: members) {
            // store the total time in the 1st element of the array
            total += member.timeSpent();
            // store the personal times in the next elements of the array
            timeDistribution.put(member.getName(),Double.valueOf(member.timeSpent()));
        }
        timeDistribution.put("Total",Double.valueOf(total));
        return timeDistribution;
    }

    // calculate the cost of each task by adding time spent on each task by members multiplied by member salary + task cost
    public HashMap<Integer,Double> CostOfTasks(ArrayList<Member> members){
        HashMap<Integer,Double> taskCosts = new HashMap<>();
        // calculate total cost for all tasks
        Double total = 0.0;
        for (Member member: members) {
            // loop through the map with tasks and their costs
            for (Integer membersTaskID: member.getTimeSpentAllTasks().keySet()){
                if(!taskCosts.containsKey(membersTaskID)){
                    Double oldCost = taskCosts.get(membersTaskID);
                    // add cost as time spent by member*memberSalary
                    Double newCost = oldCost + member.getTimeSpentPerTask(membersTaskID)*member.getSalary();
                    taskCosts.replace(Integer.valueOf(membersTaskID),newCost);
                    // add cost as time spent by member*memberSalary
                    total += member.getTimeSpentPerTask(membersTaskID)*member.getSalary();
                }else{
                    // add cost as time spent by member*memberSalary
                    taskCosts.put(membersTaskID,member.getTimeSpentPerTask(membersTaskID)*member.getSalary());
                    // get starting cost fo a task
                    double cost = 0.0;
                    for (Task task:tasks) {
                        if(task.getID() == membersTaskID){
                            cost = task.getCost();
                        }
                    }
                    // add cost as time spent by member*memberSalary + taskCost (only once)
                    total += cost + member.getTimeSpentPerTask(membersTaskID)*member.getSalary();
                }
            }
        }
        // store total cost spent
        taskCosts.put(0,total);
        return taskCosts;
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
                budgetStatus[0] += task.getBudget();
            }else{
                budgetStatus[1] += task.getBudget();
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