package examples;

import genetic.algorithms.general.NormalDistribution;
import genetics.structures.genetic.Chromosome;
import genetics.structures.genetic.FiniteGeneticAgent;
import genetics.structures.genetic.Mutable;

import java.util.ArrayList;
import java.util.Stack;

public class Example1 extends FiniteGeneticAgent
{
	public static int v = 122;
	public static final double BELL_WIDTH = 200;
	public Example1()
	{
		super(200, 7, Double.MIN_VALUE, 0.7, 0.001);
	}
	public static void main(String[] args)
	{
		Example1 t1 = new Example1();
		t1.intializeSample(t1.generateSample());
		t1.findSolution();
		Chromosome c = t1.getResult();
		printInFix(c);
		System.out.println();
		System.out.println("Generations Passed " + t1.getGenerationsPassed());
	}
	protected double calculateScore(Chromosome c) 
	{
		Stack<Mutable> s = new Stack<Mutable>();
		for(int i = 0; i < c.getGenome().size(); i++)
		{
			Mutable m = c.getGenome().get(i).getGeneContent();
			if(m instanceof Number)
			{
				s.push(m);
			}
			else
			{
				Number n2 = (Number)s.pop();
				if(n2.getValue() == 0 && ((Operator)m).getValue() == '/')
					return 0.0;
				Number n1 = (Number)s.pop();
				Number nn = new Number(Number.evaluate(n1, n2, (Operator) m));
				s.push(nn);
			}
		}
		Number res = (Number)s.pop();
		double val;
		if(res.getValue() == v)
			val = 1.0;
		else
			val = NormalDistribution.calculateNormalValue(res.getValue(), v, BELL_WIDTH);
		return val;
	}
	
	protected ArrayList<Mutable> generateSample()
	{
		ArrayList<Mutable> ret = new ArrayList<Mutable>();
		ret.add(new Number());
		ret.add(new Number());
		ret.add(new Number());
		ret.add(new Operator());
		ret.add(new Operator());
		ret.add(new Number());
		ret.add(new Operator());
		return ret;
	}
	private static void printInFix(Chromosome c)
	{
		Stack<String> s = new Stack<String>();
		for(int i = 0; i < c.getGenome().size(); i++)
		{
			Mutable m = c.getGenome().get(i).getGeneContent();
			if(m instanceof Number)
			{
				s.push(m.toString());
			}
			else
			{
				String n2 = s.pop();
				String n1 = s.pop();
				s.push("(" + n1 + " " + m + " " + n2 + ")");
			}
		}
		while(!s.isEmpty())
		{
			System.out.print(s.pop() + " ");
		}
		System.out.print("= " + v);
	}
	
}
