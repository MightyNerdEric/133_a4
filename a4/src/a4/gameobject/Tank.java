package a4.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

import a4.gameobject.grenade.SpikedGrenade;

public class Tank extends Vehicle implements ISteerable {
	private int armorStrength;
	private int missileCount;
	private final static Color tankColor = new Color(255, 200, 0);
	private static final Color playerTankColor = new Color(0, 103, 107);
	private boolean blocked;
	private boolean isSelected;
	
	public Tank(float xLoc, float yLoc, int direction) {
		super(xLoc, yLoc, tankColor, direction);
		this.armorStrength = 10;
		this.missileCount = 40;
		this.rotate(direction);
		
		blocked = false;
		isSelected = false;
	}
	
	public Tank(float xLoc, float yLoc, Color newColor, int direction) {
		super(xLoc, yLoc, newColor, direction);
		this.armorStrength = 10;
		this.missileCount = 40;
		this.rotate(direction);

		blocked = false;
		isSelected = false;
	}
	
	public int getArmorStrength() {
		return armorStrength;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
	
	public void setSelected(boolean bool) {
		isSelected = bool;
	}
	
	/**
	 * Decrement the armorStrength of the Tank, and return <code>false</code> if
	 * armorStrength falls to 0.
	 * 
	 * @return <code>true</code> if armorStrength is > 0, return <code>false</code> otherwise.
	 */
	public boolean decArmorStrength() {
		this.armorStrength--;
		if (this.armorStrength > 0)
			return true;
		else return false;
	}
	
	public boolean hasaMissile() {
		if (missileCount > 0)
			return true;
		else return false;
	}
	
	public void decMissile() {
		this.missileCount--;
	}
	
	public void reloadMissiles() {
		this.missileCount = 40;
	}
	
	public boolean isBlocked() {
		return blocked;
	}
	
	public void blocked() {
		this.blocked = true;
		this.setSpeed(0);
	}

	/**
	 * Turn the tank <code>direction</code> degrees.
	 * @param direction	The number of degrees to turn the tank. Positive numbers will turn the tank
	 * counterclockwise, while negative numbers will turn it clockwise. Turning also removes a block from a tank.
	 */
	public void turn(int direction) {
		int currentDirection = this.getDirection();
		if (currentDirection + direction < 0)
			this.setDirection(currentDirection+direction+360);
		else
			this.setDirection((currentDirection+direction)%360);
		this.blocked = false;
	}

	public void accelerate(int accel) {
		if (!blocked) {			
			int currentSpeed = super.getSpeed();
			accel += currentSpeed;
			if (accel > 10)
				accel = 10;
			else if (accel < 0)
				accel = 0;
			super.setSpeed(accel);
		}
	}
	
	public boolean collidesWith(ICollider otherObject) {
		boolean result = false;
		
		if (otherObject instanceof SpikedGrenade) {
			if (((Missile) otherObject).getParent() == this)
				return false;
			int x = (int)Math.round(getxLoc());
			int y = (int)Math.round(getyLoc());
			int otherX = (int)Math.round(((GameObject) otherObject).getxLoc());
			int otherY = (int)Math.round(((GameObject) otherObject).getyLoc());
			int radius = ((SpikedGrenade) otherObject).getRadius();
			
			if ((x-otherX)*(x-otherX) + (y-otherY)*(y-otherY) <= (10+radius)*(10+radius))
				result = true;
		}
		else if (otherObject instanceof PlasmaWave) {
			if (((Missile) otherObject).getParent() == this)
				return false;
			int x = (int)Math.round(getxLoc());
			int y = (int)Math.round(getyLoc());
			int otherX = (int)Math.round(((GameObject) otherObject).getxLoc());
			int otherY = (int)Math.round(((GameObject) otherObject).getyLoc());
			
			int minX = ((PlasmaWave) otherObject).findMinX();
			int maxX = ((PlasmaWave) otherObject).findMaxX();
			int minY = ((PlasmaWave) otherObject).findMinY();
			int maxY = ((PlasmaWave) otherObject).findMaxY();
			
			if (x+10 > otherX+minX && x-10 < otherX+maxX && y+10 > otherY+minY && y-10 < otherY+maxY)
				result = true;
		}
		else if (otherObject instanceof Missile) {
			if (((Missile) otherObject).getParent() == this)
				return false;
			result = ((Missile)otherObject).collidesWith(this);
		}
		else if (otherObject instanceof Tank) {
			int x = (int)Math.round(getxLoc());
			int y = (int)Math.round(getyLoc());
			int otherX = (int)Math.round(((GameObject) otherObject).getxLoc());
			int otherY = (int)Math.round(((GameObject) otherObject).getyLoc());
			
			if (x-10 <= otherX+10 && x-10 >= otherX-10 && y-10 >= otherY-10 && y-10 <= otherY+10)
				result = true;
			else if (x+10 >= otherX-10 && x+10 <= otherX+10 && y-10 >= otherY-10 && y-10 <= otherY+10)
				result = true;
			else if (x-10 <= otherX+10 && x-10 >= otherX-10 && y+10 >= otherY-10 && y+10 <= otherY+10)
				result = true;
			else if (x+10 >= otherX-10 && x+10 <= otherX+10 && y+10 >= otherY-10 && y+10 <= otherY+10)
				result = true;
		}
		else if (otherObject instanceof Rock) {
			int x = (int)Math.round(getxLoc());
			int y = (int)Math.round(getyLoc());
			int otherX = (int)Math.round(((GameObject) otherObject).getxLoc());
			int otherY = (int)Math.round(((GameObject) otherObject).getyLoc());
			int width = ((Rock) otherObject).getWidth()/2;
			int height = ((Rock) otherObject).getHeight()/2;
			
			if (Math.abs(x-otherX) < width+10 && Math.abs(y-otherY) < height+10)
				result = true;
		}
		else if (otherObject instanceof Tree) {
			int x = (int)Math.round(getxLoc());
			int y = (int)Math.round(getyLoc());
			int otherX = (int)Math.round(((GameObject) otherObject).getxLoc());
			int otherY = (int)Math.round(((GameObject) otherObject).getyLoc());
			int radius = ((Tree)otherObject).getDiameter()/2;
			
			if ((x-otherX)*(x-otherX) + (y-otherY)*(y-otherY) <= (10+radius)*(10+radius))
				result = true;
		}
		return result;
	}
	
	public void handleCollision(ICollider otherObject) {
		if (otherObject instanceof Missile) {
			otherObject.handleCollision(this);
		}
		else if (otherObject instanceof Tank) {
			this.setSpeed(-getSpeed());
			this.move(20);
			((Tank)otherObject).setSpeed(-((Tank)otherObject).getSpeed());
			/* This loop will back the two tanks away from each other, one move at a time, until they just barely no longer collide.
			 * It goes back and forth so that the movement will be approximately equal.
			 * */
			for (int i = 0; this.collidesWith(otherObject); i++) {
				if (i % 2 == 0)
					((Tank)otherObject).move(20);
				else
					this.move(20);
			}
			this.blocked();
			((Tank)otherObject).blocked();
		}
		else if (otherObject instanceof Rock){
			this.setSpeed(-1);
			this.move(20);
			while (this.collidesWith(otherObject))
				this.move(20);
			this.blocked();
		}
		else {
			this.setSpeed(-1);
			this.move(20);
			while (this.collidesWith(otherObject))
				this.move(20);
			this.blocked();
		}
	}
	
	public boolean contains(Point2D worldPoint) {
		Point2D localPoint = new Point();
		AffineTransform myAT = new AffineTransform(getMyTranslate());
		myAT.concatenate(getMyRotate());
		myAT.concatenate(getMyScale());
		
		try {
			localPoint = myAT.createInverse().transform(worldPoint, null);
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		
		double pointX = localPoint.getX();
		double pointY = localPoint.getY();
		
		if (pointX > -10 && pointX < 10 && pointY > -10 && pointY < 10)
			return true;
		else
			return false;
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		if (!isSelected) {
			g2d.transform(getMyTranslate());
			g2d.transform(getMyRotate());
			g2d.transform(getMyScale());
			
			if(this instanceof PlayerTank) 
				g2d.setColor(playerTankColor);
			else
				g2d.setColor(tankColor);
			Point upperLeft = new Point(-10, -10);
			g2d.fillRect(upperLeft.x, upperLeft.y, 20, 20);
			
			g2d.setColor(Color.BLACK);
			g2d.drawLine(0, 0, 0, 10);
			
			g2d.setTransform(saveAT);
		}
		else {
			g2d.transform(getMyTranslate());
			g2d.transform(getMyRotate());
			g2d.transform(getMyScale());
			
			if(this instanceof PlayerTank) 
				g2d.setColor(playerTankColor);
			else
				g2d.setColor(tankColor);
			Point upperLeft = new Point(-10, -10);
			g2d.fillRect(upperLeft.x, upperLeft.y, 20, 20);
			
			g2d.setColor(Color.BLACK);
			g2d.drawLine(0, 0, 0, 10);
			
			g2d.drawRect(upperLeft.x, upperLeft.y, 20, 20);
			g2d.fillOval(upperLeft.x-2, upperLeft.y-2, 4, 4);
			g2d.fillOval(upperLeft.x-2, upperLeft.y+18, 4, 4);
			g2d.fillOval(upperLeft.x+18, upperLeft.y-2, 4, 4);
			g2d.fillOval(upperLeft.x+18, upperLeft.y+18, 4, 4);
			
			g2d.setTransform(saveAT);
		}
	}
	
	public String toString() {
		String info = "Tank: loc=" + this.getxLoc() + "," + this.getyLoc() + " color=[255,200,0] speed=" + 
					  this.getSpeed() + ", heading=" + this.getDirection() + ", armor=" + this.armorStrength
					  + " missiles=" + this.missileCount;
		return info;
	}
}
