package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;

public class SpeedUpCommand extends AbstractAction {
	private GameWorld gw;

	public SpeedUpCommand() {
		
	}

	public SpeedUpCommand(String name) {
		super(name);
		
	}

	public SpeedUpCommand(String name, Icon icon) {
		super(name, icon);
		
	}

	public SpeedUpCommand(GameWorld gw) {
		this.gw = gw;
	}
	public void actionPerformed(ActionEvent e) {
		if (gw != null) {
			if (!gw.isPaused())
				gw.acceleratePlayerTank(1);
		}
	}

}
