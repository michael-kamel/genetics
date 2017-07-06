package genetics.structures.genetic;

import genetics.structures.general.Pair;

public abstract class InfiniteGeneticAgent extends GeneticAlgorithmAgent 
{
	private Chromosome currentBest;
	private double currentBestScore;
	
	public InfiniteGeneticAgent(int sampleSize, int chromosomesSize, double crossOverRate, double mutationRate)
	{
		super(sampleSize, chromosomesSize, crossOverRate, mutationRate);
		currentBestScore = -1;
	}
	
	public Chromosome getCurrentBest()
	{
		return currentBest;
	}
	public double getCurrentBestScore()
	{
		return currentBestScore;
	}
	protected void runCycle()
	{
		for(Pair<Chromosome, Double> pair : getSample())
		{
			double score = calculateScore(pair.getElement0());
			if(score > currentBestScore)
			{
				Chromosome best = new Chromosome(getChromosomesSize());
				for(int i = 0; i < getChromosomesSize(); i++)
				{
					Mutable geneContent = pair.getElement0().getGenome().get(i).getGeneContent();
					geneContent = geneContent.clone();
					best.addGene(geneContent);
				}
				currentBest = best;
				currentBestScore = score;
			}
		}
		regenerate();
	}
	public void runNCycles(int cycles)
	{
		for(int i = 0; i <= cycles; i++)
			runCycle();
	}
	
}
