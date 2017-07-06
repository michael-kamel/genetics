package genetics.structures.genetic;

public interface Mutable 
{
	void mutate();
	Mutable clone();
}