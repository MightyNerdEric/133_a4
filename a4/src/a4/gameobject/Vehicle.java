package a4.gameobject;

import java.awt.Color;

public abstract class Vehicle extends Moveable {

//	public Vehicle(int direction, int speed) {
//		super(direction, speed);
//	}
//	
	public Vehicle(float xLoc, float yLoc, Color objColor, int direction) {
		super(xLoc, yLoc, objColor, direction);
	}
	
	public void move(int timeElapsed) {
		int ms = timeElapsed/20;
		int myDirection = this.getDirection();
		int mySpeed = this.getSpeed();
		getMyTranslate().translate((double)(Math.cos(Math.toRadians(90-myDirection))*mySpeed)*ms, (double)(Math.sin(Math.toRadians(90-myDirection))*mySpeed)*ms);
	}
}
