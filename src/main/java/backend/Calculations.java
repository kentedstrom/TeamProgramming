package backend;

import backend.Task;

import java.util.*;

public class Calculations {

    private ArrayList<Task> tasks;
    private Calendar calendar;

    public Calculations(){}

    public Calculations(ArrayList<Task> tasks){
        this.tasks = tasks;
        this.calendar = new GregorianCalendar();
    }

    private int endOfTasks(){
        // store the end of the last task
        int endOfTasks = 0;
        for (Task task: this.tasks) {
            if(task.getEndWeek() > endOfTasks){
                endOfTasks = task.getEndWeek();
            }
        }
        return endOfTasks;
    }

    // calculate task overlap for AreaChart
    public int[] calculateWorkLoadPerWeek(){

        int endOfTasks = endOfTasks();
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

    // calculate cost per week
    public double[] calculateCostPerWeek(ArrayList<Member> members){

        int endOfTasks = endOfTasks();

        // store the cost for task per week
        double[] taskCostPerWeek = new double[endOfTasks+2];

        for (Task task: this.tasks) {
            for (int i = 0; i < endOfTasks+2; i++) {
                if(task.getStartWeek()<=i && task.getEndWeek()>=i){
                    // adding task's cost
                    taskCostPerWeek[i] += task.getCost();
                    for (Member member: members) {
                        // adding time spent by member * member's salary
                        taskCostPerWeek[i] += member.getTimeSpentPerTask(task.getID())*member.getSalary();
                    }
                }
            }
        }
        return taskCostPerWeek;
    }

    // calculate cumulative planned value
    public double[] calculatePlannedValue(ArrayList<Member> members){
        int endOfTasks = endOfTasks();

        // store the budget for task per week
        double[] taskBudgetPerWeek = new double[endOfTasks];

        for (Task task: this.tasks) {
            for (int i = 0; i < endOfTasks; i++) {
                if(task.getStartWeek()<=i && task.getEndWeek()>=i){
                    // adding task's budget cumulatively
                    for (int j = i; j < endOfTasks; j++) {
                        taskBudgetPerWeek[j] += task.getBudget();
                    }
                }
            }
        }

        return taskBudgetPerWeek;
    }

    // calculate cumulative actual value
    public double[] calculateActualValue(ArrayList<Member> members){
        // get cost per week
        double[] costPerWeek = calculateCostPerWeek(members);
        double[] cumulativeCost = new double[costPerWeek.length];
        // cumulate it
        for (int i = 0; i < costPerWeek.length; i++) {
            for (int j = i; j < costPerWeek.length; j++) {
                cumulativeCost[j] += costPerWeek[i];
            }
        }
        return  cumulativeCost;
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

    // calculates the money spent on completed and incomplete tasks
    public double[] BudgetStatus(ArrayList<Member> members){
        double[] budgetStatus = new double[2];
        for (Task task: tasks) {
            if(task.isCompleted()){
                budgetStatus[0] += task.getCost();
                for (Member member: members) {
                    budgetStatus[0] += member.getTimeSpentPerTask(task.getID());
                }
            }else{
                budgetStatus[1] += task.getBudget();
                for (Member member: members) {
                    budgetStatus[1] += member.getTimeSpentPerTask(task.getID());
                }
            }
        }
        return budgetStatus;
    }


    // calculates the cost of work done - cost of work scheduled
    public double ScheduleVariance(double EarnedValue, double PlannedValue){
        // if task on time, do nothing, if it took longer than do sg
        return EarnedValue - PlannedValue;
    }

    //calculate cost variance
    public double CostVariance(double EarnedValue, double ActualCost){
        return EarnedValue - ActualCost;
    }


    public double earnedValueCalc(double budget){
        double numOfTasks = 0.0;
        double completedTasks = 0.0;
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
             percentageWorkDone = 0;
        }
        return percentageWorkDone * budget;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}