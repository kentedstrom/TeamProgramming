package backend;

import java.util.ArrayList;

public class Task {
    ArrayList<Member> listOfMembers;
    private String name;
    private int startWeek;
    private int endWeek;
    private double cost;
    private boolean isCompleted;

    public Task(){
        this.listOfMembers = new ArrayList<Member>();
        this.startWeek = 0;
        this.endWeek = 0;
        this.cost = 0;
        this.isCompleted = false;
    }
    public Task(String name, int startWeek, int endWeek, double cost, boolean isCompleted) {
        listOfMembers = new ArrayList<Member>();
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.isCompleted = isCompleted;
        this.name = name;
    }
    public Task(Member member,String name, int startWeek, int endWeek, double cost, boolean isCompleted){
        listOfMembers = new ArrayList<Member>();
        listOfMembers.add(member);
        this.startWeek = startWeek;
        this.endWeek = endWeek;
        this.cost = cost;
        this.isCompleted = isCompleted;
        this.name = name;
    }
    public void addMember(Member member){
        listOfMembers.add(member);
        member.addTask(this);
    }
    public void removeMember(Member member){
        listOfMembers.remove(member);
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

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
