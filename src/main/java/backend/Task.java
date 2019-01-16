package backend;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Task {

    private ArrayList<String> memberIDs;
    private ArrayList<String> memberNames;
    private int ID;
    private String name;
    private int startWeek;
    private int endWeek;
    private double cost;
    private double budget;
    private boolean completed;
    private String status;
    private int weekOfCompletion;
    private static int BIG_NUMBER = 100;

    public Task(){
        this.ID = 0;
        this.memberIDs = new ArrayList<>();
        this.memberNames = new ArrayList<>();
        this.startWeek = 0;
        this.endWeek = 0;
        this.cost = 0;
        this.budget = 0;
        this.completed = false;
        this.status = "In progress";
        this.weekOfCompletion = 0;
    }


    public Task(int ID,String name, int startWeek, int endWeek, double cost, double budget, boolean isCompleted, int weekNow) {
        this.ID = ID;
        this.name = name;
        this.memberIDs = new ArrayList<>();
        this.memberNames = new ArrayList<>();
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.budget = budget;
        this.completed = isCompleted;
        if (isCompleted){
            this.status = "Completed";
            this.weekOfCompletion = weekNow;
        }
        else{
            this.status = "In progress";
        }
    }


    // task has only the name of the memberNames not the memberNames themselves, otherwise infinite recursion
    public Task(int ID,String name, String memberID, String memberName, int startWeek, int endWeek, double cost, double budget, boolean isCompleted, int weekNow){
        this.ID = ID;
        this.name = name;
        this.memberIDs = new ArrayList<>();
        this.memberIDs.add(memberID);
        this.memberNames = new ArrayList<>();
        this.memberNames.add(memberName);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.budget = budget;
        this.completed = isCompleted;
        if (isCompleted){
            this.status = "Completed";
            this.weekOfCompletion = weekNow;
        }
        else{
            this.status = "In progress";
        }

    }

    public void addMember(String memberID, String memberName){
        this.memberNames.add(memberName);
        this.memberIDs.add(memberID);
    }
    public void removeMember(String memberID, String memberName){
        this.memberNames.remove(memberName);
        this.memberIDs.remove(memberID);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @JsonIgnore
    public void setCompleted(boolean completed, int currentWeek) {
        this.completed = completed;
        if (completed){
            this.status = "Completed";
            setWeekOfCompletion(currentWeek);
        }
        else{
            this.status = "In progress";
            setWeekOfCompletion(BIG_NUMBER);
        }
    }

    public int getWeekOfCompletion(){
        return weekOfCompletion;
    }

    public void setWeekOfCompletion(int weekOfCompletion) {
        this.weekOfCompletion = weekOfCompletion;
    }

    public String toString(){
        return this.name;
    }
    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public String getListOfMemberNames(){
        int counter = 0;
        String allNames = "";
        for (String name: this.memberNames) {
            allNames += name;
            if(counter<this.memberNames.size()){
                allNames += ", ";
            }
            counter += 1;
        }
        return allNames;
    }

    public ArrayList<String> getMemberIDs() {
        return memberIDs;
    }

    public ArrayList<String> getMemberNames() {
        return memberNames;
    }

    @JsonIgnore
    public ArrayList<String> getListOfMemberIDs(){
        return this.memberIDs;
    }


    public boolean compare(Integer taskID){
        if(this.ID ==taskID){
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Task){
            Task otherTask  = (Task) obj;
            if(otherTask.getID() == this.ID){
                return true;
            }
        }
        return false;
    }
    /*
    //takes a project and gets its current week and compares if its larger than the end week and then returns the difference
    public int amIBehind(Project project){
        if (project.getCurrentWeek() > getEndWeek()){
            return project.getCurrentWeek() - getEndWeek();
        }
        else return 0;
    }
    */
}
