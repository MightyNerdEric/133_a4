package a4;

import java.awt.geom.Point2D;
import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

import a4.gameobject.*;
import a4.gameobject.grenade.SpikedGrenade;

public class GameWorld implements IObservable, IGameWorld{
	private Random gen = new Random();
	private GameObjectCollection gwCollection = new GameObjectCollection();
	private Vector<IObserver> observers = new Vector<IObserver>();
	private Vector<Tank> selected = new Vector<Tank>();
	private int lives = 3;
	private int points = 0;
	private int time = 0;
	private boolean soundEnabled;
	private boolean isPaused;
	private Sound tankFiringSound;
	private Sound rockCollisionSound;
	private Sound missileHitSound;
	private Sound bgm;
	
	
	public GameWorld(int numEnemyTanks, int numRocks, int numTrees) {
		soundEnabled = true;
		setPaused(false);
		
		// Initialize sounds
		String soundDir = "." + File.separator + "sound" + File.separator;
		String tankFiringSoundFile = "fire.aif";
		String tankFiringPath = soundDir + tankFiringSoundFile;
		tankFiringSound = new Sound(tankFiringPath);
		
		String collisionSoundFile = "collide.aif";
		String collisionPath = soundDir + collisionSoundFile;
		rockCollisionSound = new Sound(collisionPath);
		
		String missileHitSoundFile = "missilehit.aif";
		String missileHitPath = soundDir + missileHitSoundFile;
		missileHitSound = new Sound(missileHitPath);
		
		String bgmFile = "music.aif";
		String bgmPath = soundDir + bgmFile;
		bgm = new Sound(bgmPath);
		bgm.loop();
		
		this.gwCollection.add(new PlayerTank(10,10,0));
		for (int i=0; i<numEnemyTanks; i++) {
			this.generateEnemyTank();
		}
		for (int i = 0; i<numRocks; i++) {
			this.gwCollection.add(new Rock((float)gen.nextInt(2000), (float)gen.nextInt(2000), gen.nextInt(), gen.nextInt()));
		}
		for (int i = 0; i<numTrees; i++) {
			this.gwCollection.add(new Tree((float)gen.nextInt(2000), (float)gen.nextInt(2000), gen.nextInt()));
		}
		
	}
	
	public void addObserver(IObserver obs) {
		observers.add(obs);
	}
	
	public void notifyObservers() {
		GameWorldProxy gwp = new GameWorldProxy(this);
		for (IObserver i : observers) {
			i.update(gwp, null);
		}
	}
	
	public GameObjectCollection getGWCollection() {
		return gwCollection;
	}
	
	public int getLives() {
		return lives;
	}

	public int getPoints() {
		return points;
	}

	public int getTime() {
		return time;
	}
	
	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean getSoundState() {
		return soundEnabled;
	}
	
	public void toggleSound() {
		soundEnabled = !soundEnabled;
		if (soundEnabled)
			bgm.loop();
		else
			bgm.stop();
		this.notifyObservers();
	}
	
	public void setSoundEnabled(boolean bool) {
		soundEnabled = bool;
		if (soundEnabled)
			bgm.loop();
		else
			bgm.stop();
		this.notifyObservers();
	}
	
	public void mute() {
		soundEnabled = false;
		bgm.stop();
	}
	
	public void reverseSelected() {
		for (Tank t : selected) {
			t.turn(180);
			t.rotate(180);
		}
		notifyObservers();
	}
	
	public void decLives() {
		this.lives--;
		this.notifyObservers();
	}
	
	/**
	 * Increment the points value for this GameWorld
	 * @param p	An integer value which is to be added to the current GameWorld points value.
	 * 			This can include a negative value for lost points.
	 */
	public void incPoints(int p) {
		this.points += p;
		this.notifyObservers();
	}

	public void turnPlayerTank(int direction) {
		Iterator i = gwCollection.iterator();
		while (i.hasNext()) {
			GameObject go = ((GameObject)i.next());
			if (go instanceof PlayerTank) {
				((PlayerTank)go).turn(direction);
				((PlayerTank)go).rotate(direction);
				this.notifyObservers();
				return;
			}
		}
		System.out.println("Turn error: No player tank found.");
	}
	
	public void acceleratePlayerTank(int accel) {
		Iterator i = gwCollection.iterator();
		while (i.hasNext()) {
			GameObject go = ((GameObject)i.next());
			if (go instanceof PlayerTank) {
				((PlayerTank)go).accelerate(accel);
				this.notifyObservers();
				return;
			}
		}
		System.out.println("Accelerate error: No player tank found.");
	}
	
	public void firePlayerMissile() {
		Iterator i = gwCollection.iterator();
		while (i.hasNext()) {
			GameObject go = ((GameObject)i.next());
			if (go instanceof PlayerTank) {
				PlayerTank pt = (PlayerTank)go;
				if (pt.hasaMissile()) {
					pt.decMissile();
					gwCollection.add(new Missile((float)pt.getxLoc(), (float)pt.getyLoc(), pt.getDirection(), pt.getSpeed()+5, pt));
					if (getSoundState())
						tankFiringSound.play();
					this.notifyObservers();
				}
				else 
					System.out.println("Error: no more missiles.");
				return;
			}
		}
		System.out.println("Fire Player Missile error: No player tank found.");
	}
	
