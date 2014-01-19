package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;

public class SlowDownCommand extends AbstractAction {
	private GameWorld gw;

	public SlowDownCommand() {
		
	}

	public SlowDownCommand(String name) {
		super(name);
		
	}

	public SlowDownCommand(String name, Icon icon) {
		super(name, icon);
		
	}

	public SlowDownCommand(GameWorld gw) {
		this.gw = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (gw != null) {
			if (!gw.isPaused())
				gw.acceleratePlayerTank(-1);
		}
	}

}
