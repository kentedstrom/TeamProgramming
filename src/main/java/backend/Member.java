package backend;

import javax.swing.event.InternalFrameEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Member {
    private String name;
    private int ID;
    private double salary;
    private ArrayList<Integer> taskIDs;
    private Calendar calendar;
    // map to store the time spent on each task
    private HashMap<Integer,Double> timeSpentPerTask;

    public Member(){
        this.ID = 0;
        this.name = "";
        this.salary = 0.0;
        this.taskIDs = new ArrayList<>();
        this.timeSpentPerTask = new HashMap<>();
        this.calendar = new GregorianCalendar();
    }

    public Member (String name, int ID, double salary){
    this.name = name;
    this.ID = ID;
    this.salary = salary;
    this.taskIDs = new ArrayList<>();
    this.timeSpentPerTask = new HashMap<>();
    this.calendar = new GregorianCalendar();
    }

    public boolean compare(int ID) {
        if (this.ID == ID) {
            return true;
        }
        return false;
    }

    public void addTask(Integer taskID){
        taskIDs.add(taskID);
        this.timeSpentPerTask.put(taskID,Double.valueOf(0.0));
    }

    public void removeTask(Integer taskID){
        taskIDs.remove(taskID);
        this.timeSpentPerTask.remove(taskID);
    }

    public void addTimeToTask(Integer taskThatTimeSpentOn, double time){
        if(this.timeSpentPerTask.containsKey(taskThatTimeSpentOn)){
            Double timeSoFar = this.timeSpentPerTask.get(taskThatTimeSpentOn);
            Double timeNow = timeSoFar + Double.valueOf(time);
            this.timeSpentPerTask.replace(Integer.valueOf(taskThatTimeSpentOn),timeNow);
        }else{
            taskIDs.add(taskThatTimeSpentOn);
            this.timeSpentPerTask.put(taskThatTimeSpentOn,time);
        }

    }

    public void removeTimeFromTask(Integer taskThatTimeSpentOn, double time){
        if(this.timeSpentPerTask.containsKey(taskThatTimeSpentOn)){
            Double timeSoFar = this.timeSpentPerTask.get(taskThatTimeSpentOn);
            Double timeNow = timeSoFar - Double.valueOf(time);
            if(timeNow>= 0){
                this.timeSpentPerTask.replace(Integer.valueOf(taskThatTimeSpentOn),timeNow);
            }else{
                this.timeSpentPerTask.replace(Integer.valueOf(taskThatTimeSpentOn),Double.valueOf(0.0));
            }

        }else{
            taskIDs.add(taskThatTimeSpentOn);
            this.timeSpentPerTask.put(taskThatTimeSpentOn,Double.valueOf(0.0));
        }
    }

    public Double getTimeSpentPerTask(Integer taskID){
        if(timeSpentPerTask.containsKey(taskID)){
            return this.timeSpentPerTask.get(taskID);
        }
        return 0.0;
    }

    public HashMap<Integer, Double> getTimeSpentAllTasks() {
        return this.timeSpentPerTask;
    }

    // get the time spent from the tasks where time was spent
    public double timeSpent(){
        Double totalTime = 0.0;
        for (Integer taskID: this.timeSpentPerTask.keySet()) {
            totalTime += timeSpentPerTask.get(taskID);
        }
        return (double) totalTime;
    }

    public ArrayList<Integer> getTasks(){
        return this.taskIDs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
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
