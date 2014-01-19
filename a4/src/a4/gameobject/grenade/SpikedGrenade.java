package a4.gameobject.grenade;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import a4.gameobject.Missile;
import a4.gameobject.Tank;


public class SpikedGrenade extends Missile {
	private Body myBody;
	private Flame [] flames;
	private double flameOffset = 0;
	private double flameIncrement = 0.05;
	private double maxFlameOffset = 0.5;
	
	public SpikedGrenade(float xLoc, float yLoc, int direction, int speed, Tank parent) {
		super(xLoc, yLoc, direction, speed, parent);
		
		this.scale(3, 3);
		
		myBody = new Body();
		myBody.translate(2.5, 1.7);
		myBody.scale(2.5, 1.5);
		flames = new Flame[4];
		
		Flame f0 = new Flame();
		f0.translate(0, 4);
		f0.scale(0.5, 0.8);
		flames[0] = f0;
		f0.setColor(Color.red);
		
		Flame f1 = new Flame();
		f1.translate(0, 7);
		f1.rotate(-90);
		f1.scale(0.5, 0.5);
		flames[1] = f1;
		f1.setColor(Color.green);
		
		Flame f2 = new Flame();
		f2.translate(0, 4);
		f2.rotate(180);
		f2.scale(0.5, 0.8);
		flames[2] = f2;
		f2.setColor(Color.blue);
		
		Flame f3 = new Flame();
		f3.translate(0, 7);
		f3.rotate(90);
		f3.scale(0.5, 0.5);
		flames[3] = f3;
		f3.setColor(Color.magenta);
		
		this.move(40);
	}
	
	public void update() {
		getMyRotate().rotate(Math.toRadians(5));
		
		flameOffset += flameIncrement;
		for (Flame f : flames) {
			f.translate(0, flameOffset);
		}
		
		if (Math.abs(flameOffset) >= maxFlameOffset) {
			flameIncrement *= -1;
		}
	}
	
	public int getRadius() {
		return myBody.getRadius();
	}
	public void rotate(double degrees) {
		getMyRotate().rotate(Math.toRadians(degrees));
	}
	
	public void scale(double sx, double sy) {
		getMyScale().scale(sx, sy);
	}
	
	public void translate(double dx, double dy) {
		getMyTranslate().translate(dx, dy);
	}
	
	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();
		
		g2d.transform(getMyTranslate());
		g2d.transform(getMyRotate());
		g2d.transform(getMyScale());
		
		myBody.draw(g2d);
		for (Flame f : flames) {
			f.draw(g2d);
		}
		
		g2d.setTransform(saveAT);
	}
}
