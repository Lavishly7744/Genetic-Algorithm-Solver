public class Main {
    public static void main(String[] args) {
        // Genetic Algorithm parameters
        int populationSize = 100;
        double mutationRate = 0.1;
        int generations = 100;
        double minGeneValue = -10;
        double maxGeneValue = 10;

        // Initialize population
        Population population = new Population(populationSize, minGeneValue, maxGeneValue);

        // Evolve the population over multiple generations
        for (int generation = 0; generation < generations; generation++) {
            population.evolve(mutationRate, minGeneValue, maxGeneValue);

            // Output the best result for the current generation
            Chromosome bestChromosome = population.getBestChromosome();
            double avgFitness = population.getAverageFitness();
            System.out.printf("Generation %d: Best Fitness = %.4f, Average Fitness = %.4f, Best Gene = %.4f\n",
                    generation, bestChromosome.getFitness(), avgFitness, bestChromosome.getGene());
        }

        // Output the final best solution
        Chromosome bestSolution = population.getBestChromosome();
        System.out.println("\nBest solution found:");
        System.out.println(bestSolution);
    }
}
