package genetics.structures.genetic;

import java.util.ArrayList;

public class Chromosome
{
	private ArrayList<Gene<Mutable>> genome;
	private int genesCount;
	
	public Chromosome(int genesCount)
	{
		this.genesCount = genesCount;
		this.genome = new ArrayList<Gene<Mutable>>();
	}
	public Chromosome(ArrayList<Gene<Mutable>> genome, int genesCount)
	{
		this(genesCount);
		this.genome = genome;
	}
	public Chromosome(Mutable[] objects, int genesCount)
	{
		ArrayList<Gene<Mutable>> list = new ArrayList<Gene<Mutable>>();
		for(Mutable e : objects)
			list.add(Gene.createGene(e));
		this.genome = new ArrayList<Gene<Mutable>>();
		this.genome = list;
		this.genesCount = genesCount;
	}
	
	public ArrayList<Gene<Mutable>> getGenome() 
	{
		return genome;
	}
	public int getGenesCount() 
	{
		return genesCount;
	}
	
	public void addGene(Mutable object)
	{
		genome.add(Gene.createGene(object));
	}
	public void crossOver(Chromosome c, int splitIndex)
	{
		for(int i = splitIndex; i < genome.size() && i < c.getGenome().size(); i++)
		{
			Gene<Mutable> tmp = genome.get(i);
			genome.set(i, c.genome.get(i));
			c.genome.set(i, tmp);
		}
	}
	
	public String toString()
	{
		String ret = "{ ";
		for(int i = 0; i < genome.size(); i++)
			ret += genome.get(i).toString()+ ", ";
		return ret + "}";
	}
}
