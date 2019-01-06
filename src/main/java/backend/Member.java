package backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Member {
    private String name;
    private String ID;
    private double salary;
    private ArrayList<Task> tasks;
    private Calendar calendar;
    // map to store the time spent on each task
    private HashMap<Task,Double> timeSpentPerTask;

    public Member(){
        this.ID = "";
        this.name = "";
        this.salary = 0.0;
        this.tasks = new ArrayList<>();
        this.timeSpentPerTask = new HashMap<>();
        this.calendar = new GregorianCalendar();
    }

    public Member (String name, String ID, double salary){
    this.name = name;
    this.ID = ID;
    this.salary = salary;
    this.tasks = new ArrayList<>();
    this.timeSpentPerTask = new HashMap<>();
    this.calendar = new GregorianCalendar();
    }

    public boolean compare(String ID) {
        if (ID.equals(this.getID())) {
            return true;
        } else {
            return false;
        }
    }

    public void addTask(Task task){
        tasks.add(task);
        this.timeSpentPerTask.put(task,Double.valueOf(0.0));
    }

    public void removeTask(Task task){
        tasks.remove(task);
        this.timeSpentPerTask.remove(task);
    }

    public void addTimeToTask(Task taskThatTimeSpentOn, double time){
        Double timeSoFar = this.timeSpentPerTask.get(taskThatTimeSpentOn);
        Double timeNow = timeSoFar + Double.valueOf(time);
        this.timeSpentPerTask.replace(taskThatTimeSpentOn,timeNow);
    }

    public Double getTimeSpentPerTask(Task task){
        return this.timeSpentPerTask.get(task);
    }

    public HashMap<Task, Double> getTimeSpentAllTasks() {
        return this.timeSpentPerTask;
    }

    // distinguish between completed and incomplete tasks
    public int timeSpent(){
        // if a task is incomplete, the time spent = the time from the beginnign of the task till the current date
        //int currentWeek = calendar.WEEK_OF_YEAR;
        int timeSpent = 0;
        for(Task task : this.tasks){
            timeSpent += task.getEndWeek() - task.getStartWeek();
            /*
            if(task.isCompleted()){
                timeSpent += task.getEndWeek() - task.getStartWeek();
            }else{
                timeSpent += currentWeek - task.getStartWeek();
            }
            */

        }
        return timeSpent;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
