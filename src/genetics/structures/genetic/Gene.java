package genetics.structures.genetic;

public class Gene<T extends Mutable> 
{
	private T geneContent;
	
	public Gene(T geneContent)
	{
		this.geneContent = geneContent;
	}

	public T getGeneContent() 
	{
		return geneContent;
	}
	
	public void mutateGene()
	{
		geneContent.mutate();
	}
	public static <K extends Mutable> Gene<K> createGene(K content)
	{
		return new Gene<K>(content);
	}
	
	public String toString()
	{
		return geneContent.toString();
	}
}