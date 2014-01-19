package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;

public class TickCommand extends AbstractAction {
	private GameWorld gw;
	
	public TickCommand(GameWorld gw) {
		this.gw = gw;
	}

	public void actionPerformed(ActionEvent e) {
		gw.tick();
	}

}
