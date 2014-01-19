package a4.strategy;

import a4.gameobject.EnemyTank;

public class SpinAndFireStrategy implements Strategy {
	private EnemyTank et;
	private int timeElapsed;
	
	public SpinAndFireStrategy(EnemyTank et) {
		this.et = et;
		timeElapsed = 0;
	}

	public boolean apply() {
		timeElapsed += 20;
		
		if (et.getSpeed() < 5 && timeElapsed % 100 == 0)
			et.accelerate(1);
		if (timeElapsed % 200 == 0) {
			et.rotate(20);
			et.turn(20);
		}
		if (timeElapsed % 500 == 0)
			return true;
		else
			return false;
	}

}
