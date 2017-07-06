package examples;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JFrame;

import genetics.structures.general.Pair;
import genetics.structures.genetic.Chromosome;
import genetics.structures.genetic.InfiniteGeneticAgent;
import genetics.structures.genetic.Mutable;

public class Example2 extends InfiniteGeneticAgent
{
	private static final int X_SIZE = 1000;
	private static final int Y_SIZE = 1000;
	private static final int CIRCLES_MAX_SIZE = 100;
	private static final double OVERLAP_EFFECT = 200;
	private int rounds;
	private ArrayList<Pair<Circle, Coord>> map;
	private int mapSize = 20;
	
	
	public Example2(int sampleSize, int chromosomesSize, int rounds) 
	{
		super(sampleSize, chromosomesSize, 0.7, 0.005);
		this.rounds = rounds;
	}

	public static void main(String[] args)
	{
		java.awt.EventQueue.invokeLater(new Runnable() 
		{
		    public void run() 
		    {
		    	JFrame frame = new JFrame();
		    	frame.setSize(X_SIZE, Y_SIZE);
		    	frame.setTitle("Biggest Fitting Circle");
		    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
				frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
				JCirclesLabel label = new JCirclesLabel();
				Example2  t2 = new Example2(240, 2, 6000);
				t2.map = new ArrayList<Pair<Circle,Coord>>();
				for(int i = 0; i < t2.mapSize; i++)
				{
					Circle c = new Circle(CIRCLES_MAX_SIZE);
					Coord loc = new Coord(X_SIZE-CIRCLES_MAX_SIZE, Y_SIZE-CIRCLES_MAX_SIZE, CIRCLES_MAX_SIZE, CIRCLES_MAX_SIZE);
					c.mutate();
					do
					{
						loc.mutate();
					}
					while(t2.overlapsAny(c, loc));
					t2.map.add(Pair.createPair(c, loc));
				}
				t2.intializeSample(t2.generateSample());
				
				frame.add(label);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    	frame.setVisible(true);
		    	for(Pair<Circle, Coord> pair : t2.map)
				{
					Pair<Circle, Coord> np = Pair.createPair(pair.getElement0(), pair.getElement1());
					label.addCircle(np);
				}
		    	t2.runNCycles(t2.rounds);
				Circle bc = (Circle) t2.getCurrentBest().getGenome().get(0).getGeneContent();
				Coord bcoord = (Coord) t2.getCurrentBest().getGenome().get(1).getGeneContent();
				Pair<Circle, Coord> best = Pair.createPair(bc, bcoord);
				label.setTarget(best);
				label.revalidate();
				label.repaint();
		    }
		});
	}
	protected double calculateScore(Chromosome c)
	{
		Circle circle = (Circle)c.getGenome().get(0).getGeneContent();
		Coord location = (Coord)c.getGenome().get(1).getGeneContent();
		//normaliseLocation(circle, location);
		double score = circle.getArea();
		if(outOfBounds(circle, location))
			return 0;
		if(overlapsAny(circle, location))
			return score/OVERLAP_EFFECT;
		return score;
	}

	private boolean outOfBounds(Circle circle, Coord location)
	{
		int radius = circle.getRadius();
		int leftBound = location.getX() - radius;
		int rightBound = location.getX() + radius;
		int topBound = location.getY() + radius;
		int bottomBound = location.getY() - radius;
		if((leftBound < 0) || (rightBound > X_SIZE) || (bottomBound < 0) || (topBound > Y_SIZE))
			return true;
		return false;
	}
	private void normaliseLocation(Circle circle, Coord location)
	{
		int radius = circle.getRadius();
		int leftBound = location.getX() - radius;
		int rightBound = location.getX() + radius;
		int topBound = location.getY() + radius;
		int bottomBound = location.getY() - radius;
		if(leftBound < 0)
		{
			leftBound = Math.abs(leftBound);
			location.setX(location.getX() + leftBound);
			circle.setRadius(radius - Math.abs(leftBound));
		}
		if(rightBound > X_SIZE)
		{
			rightBound -= X_SIZE;
			location.setX(location.getX() - rightBound);
			circle.setRadius(radius - rightBound);
		}
		if(bottomBound < 0)
		{
			bottomBound = Math.abs(bottomBound);
			location.setY(location.getY() + bottomBound);
			circle.setRadius(radius - Math.abs(bottomBound));
		}
		if(topBound > Y_SIZE)
		{
			topBound -= Y_SIZE;
			location.setY(location.getY() - topBound);
			circle.setRadius(radius - topBound);
		}
		circle.calculateArea();
	}

	public ArrayList<Mutable> generateSample()
	{
		Circle c = new Circle(X_SIZE/2);
		Coord d = new Coord(X_SIZE, Y_SIZE, 0 ,0);
		ArrayList<Mutable> sample = new ArrayList<Mutable>();
		sample.add(c);
		sample.add(d);
		return sample;
	}
	private boolean overlapsAny(Circle circ, Coord loc)
	{
		for(Pair<Circle, Coord> pair : map)
		{
			double splitDistance = Coord.distance(loc, pair.getElement1());
			double radii = pair.getElement0().getRadius() + circ.getRadius();
			if(splitDistance < radii)
				return true;
		}
		return false;
	}
	private double overlapScore(Circle circ, Coord loc)
	{
		double score = 0;
		for(Pair<Circle, Coord> pair : map)
		{
			double splitDistance = Coord.distance(loc, pair.getElement1());
			double radii = pair.getElement0().getRadius() + circ.getRadius();
			if(splitDistance < radii)
				score += radii - splitDistance;
		}
		return score;
	}

}
