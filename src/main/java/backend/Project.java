package backend;

import java.util.ArrayList;

public class Project {
    private String name;
    private ArrayList<Member> members;
    private ArrayList<Task> tasks;
    private ArrayList<Risk> risks;
    private Calculations schedule;
    private Factory factory;
    private double budget;

    public Project() {
        this.name = "";
        this.budget = 0;
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.schedule = new Calculations(tasks);
        this.factory = new Factory();
    }

    public Project(String name, double budget) {
        this.budget = budget;
        this.name = name;
        this.members = new ArrayList<>();
        this.tasks = new ArrayList<>();
        this.risks = new ArrayList<>();
        this.schedule = new Calculations(tasks);
        this.factory = new Factory();
    }


    public void createMember(String name, String Id, double salary) {
        members.add(this.factory.createMember(name, Id, salary));
    }

    public void removeMember(Member memberToRemove) {
        members.remove(memberToRemove);
    }

    public void createTask(String name, int startWeek, int endWeek, double cost, boolean completed) {
        tasks.add(factory.createTask(name, startWeek, endWeek, cost, completed));
    }

    public void createTask(Member member, String name, int startWeek, int endWeek, double cost, boolean completed) {
        tasks.add(factory.createTask(member, name, startWeek, endWeek, cost, completed));
    }

    public void createTask() {
        tasks.add(factory.createTask());
    }

    public void removeTask(Task tasToRemove) {
        tasks.remove(tasToRemove);
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


    // member stuff below
    public void addMembers(Member member) {
        members.add(member);
    }

    public void removeMembers(Member member) {
        members.remove(member);
    }

    public Member searchMember(String id) {
        for (Member member : members) {
            if (member.compare(id)) {
                return member;
            }
        }
        return null;
    }
}