	public void firePlayerGrenade() {
		Iterator i = gwCollection.iterator();
		while (i.hasNext()) {
			GameObject go = ((GameObject)i.next());
			if (go instanceof PlayerTank) {
				PlayerTank pt = (PlayerTank)go;
				if (pt.hasaMissile()) {
					pt.decMissile();
					gwCollection.add(new SpikedGrenade((float)pt.getxLoc(), (float)pt.getyLoc(), pt.getDirection(), pt.getSpeed()+5, pt));
					if (getSoundState())
						tankFiringSound.play();
					this.notifyObservers();
				}
				else 
					System.out.println("Error: no more missiles.");
				return;
			}
		}
		System.out.println("Fire Player Missile error: No player tank found.");
	}
	
	public void firePlayerPlasma() {
		Iterator i = gwCollection.iterator();
		while (i.hasNext()) {
			GameObject go = ((GameObject)i.next());
			if (go instanceof PlayerTank) {
				PlayerTank pt = (PlayerTank)go;
				if (pt.hasaMissile()) {
					pt.decMissile();
					gwCollection.add(new PlasmaWave((float)pt.getxLoc(), (float)pt.getyLoc(), pt.getDirection(), pt.getSpeed()+5, pt));
					if (getSoundState())
						tankFiringSound.play();
					this.notifyObservers();
				}
				else 
					System.out.println("Error: no more missiles.");
				return;
			}
		}
		System.out.println("Fire Player Missile error: No player tank found.");
	}
	
	public void enemySwitch() {
		if (!worldHasEnemyTanks()) {
			System.out.println("There are no enemy tanks!");
			return;
		}
		
		for (Object o : gwCollection) {
			if (o instanceof EnemyTank)
				((EnemyTank)o).nextStrategy();
		}
	}
		
	public void fireEnemyMissile(EnemyTank et) {
		if (et.hasaMissile()) {
			et.decMissile();
			gwCollection.add(new Missile((float)et.getxLoc(), (float)et.getyLoc(), et.getDirection(), et.getSpeed()+5, et));
			if (getSoundState())
				tankFiringSound.play();
			this.notifyObservers();
		}
	}
	
	/**
	 * Looks at the coordinates (x,y) in the GameWorld to try to find a Tank. If found, the Tank
	 * will be "selected." If nothing is found, all selected objects should be unselected.
	 * @param x x coordinate of mouse click
	 * @param y y coordinate of mouse click
	 */
	public void select(Point2D clickLocation) {
		// For now, this will only look for objects of class Tank
		
		deselectAll();
		for (Iterator i = gwCollection.iterator(); i.hasNext();) {
			GameObject myGo = (GameObject)i.next();
			if (myGo instanceof Tank) {
				if (((Tank) myGo).contains(clickLocation)) {
					((Tank) myGo).setSelected(true);
					selected.add((Tank) myGo);
				}
			}
		}
		notifyObservers();
	}
	
	public void multiSelect(Point2D clickLocation) {
		// For now, this will only look for objects of class Tank
		for (Iterator i = gwCollection.iterator(); i.hasNext();) {
			GameObject myGo = (GameObject)i.next();
			if (myGo instanceof Tank) {
				if (((Tank) myGo).contains(clickLocation)) {
					((Tank) myGo).setSelected(true);
					selected.add((Tank) myGo);
					notifyObservers();
				}
			}
		}
	}
	
	public void deselectAll() {
		// for loop is done from top to bottom due to shifting indices.
		for (int i = selected.size()-1; i >= 0; i--) {
			selected.elementAt(i).setSelected(false);
			selected.remove(i);
		}
	}
	
