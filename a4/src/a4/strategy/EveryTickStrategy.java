package a4.strategy;

import a4.gameobject.EnemyTank;

public class EveryTickStrategy implements Strategy {
	int timeElapsed;
	private EnemyTank et;
	
	public EveryTickStrategy(EnemyTank et) {
		this.et = et;
		timeElapsed = 0;
	}

	public boolean apply() {
		timeElapsed += 20;
		if (et.isBlocked()) {
			et.rotate(45);
			et.turn(45);
		}
		if (et.getSpeed() < 6 && timeElapsed % 100 == 0)
			et.accelerate(1);
		if (timeElapsed % 600 == 0 )
			return true;
		else
			return false;
	}

}
