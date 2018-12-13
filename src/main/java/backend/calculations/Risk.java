package backend.calculations;

public class Risk {
    private String name;
    private double probability;
    private double impact;

    public Risk(String name, double probability, double impact){
        this.name = name;
        this.probability = probability;
        this.impact = impact;
    }
}
