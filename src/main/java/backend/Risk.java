package backend;


public class Risk implements Comparable<Risk> {
    private String name;
    private double probability;
    private double impact;

    public Risk(String name, double probability, double impact){
        this.name = name;
        this.probability = probability;
        this.impact = impact;
    }
    public Risk (){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public double getImpact() {
        return impact;
    }

    public void setImpact(double impact) {
        this.impact = impact;
    }

    // ------------- comparing by impact ----------
    @Override
    public int compareTo(Risk otherRisk){
        if(this.impact > otherRisk.getImpact()){
            return 1;
        }else if(this.impact == otherRisk.getImpact()){
            return 0;
        }else{
            return -1;
        }
    }

}
