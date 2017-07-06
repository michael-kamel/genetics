package genetic.algorithms.general;

import genetics.structures.general.Pair;

public class RouletteWheel
{
	public static <T> Pair<T, Double>[] getRandomWeigthedElements(Pair<T, Double>[] elements, int size)
	{
		Pair<T, Double>[] ret = new Pair[size];
		double accumulatedWeight = 0.0;
		double[] upperBounds = new double[elements.length];
		double randomProb ;
		for(int i = 0; i < elements.length; i++)
		{
			double probability = elements[i].getElement1().doubleValue();
			accumulatedWeight += probability;
			upperBounds[i] = accumulatedWeight;
		}
		randomProb = Math.random()*accumulatedWeight;
		int count = 0;
		for(int i = 0; i < elements.length && count < size; i++)
		{
			if(randomProb < upperBounds[i])
			{
				ret[count] = elements[i];
				randomProb = Math.random()*accumulatedWeight;
				count++;
				i=0;
			}
		}
		for(int i = count; i < size; i++)
			ret[i] = elements[elements.length - 1];
		return ret;
	}
}