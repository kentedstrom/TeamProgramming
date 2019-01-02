package backend;

import java.util.ArrayList;

public class Factory {
    public Factory(){

    }

    public Member createMember(String name, String Id, double salary){
        return new Member(name, Id, salary);
    }

    public Task createTask(Member member,String name, int startWeek, int endWeek, double cost, Boolean completed){
        return new Task(member,name, startWeek, endWeek, cost, completed);
    }

    public Task createTask(){

        return new Task("", -1, -1, -1, false);
    }

    public Task createTask(String name, int startWeek, int endWeek, double cost, boolean completed){

        return new Task(name, startWeek, endWeek, cost, completed);
    }

    public Risk createRisk(String name, double probability, double impact){
        return new Risk(name, probability, impact);
    }

    public Calculations createCalculations(ArrayList<Task> tasks){
        return new Calculations(tasks);
    }
}
