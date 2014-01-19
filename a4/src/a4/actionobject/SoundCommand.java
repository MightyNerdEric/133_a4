package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import a4.GameWorld;


public class SoundCommand extends AbstractAction {
	private GameWorld gw;
	private boolean soundEnabled;
	
	public SoundCommand() {
	}

	public SoundCommand(String arg0) {
		super(arg0);
	}

	public SoundCommand(String arg0, Icon arg1) {
		super(arg0, arg1);
	}
	
	public SoundCommand(GameWorld gw) {
		this.gw = gw;
		soundEnabled = true;
	}
	
	public void actionPerformed(ActionEvent a) {
		soundEnabled = !soundEnabled;
		if (!gw.isPaused()) 
			gw.setSoundEnabled(soundEnabled);
		String soundState = soundEnabled ? "ON" : "OFF";
		System.out.println("Sound is " + soundState);
	}

}
