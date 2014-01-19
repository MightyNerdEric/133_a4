package a4.gameobject;

import java.awt.Color;

public class PlayerTank extends Tank {
	private static final Color playerTankColor = new Color(0, 103, 107);
	
	public PlayerTank(float xLoc, float yLoc, int direction) {
		super(xLoc, yLoc, playerTankColor, direction);
	}
	
	public static Color getPlayertankcolor() {
		return playerTankColor;
	}
	
	public void blocked() {
		this.setSpeed(0);
	}

}
