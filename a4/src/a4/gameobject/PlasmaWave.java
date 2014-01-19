package a4.gameobject;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Random;


public class PlasmaWave extends Missile {
	Point [] cpa = new Point[4];
	private Random gen = new Random();
	
	public PlasmaWave(float xLoc, float yLoc, int direction, int speed, Tank parent) {
		super(xLoc, yLoc, direction, speed, parent);
		cpa[0] = new Point(gen.nextInt(100), gen.nextInt(100));
		cpa[1] = new Point(gen.nextInt(100), gen.nextInt(100));
		cpa[2] = new Point(gen.nextInt(100), gen.nextInt(100));
		cpa[3] = new Point(gen.nextInt(100), gen.nextInt(100));
	}
	
	public PlasmaWave(float xLoc, float yLoc, int direction, int speed, Tank parent, Point[] array) {
		super(xLoc, yLoc, direction, speed, parent);
		cpa = array;
	}
	
	public int findMinX() {
		int minX = cpa[0].x;
		if (cpa[1].x < minX)
			minX = cpa[1].x;
		if (cpa[2].x < minX)
			minX = cpa[2].x;
		if (cpa[3].x < minX)
			minX = cpa[3].x;
		return minX;
	}
	
	public int findMinY() {
		int minY = cpa[0].y;
		if (cpa[1].y < minY)
			minY = cpa[1].y;
		if (cpa[2].y < minY)
			minY = cpa[2].y;
		if (cpa[3].y < minY)
			minY = cpa[3].y;
		return minY;
	}
	
	public int findMaxX() {
		int maxX = cpa[0].x;
		if (cpa[1].x > maxX)
			maxX = cpa[1].x;
		if (cpa[2].x > maxX)
			maxX = cpa[2].x;
		if (cpa[3].x > maxX)
			maxX = cpa[3].x;
		return maxX;
	}
	
	public int findMaxY() {
		int maxY = cpa[0].y;
		if (cpa[1].y > maxY)
			maxY = cpa[1].y;
		if (cpa[2].y > maxY)
			maxY = cpa[2].y;
		if (cpa[3].y > maxY)
			maxY = cpa[3].y;
		return maxY;
	}

	public void draw(Graphics2D g2d) {
		AffineTransform saveAT = g2d.getTransform();

		g2d.transform(getMyTranslate());
		g2d.transform(getMyScale());
		g2d.transform(getMyRotate());
		g2d.setColor(new Color(100, 150, 150));
		
		drawBezierCurve(cpa, g2d);
		g2d.drawLine(cpa[0].x, cpa[0].y, cpa[1].x, cpa[1].y);
		g2d.drawLine(cpa[1].x, cpa[1].y, cpa[2].x, cpa[2].y);
		g2d.drawLine(cpa[2].x, cpa[2].y, cpa[3].x, cpa[3].y);
		g2d.drawLine(cpa[3].x, cpa[3].y, cpa[0].x, cpa[0].y);

		g2d.setTransform(saveAT);
	}
	
	private void drawBezierCurve(Point [] cpa, Graphics2D g2d) {
		Point currentPoint = cpa[0];
		double t = 0;
		
		while (t <= 1) {
			Point nextPoint = new Point(0,0);
			for (int i=0; i<=3; i++) {
				nextPoint.x = (int) (nextPoint.x + (blendingFunction(i, t) * cpa[i].x));
				nextPoint.y = (int) (nextPoint.y + (blendingFunction(i, t) * cpa[i].y));
			}
			g2d.drawLine(currentPoint.x, currentPoint.y, nextPoint.x, nextPoint.y);
			currentPoint = nextPoint;
			t += 0.01;
		}
	}
	
	private double blendingFunction(int i, double t) {
		switch(i) {
			case 0: return ((1-t)*(1-t)*(1-t));
			case 1: return (3*t*(1-t)*(1-t));
			case 2: return (3*t*t*(1-t));
			case 3: return (t*t*t);
			default: return 0;
		}
	}
}
