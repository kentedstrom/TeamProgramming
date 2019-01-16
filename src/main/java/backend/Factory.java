package backend;

import java.util.ArrayList;

public class Factory {
    public Factory(){

    }

    public Member createMember(String name, String memberID, double salary){
        return new Member(name, memberID, salary);
    }

    public Task createTask(int taskID,String name, String memberID, String memberName, int startWeek, int endWeek, double cost, double budget, Boolean completed, int weekNow){
        return new Task(taskID,name, memberID,memberName, startWeek, endWeek, cost, budget, completed,weekNow);
    }

    public Task createTask(){
        return new Task();
    }

    public Task createTask(int ID, String name, int startWeek, int endWeek, double cost, double budget, boolean completed, int weekNow){

        return new Task(ID, name, startWeek, endWeek, cost, budget, completed,weekNow);
    }

    public Risk createRisk(String name, double probability, double impact){
        return new Risk(name, probability, impact);
    }

    public Calculations createCalculations(ArrayList<Task> tasks){
        return new Calculations(tasks);
    }
}
