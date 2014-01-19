package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import a4.GameWorld;

public class FirePlasmaCommand extends AbstractAction {
	private GameWorld gw;
	
	public FirePlasmaCommand(GameWorld gw) {
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (gw != null) {
			if (!gw.isPaused())
				gw.firePlayerPlasma();
		}
		else System.out.println("Something went wrong.");
	}

}
