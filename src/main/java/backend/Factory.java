package backend;

import java.util.ArrayList;

public class Factory {
    public Factory(){

    }

    public Member createMember(String name, int memberID, double salary){
        return new Member(name, memberID, salary);
    }

    public Task createTask(int taskID,int memberID,String name, int startWeek, int endWeek, double cost, double budget, Boolean completed){
        return new Task(taskID,memberID,name, startWeek, endWeek, cost, budget, completed);
    }

    public Task createTask(){
        return new Task();
    }

    public Task createTask(int ID, String name, int startWeek, int endWeek, double cost, double budget, boolean completed){

        return new Task(ID, name, startWeek, endWeek, cost, budget, completed);
    }

    public Risk createRisk(String name, double probability, double impact){
        return new Risk(name, probability, impact);
    }

    public Calculations createCalculations(ArrayList<Task> tasks){
        return new Calculations(tasks);
    }
}
