package a4.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Tree extends Landscape implements IDrawable {
	private int diameter;
	private final static Color treeColor = new Color(8, 142, 16);
	private int radius;
	
	public Tree(float xLoc, float yLoc, int diameter) {
		super(xLoc, yLoc, treeColor);
		this.diameter = Math.abs((diameter % 5) + 1)*8;
		radius = this.diameter/2;
	}
	
	public int getDiameter() {
		return diameter;
	}

	public static Color getTreecolor() {
		return treeColor;
	}
	
	public boolean collidesWith(ICollider otherObject) {
		if (otherObject instanceof Tank)
			return otherObject.collidesWith(this);
		return false;
	}

	public void handleCollision(ICollider otherObject) {
		otherObject.handleCollision(this);		
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(getMyTranslate());
		g2d.transform(getMyScale());
		
		g2d.setColor(treeColor);
		Point upperLeft = new Point(-radius, -radius);
		g2d.fillOval(upperLeft.x, upperLeft.y, radius*2, radius*2);
		g2d.setTransform(saveAT);
	}

	public String toString() {
		String info = "Tree: loc=" + this.getxLoc() + "," + this.getyLoc() + " color=[8,142,16] diameter=" + this.diameter;
		return info;
	}

	
}