	/**
	 * The tick movement is called for every clock tick. It increments the games "time," switches enemy strategies every 10
	 * seconds, reloads tank missiles every 30 seconds, and is responsible for movement, collisions, and removing objects from
	 * the GameWorld when those operations necessitate it. It will also respawn 5 tanks if all tanks are destroyed.
	 */
	public void tick() {
		time += 20;
		
		if (time % 10000 == 0)
			enemySwitch();
		
		for (Iterator i = gwCollection.iterator(); i.hasNext();) {
			GameObject myGo = (GameObject)i.next();
			if (myGo instanceof Moveable)
				((Moveable)myGo).move(20);
			
			if (myGo instanceof SpikedGrenade)
				((SpikedGrenade) myGo).update();
				
			if (myGo instanceof EnemyTank)
				if (((EnemyTank)myGo).update())
					fireEnemyMissile((EnemyTank)myGo);
			
			if (myGo instanceof Missile)
				if (!((Missile)myGo).decLifetime())
					i.remove();
			
			if (myGo instanceof Tank && time % 30000 == 0)
				((Tank)myGo).reloadMissiles();
		}
		
		Vector<GameObject> toDelete = new Vector<GameObject>();
		Vector[] collisionHandled = new Vector[gwCollection.size()+1];
		
		for (int ind = 0; ind<=gwCollection.size(); ind++)
			collisionHandled[ind] = new Vector<GameObject>();
		for (Iterator i = gwCollection.iterator(); i.hasNext();) {
			GameObject myGo = (GameObject)i.next();
			for (Iterator j = gwCollection.iterator(); j.hasNext();) {
				GameObject otherObject = (GameObject)j.next();
				if (myGo != otherObject && !collisionHandled[gwCollection.indexOf(myGo)].contains(otherObject)) {
					if (myGo.collidesWith(otherObject)) {
						collisionHandled[gwCollection.indexOf(otherObject)].add(myGo);
						myGo.handleCollision(otherObject);
						if (myGo instanceof Missile)
							toDelete.add(myGo);
						if (otherObject instanceof Missile)
							toDelete.add(otherObject);
						if ((myGo instanceof Missile && otherObject instanceof EnemyTank)) {
							if (((Missile)myGo).getParent() instanceof PlayerTank)
								points += 20;
						}
						else if ((myGo instanceof EnemyTank && otherObject instanceof Missile)) {
							if (((Missile)otherObject).getParent() instanceof PlayerTank)
								points += 20;
						}
						else if (((myGo instanceof Tank && otherObject instanceof Rock) || (myGo instanceof Rock && otherObject instanceof Tank)) && getSoundState())
							rockCollisionSound.play();
						else if (((myGo instanceof Missile && otherObject instanceof Tank) || (myGo instanceof Tank && otherObject instanceof Missile)) && getSoundState())
							missileHitSound.play();
					}
				}
			}
		}
		for (GameObject go : toDelete)
			gwCollection.remove(go);
		
		int etCount = 0;
		for (Iterator i = gwCollection.iterator(); i.hasNext();) {
			GameObject myGo = (GameObject)i.next();
			if (myGo instanceof EnemyTank) {
				etCount++;
				if (((Tank) myGo).getArmorStrength() < 1) {
					i.remove();
					etCount--;
				}
			}
			else if (myGo instanceof PlayerTank) {
				if (((Tank)myGo).getArmorStrength() < 1) {
					i.remove();
					respawnPlayer();
				}
			}
		}
		if (etCount == 0) {
			for (int i=0; i<5; i++) {
				this.generateEnemyTank();
			}
		}
		this.notifyObservers();
	}
	
	/**
	 * Outputs the toString information of each GameObject in the GameWorld to the command line.<br>
	 * Deprecated.
	 */
	public void displayMap() {
		for (Object myObject : gwCollection)
			System.out.println(myObject.toString());
	}
	
	
	/**
	 * This method will remove the current player Tank and replace it with a new one at the
	 * origin (x:0 y:0 heading:0) if the player still has lives remaining. Otherwise, it signals
	 * that the game is over.
	 */
	public void respawnPlayer() {
		lives--;
		
		if (lives > 0) {
			PlayerTank newPlayer = new PlayerTank(10,10,0);
			
			boolean collides = false;
			while (true) {
				collides = false;
				
				for (Iterator i = gwCollection.iterator(); i.hasNext();) {
					if (newPlayer.collidesWith((ICollider) i.next()))
						collides = true;
				}
				if (collides == false) {
					gwCollection.add(newPlayer);
					return;
				}
				// If origin is occupied, spawn in random location
				newPlayer = new PlayerTank((float)gen.nextInt(900), (float)gen.nextInt(900), 0);
			}
		}
		else
			JOptionPane.showMessageDialog(null, "Game Over");
		this.notifyObservers();
	}
	
	public int missileCount() {
		int missileCount = 0;
		for (Object myObject : gwCollection) {
			if (myObject instanceof Missile)
				missileCount++;
		}
		return missileCount;
	}
	
	private void generateEnemyTank() {
		// Direction needs to be 0-359, and divisible by 15. We begin with a 
		// random int 0-359, then round down to the next lowest multiple of 15.
		int direction = gen.nextInt(360);
		direction -= direction%15;
		boolean collides = false;
		while (true) {
			collides = false;
			EnemyTank newEnemy = new EnemyTank((float)gen.nextInt(900), (float)gen.nextInt(900), direction);
			for (Iterator i = gwCollection.iterator(); i.hasNext();) {
				if (newEnemy.collidesWith((ICollider) i.next()))
					collides = true;
			}
			if (collides == false) {
				this.gwCollection.add(newEnemy);
				return;
			}
		}
	}
	
	/**
	 * Method for checking if there are enemy tanks in the game world
	 * 
	 * @return 	<code>true</code> if one or more non-player tanks are present;
	 * 			<code>false</code> otherwise
	 */
	private boolean worldHasEnemyTanks() {
		for (Iterator i = gwCollection.iterator(); i.hasNext();) {
			if (i.next() instanceof EnemyTank)
				return true;
		}
		return false;
	}
}
