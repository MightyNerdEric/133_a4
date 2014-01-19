package a4.gameobject;

import java.awt.Color;

public abstract class Moveable extends GameObject {
	private int direction;
	private int speed;
	
	public Moveable(float xLoc, float yLoc, Color objColor, int direction) {
		super(xLoc, yLoc, objColor);
		this.direction = direction;
		this.speed = 0;
	}
	
	public Moveable(float xLoc, float yLoc, Color objColor, int direction, int speed) {
		super(xLoc, yLoc, objColor);
		this.direction = direction;
		this.speed = speed;
	}
	
	public void rotate(int degrees) {
		getMyRotate().rotate(Math.toRadians(-degrees));
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction % 360;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public abstract void move(int timeElapsed);
}
