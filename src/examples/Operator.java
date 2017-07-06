package examples;

import genetics.structures.genetic.Mutable;

import java.util.Random;

public class Operator implements Mutable, Cloneable
{
	private char value;
	
	public Operator()
	{
		value = '+';
	}
	public Operator(char value)
	{
		this.value = value;
	}
	public void mutate() 
	{
		Random rnd = new Random();
		switch(rnd.nextInt(4))
		{
		case 0: value = '+'; break;
		case 1: value = '-'; break;
		case 2: value = '*'; break;
		case 3: value = '/'; 
		}
	}

	public char getValue() 
	{
		return value;
	}
	
	public String toString()
	{
		return Character.toString(value);
	}
	
	public Operator clone()
	{
		return new Operator(value);
	}
}

