package a4.gameobject.grenade;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;


public class Body {
	private int myRadius;
	private Color myColor;
	private AffineTransform myTranslation;
	private AffineTransform myRotation;
	private AffineTransform myScale;
	
	public Body () {
		myRadius = 2;
		myColor = Color.yellow ;
		myTranslation = new AffineTransform();
		myRotation = new AffineTransform();
		myScale = new AffineTransform();
	}
	
	public int getRadius() {
		return myRadius;
	}
	
	public void rotate(double degrees) {
		myRotation.rotate(Math.toRadians(degrees));
	}
	
	public void scale(double sx, double sy) {
		myScale.scale(sx, sy);
	}
	
	public void translate(double dx, double dy) {
		myTranslation.translate(dx, dy);
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(myTranslation);
		g2d.transform(myRotation);
		g2d.transform(myScale);
		g2d.setColor(myColor);
		
		Point upperLeft = new Point(-myRadius, -myRadius);
		g2d.fillOval(upperLeft.x, upperLeft.y, myRadius, myRadius);
		g2d.setTransform(saveAT);
	}
}
