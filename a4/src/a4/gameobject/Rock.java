package a4.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

public class Rock extends Landscape implements IDrawable {
	private int width;
	private int height;
	private final static Color rockColor = new Color(160, 160, 160);
	
	public Rock(float xLoc, float yLoc, int width, int height) {
		super(xLoc, yLoc, rockColor);
		this.width = Math.abs(width % 20 + 1)*4;
		this.height = Math.abs(height % 20 + 1)*4;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static Color getRockcolor() {
		return rockColor;
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
		
		g2d.setColor(rockColor);
		Point upperLeft = new Point(-width/2, -height/2);
		g2d.fillRect(upperLeft.x, upperLeft.y, width, height);
		g2d.setTransform(saveAT);
	}

	public String toString() {
		String info = "Rock: loc=" + this.getxLoc() + "," + this.getyLoc() + " color=[160,160,160] width=" +
					  this.getWidth() + " height=" + this.getHeight();
		return info;
	}
}
