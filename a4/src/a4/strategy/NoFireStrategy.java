package a4.strategy;

import a4.gameobject.EnemyTank;

public class NoFireStrategy implements Strategy {
	private EnemyTank et;

	public NoFireStrategy(EnemyTank et) {
		this.et = et;
	}

	public boolean apply() {
		if (et.isBlocked()) {
			et.rotate(45);
			et.turn(45);
		}
		if (et.getSpeed() < 6)
			et.accelerate(1);
		return false;
	}

}
