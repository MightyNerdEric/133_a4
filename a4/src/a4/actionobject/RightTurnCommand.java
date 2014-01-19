package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;

public class RightTurnCommand extends AbstractAction {
	private GameWorld gw;
	
	public RightTurnCommand() {
		
	}

	public RightTurnCommand(String arg0) {
		super(arg0);
		
	}

	public RightTurnCommand(String arg0, Icon arg1) {
		super(arg0, arg1);
		
	}

	public RightTurnCommand(GameWorld gw) {
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if (gw != null) {
			if (!gw.isPaused())
				gw.turnPlayerTank(15);
		}
	}

}
