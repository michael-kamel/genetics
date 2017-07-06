package examples;

import java.util.Random;

import genetics.structures.genetic.Mutable;

public class Circle implements Mutable
{
	private int radius;
	private double area;
	private int maxRadius;
	
	public Circle(int maxRadius)
	{
		this.radius = 1;
		this.maxRadius = maxRadius;
		calculateArea();
	}
	public Circle(int radius, int maxRadius)
	{
		this.radius = radius;
		this.maxRadius = maxRadius;
		calculateArea();
	}
	
	public int getRadius() 
	{
		return radius;
	}
	public void setRadius(int radius)
	{
		this.radius = radius;
	}
	public double getArea() 
	{
		return area;
	}
	public int getMaxRadius() 
	{
		return maxRadius;
	}
	
	public void calculateArea()
	{
		area = Math.PI*(radius*radius);
	}
	public void mutate()
	{
		radius = new Random().nextInt(maxRadius+1);
		calculateArea();
	}
	public Circle clone()
	{
		return new Circle(radius, maxRadius);
	}
}
