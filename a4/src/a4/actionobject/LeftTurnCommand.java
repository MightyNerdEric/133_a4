package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;

public class LeftTurnCommand extends AbstractAction {
	private GameWorld gw;

	public LeftTurnCommand() {
		
	}

	public LeftTurnCommand(String name) {
		super(name);
		
	}

	public LeftTurnCommand(String name, Icon icon) {
		super(name, icon);
		
	}
	
	public LeftTurnCommand(GameWorld gw) {
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		if (gw != null) {
			if (!gw.isPaused())
				gw.turnPlayerTank(-15);
		}
	}

}
