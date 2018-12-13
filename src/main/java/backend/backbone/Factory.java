package backend.backbone;

import backend.calculations.Risk;
import backend.calculations.Schedule;

import java.util.ArrayList;

public class Factory {
    public Factory(){

    }

    public Member createMember(String name, String Id, double salary){
        return new Member(name, Id, salary);
    }

    public Task createTask(ArrayList<Member> members, int startWeek, int endWeek, double cost, Boolean completed){
        return new Task(members, startWeek, endWeek, cost, completed);
    }

    public Task createTask(){
        ArrayList<Member> members = new ArrayList<>();
        return new Task(members, -1, -1, -1, false);
    }

    public Task createTask(int startWeek, int endWeek, double cost, boolean completed){
        ArrayList<Member> members = new ArrayList<>();
        return new Task(members, startWeek, endWeek, cost, completed);
    }

    public Risk createRisk(String name, double probability, double impact){
        return new Risk(name, probability, impact);
    }

    public Schedule createSchedule(){
        return new Schedule();
    }
}
