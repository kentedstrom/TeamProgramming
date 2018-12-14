package backend;

import java.util.ArrayList;

public class RiskMatrix {
    private ArrayList<Risk> risks;

    public RiskMatrix(){

    }

    public RiskMatrix(ArrayList<Risk> risks){
        this.risks = risks;
    }

    public void addRisk(Risk risk){
        risks.add(risk);
    }

    public void addRisk(String name, double probability, double impact){
        Risk risk = new Risk(name, probability, impact);
        risks.add(risk);
    }
    public void removeRisk(Risk risk){
        risks.remove(risk);
    }
    public void parseRisks(ArrayList <Risk> risks){

    }
}
