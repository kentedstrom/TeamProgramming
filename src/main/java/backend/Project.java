package backend;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
    private String name;
    private ArrayList<Member> members;
    private ArrayList<Task> tasks;
    private ArrayList<Risk> risks;
    private Calculations schedule;
    private Factory factory;
    private double budget;
    private Calendar calendar;
    protected static int currentWeek;

    public Project() {
        this.name = "";
        this.budget = 0;
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.schedule = new Calculations(tasks);
        this.factory = new Factory();
        this.calendar = new GregorianCalendar();
    }

    public Project(String name, double budget) {
        this.budget = budget;
        this.name = name;
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.schedule = new Calculations(tasks);
        this.factory = new Factory();
        this.calendar = new GregorianCalendar();
    }


    public void createMember(String name, String Id, double salary) {
        for(Member member : members){
            if(Id.equals(member.getID())){
                return;
            }
        }
        members.add(this.factory.createMember(name, Id, salary));
    }

    // when a member is removed, remove their name from all tasks they were involved in
    public void removeMember(Member memberToRemove) {
        ArrayList<Task> removedMembersTasks = memberToRemove.getTasks();
        for (Task taskInProject: this.tasks) {
            if(taskInProject.equals(removedMembersTasks)){
                taskInProject.removeMember(memberToRemove.getName());
            }
        }

        members.remove(memberToRemove);
    }

    public Member searchMember(String id) {
        for (Member member : members) {
            if (member.compare(id)) {
                return member;
            }
        }
        return null;
    }
    public int getCurrentWeek(){
        return currentWeek;
    }
    public void setCurrentWeek(int week){
        currentWeek = week;
    }

    public void createTask(String name, int startWeek, int endWeek, double cost, boolean completed) {
        tasks.add(factory.createTask(name, startWeek, endWeek, cost, completed));
    }

    public void createTask(String memberName, String name, int startWeek, int endWeek, double cost, boolean completed) {
        Task newTask = factory.createTask(memberName, name, startWeek, endWeek, cost, completed);
        tasks.add(newTask);
        // the section below could be rewritten with search through member ID
        for (Member member: this.members) {
            if(member.getName().equals(memberName)){
                member.addTask(newTask);
            }
        }
    }

    public void createTask() {
        tasks.add(factory.createTask());
    }

    public void removeTask(Task taskToRemove) {
        ArrayList<String> membersToAdjust = taskToRemove.getListOfMemberNames();
        for (Member member: this.members) {
            if(member.getName().equals(membersToAdjust)){
                member.removeTask(taskToRemove);
            }
        }
        tasks.remove(taskToRemove);
    }

    public void createRisk(String name, double probability, double impact) {
        risks.add(factory.createRisk(name, probability, impact));
    }

    public void removeRisk(Risk riskToRemove) {
        risks.remove(riskToRemove);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Risk> getRisks() {
        return risks;
    }

    public void setRisks(ArrayList<Risk> risks) {
        this.risks = risks;
    }

    public Calculations getSchedule() {
        return schedule;
    }

    public void setSchedule(Calculations schedule) {
        this.schedule = schedule;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

}