package genetics.structures.genetic;

import genetic.algorithms.general.RouletteWheel;
import genetics.structures.general.Pair;

import java.util.ArrayList;
import java.util.Random;

public abstract class GeneticAlgorithmAgent 
{
	private double crossOverRate;
	private double mutationRate;
	private Pair<Chromosome, Double>[] sample;
	private int sampleSize;
	private int chromosomesSize;
	private int generationsPassed = 0;
	
	public GeneticAlgorithmAgent(int sampleSize, int chromosomesSize, double crossOverRate, double mutationRate)
	{
		this.sampleSize = sampleSize;
		this.sample = new Pair[sampleSize];
		this.chromosomesSize = chromosomesSize;
		this.crossOverRate = crossOverRate;
		this.mutationRate = mutationRate;
	}
	
	public double getCrossOverRate() 
	{
		return crossOverRate;
	}
	public double getMutationRate() 
	{
		return mutationRate;
	}
	public void setCrossOverRate(double crossOverRate)
	{
		this.crossOverRate = crossOverRate;
	}
	public void setMutationRate(double mutationRate) 
	{
		this.mutationRate = mutationRate;
	}
	public Pair<Chromosome, Double>[] getSample()
	{
		return sample;
	}
	public int getSampleSize() 
	{
		return sampleSize;
	}
	public int getChromosomesSize() 
	{
		return chromosomesSize;
	}
	public int getGenerationsPassed() 
	{
		return generationsPassed;
	}

	protected void regenerate()
	{
		Pair<Chromosome, Double>[] newGeneration = new Pair[getSampleSize()];
		for(int i = 0; i < getSampleSize(); i+=2)
		{
			Random rnd = new Random();
			Pair<Chromosome, Double>[] cross = RouletteWheel.getRandomWeigthedElements(getSample(), 2);
			
			if(Math.random() < getCrossOverRate())
			{
				Chromosome chromo1 = cross[0].getElement0();
				Chromosome chromo2 = cross[1].getElement0();
				chromo1.crossOver(chromo2, rnd.nextInt(getChromosomesSize()));
				if(Math.random() < getMutationRate())
					chromo1.getGenome().get(rnd.nextInt(chromo1.getGenome().size())).mutateGene();
				if(Math.random() < getMutationRate())
					chromo2.getGenome().get(rnd.nextInt(chromo2.getGenome().size())).mutateGene();
				newGeneration[i] = Pair.createPair(chromo1, calculateScore(chromo1));
				newGeneration[i+1] = Pair.createPair(chromo2, calculateScore(chromo2));
			}
			else
			{
				newGeneration[i] = cross[0];
				newGeneration[i+1] = cross[1];
			}
		}
		//this.sample = sample;
		generationsPassed++;
	}
	protected abstract double calculateScore(Chromosome c);
	protected abstract void runCycle();
	protected abstract ArrayList<Mutable> generateSample();
	public <T extends Mutable> void intializeSample(ArrayList<T> objectsList)
	{
		for(int i = 0; i < sampleSize; i++)
		{
			Chromosome c = new Chromosome(chromosomesSize);
			for(int x = 0; x < chromosomesSize; x++)
			{
				Mutable geneContent = objectsList.get(x);
				geneContent = geneContent.clone();
				geneContent.mutate();
				c.addGene(geneContent);
			}
			sample[i] = Pair.createPair(c, calculateScore(c));
		}
	}
}