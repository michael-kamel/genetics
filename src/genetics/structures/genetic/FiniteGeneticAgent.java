package genetics.structures.genetic;

import genetics.structures.general.Pair;


public abstract class FiniteGeneticAgent extends GeneticAlgorithmAgent
{
	private Chromosome result;
	private double tolerance;
	
	public FiniteGeneticAgent(int sampleSize, int chromosomesSize, double tolerance, double crossOverRate, double mutationRate)
	{
		super(sampleSize, chromosomesSize, crossOverRate, mutationRate);
		this.tolerance = tolerance;
	}
	
	public double getTolerance()
	{
		return tolerance;
	}
	public Chromosome getResult() 
	{
		return result;
	}
	protected void runCycle()
	{
		for(Pair<Chromosome, Double> pair : getSample())
			if(Math.abs(1 - calculateScore(pair.getElement0())) < getTolerance())
			{
				result = pair.getElement0();
				return;
			}
		regenerate();
	}
	public void findSolution()
	{
		while(result == null)
			runCycle();
	}
}
