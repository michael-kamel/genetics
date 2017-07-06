package examples;

import genetics.structures.general.Pair;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JLabel;

public class JCirclesLabel extends JLabel 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Pair<Circle, Coord>> circles;
	private Pair<Circle, Coord> target;
	
	public JCirclesLabel()
	{
		circles = new ArrayList<Pair<Circle,Coord>>();
	}
	public Pair<Circle, Coord> getTarget() 
	{
		return target;
	}
	public void setTarget(Pair<Circle, Coord> target) 
	{
		this.target = target;
	}
	public ArrayList<Pair<Circle, Coord>> getCircles() 
	{
		return circles;
	}
	
	public void addCircle(Pair<Circle, Coord> circ)
	{
		circles.add(circ);
	}
	public void clear()
	{
		circles.clear();
	}
	protected void paintComponent(Graphics g) 
	{ 
	    super.paintComponent(g);
	    g.setColor(Color.BLACK);
	    for(Pair<Circle, Coord> pair : circles)
	    {
	    	g.drawOval(pair.getElement1().getX() - pair.getElement0().getRadius(), this.getSize().height - (pair.getElement0().getRadius() + pair.getElement1().getY()), pair.getElement0().getRadius()*2, pair.getElement0().getRadius()*2);
	    }
	    g.setColor(Color.RED);
	    if(target != null)
	    	g.drawOval(target.getElement1().getX() - target.getElement0().getRadius(), this.getSize().height - (target.getElement0().getRadius() + target.getElement1().getY()), target.getElement0().getRadius()*2, target.getElement0().getRadius()*2);
	}
}
