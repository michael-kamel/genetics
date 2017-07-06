package examples;

import genetics.structures.genetic.Mutable;

import java.util.Random;

public class Number implements Mutable, Cloneable
{
	private int value;
	
	public Number()
	{
		value = 0;
	}
	public Number(int value)
	{
		this.value = value;
	}
	public void mutate() 
	{
		Random rnd = new Random();
		value = rnd.nextInt(10);
	}
	public void setValue(int value)
	{
		this.value = value;
	}
	public int getValue()
	{
		return value;
	}	
	
	public String toString()
	{
		return Integer.toString(value);
	}
	public Number clone()
	{
		return new Number(value);
	}
	
	public static int evaluate(Number num1, Number num2, Operator operator)
	{
		int val1 = num1.getValue();
		int val2 = num2.getValue();
		switch(operator.getValue())
		{
		case '+': return val1+val2;
		case '-': return val1-val2;
		case '/': return val1/val2;
		case '*': return val1*val2;
		default : return val1+val2;
		}
	}
}
