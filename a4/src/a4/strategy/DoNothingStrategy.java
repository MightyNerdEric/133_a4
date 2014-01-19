package a4.strategy;

import a4.gameobject.EnemyTank;

public class DoNothingStrategy implements Strategy {
	private EnemyTank et;

	public DoNothingStrategy(EnemyTank et) {
		this.et = et;
	}
	
	public boolean apply() {
		et.setSpeed(0);
		return false;
	}

}
