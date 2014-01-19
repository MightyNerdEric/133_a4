package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;

public class FirePlayerMissileCommand extends AbstractAction {
	private GameWorld gw;
	
	public FirePlayerMissileCommand(GameWorld gw) {
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (gw != null) {
			if (!gw.isPaused())
				gw.firePlayerMissile();
		}
		else System.out.println("Something went wrong.");
	}

}
