package a4.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import a4.gameobject.grenade.SpikedGrenade;

public class Missile extends Projectile {
	private int lifetime;
	private final static Color missileColor = Color.black;
	private Tank parent;

	public Missile(float xLoc, float yLoc, int direction, int speed, Tank parent) {
		super(xLoc, yLoc, missileColor, direction, speed);
		this.lifetime = 60;		// Lifetime extended to 60 for improved playability
		this.parent = parent;
		// Missile can build its own Translation and Scale, but needs a clone of its parent's rotate 
		setMyRotate((AffineTransform)parent.getMyRotate().clone());
		this.move(60);
	}
	
	public Missile(float xLoc, float yLoc, Color objColor, int direction, int speed) {
		super(xLoc, yLoc, objColor, direction, speed);
		this.lifetime = 5;
		
		this.move(60);
	}
	
	public Tank getParent() {
		return parent;
	}
	
	/**Decrements a missile's lifetime, returning true if the missile is still alive,	
	 * and false if the missile's lifetime reaches zero.
	 * 
	 * @return	Returns true if missile is still alive, false if the lifetime has reached 0.
	 */
	public boolean decLifetime() {
		this.lifetime--;
		if (lifetime > 0)
			return true;
		else return false;
	}
	
	public AffineTransform getMyAT() {
		AffineTransform myAT = new AffineTransform(getMyTranslate());
		myAT.concatenate(getMyRotate());
		myAT.concatenate(getMyScale());
		return myAT;
	}
	
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		
		if (otherObject instanceof Tank) {
			if (this instanceof SpikedGrenade || this instanceof PlasmaWave)
				return otherObject.collidesWith(this);
			else if (parent == otherObject)
				return false;
			AffineTransform myAT = getMyAT();
			Point2D top = new Point(0, 5);
			Point2D lowerLeft = new Point(-3, -5);
			Point2D lowerRight = new Point(3, -5);
			
			top = myAT.transform(top, null);
			lowerLeft = myAT.transform(lowerLeft, null);
			lowerRight = myAT.transform(lowerRight, null);
			
			if (((Tank) otherObject).contains(top))
				result = true;
			else if (((Tank) otherObject).contains(lowerLeft))
				result = true;
			else if (((Tank) otherObject).contains(lowerRight))
				result = true;
		}
		else if (otherObject instanceof Missile) {
			AffineTransform myAT = getMyAT();
			Point2D top = new Point(0, 5);
			Point2D lowerLeft = new Point(-3, -5);
			Point2D lowerRight = new Point(3, -5);
			
			top = myAT.transform(top, null);
			lowerLeft = myAT.transform(lowerLeft, null);
			lowerRight = myAT.transform(lowerRight, null);
			
			if (((Missile) otherObject).contains(top))
				result = true;
			else if (((Missile) otherObject).contains(lowerLeft))
				result = true;
			else if (((Missile) otherObject).contains(lowerRight))
				result = true;
		}
		return result;
	}
	
	public void handleCollision(ICollider otherObject) {
		if (otherObject instanceof Tank) {
			// SpikedGrenades cause 5 damage; this is accomplished simply by adding an extra 4
			if (this instanceof SpikedGrenade)
				for (int i = 0; i < 4; i++)
					((Tank) otherObject).decArmorStrength();
			((Tank) otherObject).decArmorStrength();
		}
	}
	
	public boolean contains(Point2D worldPoint) {
		Point2D localPoint = new Point();
		AffineTransform myAT = getMyAT();

		try {
			localPoint = myAT.createInverse().transform(worldPoint, null);
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}

		double pointX = localPoint.getX();
		double pointY = localPoint.getY();

		if (pointX >= -3 && pointX <= 3 && pointY >= -5 && pointY <= 5) {
			if ((1.0/3.0)*(3-Math.abs(pointX)) >= pointY+5.0) {
				return true;
			}
		}
		return false;
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		g2d.transform(getMyTranslate());
		g2d.transform(getMyRotate());
		g2d.transform(getMyScale());
		g2d.setColor(missileColor);
		
		g2d.drawLine(-3, -5, 3, -5);
		g2d.drawLine(-3, -5, 0, 5);
		g2d.drawLine(3, -5, 0, 5);
		
		g2d.setTransform(saveAT);
	}
	
	public String toString() {
		String info = "Missile: loc=" + this.getxLoc() + "," + this.getyLoc() + " color=[0,0,0] speed=" + 
					  this.getSpeed() + ", heading=" + this.getDirection() + ", lifetime=" + this.lifetime;
		return info;
	}
}
