package backend.backbone;

import java.util.ArrayList;

public class Member {
    private String name;
    private String ID;
    private double salary;
    private ArrayList<Task> tasks;


    public Member (String name, String ID, double salary){
    this.name =name;
    this.ID = ID;
    this.salary = salary;
    this.tasks = new ArrayList<>();
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
    }
    public void removeTask(Task task){
        tasks.remove(task);
    }

    public int timeSpent(){
        int timeSpent = 0;
        for(Task task : this.tasks){
            timeSpent += task.getEndWeek() - task.getStartWeek();
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


}
