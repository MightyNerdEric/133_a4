package a4;

import java.awt.geom.AffineTransform;
import java.util.*;

import a4.gameobject.*;

public class GameWorldProxy implements IObservable, IGameWorld {
	private GameObjectCollection gameWorld = new GameObjectCollection();
	private GameWorld realGameWorld;
	
	public GameWorldProxy(GameWorld gw) {
		realGameWorld = gw;
	}
	
	public void addObserver(IObserver obs) {
		
	}
	
	public void notifyObservers() {
		
	}
	
	public GameObjectCollection getGWCollection() {
		return realGameWorld.getGWCollection();
	}
	
	public int getLives() {
		return realGameWorld.getLives();
	}

	public int getPoints() {
		return realGameWorld.getPoints();
	}

	public int getTime() {
		return realGameWorld.getTime();
	}
	
	public boolean getSoundState() {
		return realGameWorld.getSoundState();
	}
	
	public void toggleSound() {
		
	}

	public void decLives() {
		
	}
	
	public void incPoints(int p) {
		
	}
	
	public void tick() {
	}
	
	public void turnPlayerTank(int direction) {
		
	}
	
	public void acceleratePlayerTank(int accel) {
		
	}
	
	public void firePlayerMissile() {
		
	}
	
	public void enemySwitch() {
		
	}
	
	public void turnEnemyTank(int direction) {
		
	}
	
	public void accelerateEnemyTank(int accel) {
		
	}
	
	public void fireEnemyMissile(EnemyTank et) {
		
	}
	
	public boolean playerTankHit() {
		return false;
	}
	
	public boolean enemyTankHit() {
		return false;
	}
	
	public void missileCollision() {
		
	}
	
	public void collision() {
		
	}

	public void displayMap() {
	
	}
	
	public void respawnPlayer() {

	}
	
	public int missileCount() {
		return realGameWorld.missileCount();
	}
}
