import java.util.Random;

public class Chromosome {
    private double gene;  // The value of x in our example problem
    private double fitness;  // Fitness score based on the function f(x)

    // Constructor: Randomly initialize the gene within a specified range
    public Chromosome(double minGeneValue, double maxGeneValue) {
        Random random = new Random();
        this.gene = minGeneValue + (maxGeneValue - minGeneValue) * random.nextDouble();
        this.fitness = calculateFitness();
    }

    // Constructor: Directly initialize the gene with a specified value
    public Chromosome(double gene) {
        this.gene = gene;
        this.fitness = calculateFitness();
    }

    // Fitness function: Maximize f(x) = x^2
    private double calculateFitness() {
        return Math.pow(this.gene, 2);  // Example function f(x) = x^2
    }

    // Getters
    public double getGene() {
        return gene;
    }

    public double getFitness() {
        return fitness;
    }

    // Mutate the gene by a random amount
    public void mutate(double mutationRate, double minGeneValue, double maxGeneValue) {
        Random random = new Random();
        if (random.nextDouble() < mutationRate) {
            double mutationAmount = (random.nextDouble() - 0.5) * 2 * (maxGeneValue - minGeneValue);
            this.gene = Math.max(minGeneValue, Math.min(maxGeneValue, this.gene + mutationAmount));
            this.fitness = calculateFitness();  // Recalculate fitness after mutation
        }
    }

    // Crossover with another chromosome to produce a child
    public Chromosome crossover(Chromosome otherParent) {
        Random random = new Random();
        double newGene = (this.gene + otherParent.getGene()) / 2;  // Simple average crossover
        return new Chromosome(newGene);
    }

    @Override
    public String toString() {
        return String.format("Gene: %.4f, Fitness: %.4f", gene, fitness);
    }
}
