package examples;

import genetics.structures.genetic.Mutable;
import java.util.concurrent.ThreadLocalRandom;

public class Coord implements Mutable
{
	private int x;
	private int y;
	private int maxX;
	private int maxY;
	private int minX;
	private int minY;
	
	public Coord(int maxX, int maxY, int minX, int minY)
	{
		this.maxX = maxX;
		this.maxY = maxY;
		this.minX = minX;
		this.minY = minY;
	}
	public Coord(int x, int y, int maxX, int maxY, int minX, int minY)
	{
		this(maxX, maxY, minX, minY);
		this.x = x;
		this.y = y;
	}
	
	public int getX() 
	{
		return x;
	}
	public int getY() 
	{
		return y;
	}
	public void setX(int x) 
	{
		this.x = x;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public int getMaxX() 
	{
		return maxX;
	}
	public int getMaxY()
	{
		return maxY;
	}
	public int getMinX() 
	{
		return minX;
	}
	public int getMinY() 
	{
		return minY;
	}
	
	public void mutate()
	{
		this.x = ThreadLocalRandom.current().nextInt(minX, maxX+1);
		this.y = ThreadLocalRandom.current().nextInt(minY, maxY+1);
	}
	public Coord clone()
	{
		return new Coord(x, y ,maxX, maxY, minX, minY);
	}
	public static double distance(Coord c1, Coord c2)
	{
		double dy = Math.abs(c2.getY() - c1.getY());
		double dx = Math.abs(c2.getX() - c1.getX());
		return Math.sqrt((dx*dx) + (dy*dy));
	}
	public String toString()
	{
		return "X:" + x + " Y:" + y;
	}
}
