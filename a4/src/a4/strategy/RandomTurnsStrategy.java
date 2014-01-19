package a4.strategy;

import java.util.Random;

import a4.gameobject.EnemyTank;

public class RandomTurnsStrategy implements Strategy {
	private EnemyTank et;
	private int timeElapsed;
	private Random gen;

	public RandomTurnsStrategy(EnemyTank et) {
		this.et = et;
		gen = new Random();
		timeElapsed = 0;
	}
	
	public boolean apply() {
		timeElapsed += 20;
		if (et.isBlocked()) {
			et.rotate(45);
			et.turn(45);
		}
		if (et.getSpeed() < 4 && timeElapsed % 100 == 0)
			et.accelerate(1);
		if (timeElapsed % 1000 == 0) {
			int angle = gen.nextInt(24) * 15;
			et.rotate(angle);
			et.turn(angle);
		}
		if (timeElapsed % 1200 == 0)
			return true;
		else
			return false;
	}

}
