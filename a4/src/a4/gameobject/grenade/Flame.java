package a4.gameobject.grenade;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;


public class Flame {
	private Point top, bottomLeft, bottomRight;
	private Color myColor;
	private AffineTransform myTranslation;
	private AffineTransform myRotation;
	private AffineTransform myScale;
	
	public Flame () {
		top = new Point(0, 2);
		bottomLeft = new Point(-1,-2);
		bottomRight = new Point(1,-2);
		myColor = Color.red;
		
		myTranslation = new AffineTransform();
		myRotation = new AffineTransform();
		myScale = new AffineTransform();
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
	
	public void setColor(Color myColor) {
		this.myColor = myColor;
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(myRotation);
		g2d.transform(myScale);
		g2d.transform(myTranslation);
		
		g2d.setColor(myColor);
//		g2d.drawLine(top.x, top.y, bottomLeft.x, bottomLeft.y);
//		g2d.drawLine(bottomLeft.x, bottomLeft.y, bottomRight.x, bottomRight.y);
//		g2d.drawLine(bottomRight.x, bottomRight.y, top.x, top.y);
		int [] xPts = new int [] {top.x, bottomLeft.x, bottomRight.x};
		int [] yPts = new int [] {top.y, bottomLeft.y, bottomRight.y};
		g2d.fillPolygon(xPts, yPts, 3);
		
		g2d.setTransform(saveAT);
	}
}
