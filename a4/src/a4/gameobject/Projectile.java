package a4.gameobject;

import java.awt.Color;

public abstract class Projectile extends Moveable {
	
	public Projectile(float xLoc, float yLoc, Color objColor, int direction, int speed) {
		super(xLoc, yLoc, objColor, direction, speed);
	}
	
	public void move(int timeElapsed) {
		int ms = timeElapsed/20;
		int myDirection = this.getDirection();
		int mySpeed = this.getSpeed();
		getMyTranslate().translate(Math.cos(Math.toRadians(90-myDirection))*mySpeed*ms, Math.sin(Math.toRadians(90-myDirection))*mySpeed*ms);
	}
}
