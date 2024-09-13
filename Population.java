import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Population {
    private ArrayList<Chromosome> chromosomes;
    private int populationSize;

    // Constructor: Initializes a population of random chromosomes
    public Population(int populationSize, double minGeneValue, double maxGeneValue) {
        this.populationSize = populationSize;
        this.chromosomes = new ArrayList<>();
        for (int i = 0; i < populationSize; i++) {
            chromosomes.add(new Chromosome(minGeneValue, maxGeneValue));
        }
    }

    // Evolve the population for one generation
    public void evolve(double mutationRate, double minGeneValue, double maxGeneValue) {
        // Select the fittest individuals
        selectFittest();

        // Create the next generation using crossover
        ArrayList<Chromosome> newGeneration = new ArrayList<>();
        Random random = new Random();
        while (newGeneration.size() < populationSize) {
            Chromosome parent1 = chromosomes.get(random.nextInt(chromosomes.size()));
            Chromosome parent2 = chromosomes.get(random.nextInt(chromosomes.size()));
            Chromosome child = parent1.crossover(parent2);
            child.mutate(mutationRate, minGeneValue, maxGeneValue);
            newGeneration.add(child);
        }

        this.chromosomes = newGeneration;
    }

    // Select the top half of the population based on fitness
    private void selectFittest() {
        Collections.sort(chromosomes, Comparator.comparingDouble(Chromosome::getFitness).reversed());
        chromosomes = new ArrayList<>(chromosomes.subList(0, populationSize / 2));
    }

    // Get the best chromosome in the population
    public Chromosome getBestChromosome() {
        return Collections.max(chromosomes, Comparator.comparingDouble(Chromosome::getFitness));
    }

    // Get the average fitness of the population
    public double getAverageFitness() {
        return chromosomes.stream().mapToDouble(Chromosome::getFitness).average().orElse(0);
    }
}
