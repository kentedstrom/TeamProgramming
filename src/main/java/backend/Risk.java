package backend;

public class Risk {
    private String name;
    private double probability;
    private double impact;

    public Risk(String name, double probability, double impact){
        this.name = name;
        this.probability = probability;
        this.impact = impact;
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
}
