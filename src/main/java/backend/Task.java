package backend;

import java.util.ArrayList;

public class Task {

    private ArrayList<String> memberNames;
    private String name;
    private int startWeek;
    private int endWeek;
    private double budget;
    private boolean isCompleted;
    private String status;

    public Task(){
        this.memberNames = new ArrayList<>();
        this.startWeek = 0;
        this.endWeek = 0;
        this.budget = 0;
        this.isCompleted = false;
        this.status = "In progress";
    }


    public Task(String name, int startWeek, int endWeek, double budget, boolean isCompleted) {
        this.memberNames = new ArrayList<>();
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.budget = budget;
        this.isCompleted = isCompleted;
        this.name = name;
        if (isCompleted){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }
    }


    // task has only the name of the memberNames not the memberNames themselves, otherwise infinite recursion
    public Task(String memberName,String name, int startWeek, int endWeek, double budget, boolean isCompleted){
        this.memberNames = new ArrayList<>();
        this.memberNames.add(memberName);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.budget = budget;
        this.isCompleted = isCompleted;
        this.name = name;
        if (isCompleted){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }

    }
    public void addMember(String memberName){
        this.memberNames.add(memberName);
    }
    public void removeMember(String memberName){
        this.memberNames.remove(memberName);
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
    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
        if (completed){
            this.status = "Completed";
        }
        else{
            this.status = "In progress";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return this.name;
    }
    public String getStatus(){
        return this.status;
    }

    public ArrayList<String> getListOfMemberNames(){
        return this.memberNames;
    }
}
