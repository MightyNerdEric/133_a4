package a4;

import java.awt.geom.AffineTransform;

import a4.gameobject.EnemyTank;

public interface IGameWorld {
	public int getTime();
	public int getLives();
	public int getPoints();
	public boolean getSoundState();
	public void toggleSound();
	public void decLives();
	public void incPoints(int p);
	public void tick();
	public void addObserver(IObserver obs);
	public void notifyObservers();
	public void turnPlayerTank(int direction);
	public void acceleratePlayerTank(int accel);
	public void firePlayerMissile();
	public void enemySwitch();
	public void fireEnemyMissile(EnemyTank et);
	public void displayMap();
	public void respawnPlayer();
	public int missileCount();
}
