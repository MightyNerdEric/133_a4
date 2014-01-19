package a4.gameobject;

import a4.strategy.*;

public class EnemyTank extends Tank {
	private Strategy curStrategy;

	public EnemyTank(float xLoc, float yLoc, int direction) {
		super(xLoc, yLoc, direction);
		curStrategy = new RandomTurnsStrategy(this);
	}
	
	public boolean update() {
		return invokeStrategy();
	}
	
	public void setStrategy(Strategy s) {
		curStrategy = s;
	}
	
	public void nextStrategy() {
		if (curStrategy instanceof NoFireStrategy)
			curStrategy  = new EveryOtherTickStrategy(this);
		else if (curStrategy instanceof EveryOtherTickStrategy)
			curStrategy = new EveryTickStrategy(this);
		else if (curStrategy instanceof EveryTickStrategy)
			curStrategy = new RandomTurnsStrategy(this);
		else if (curStrategy instanceof RandomTurnsStrategy)
			curStrategy = new SpinAndFireStrategy(this);
		else if (curStrategy instanceof SpinAndFireStrategy)
			curStrategy = new NoFireStrategy(this);		
	}
	
	public boolean invokeStrategy() {
		return curStrategy.apply();
	}

}
