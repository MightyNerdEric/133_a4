package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class HelpCommand extends AbstractAction {

	public HelpCommand() {
		
	}

	public HelpCommand(String arg0) {
		super(arg0);
		
	}

	public HelpCommand(String arg0, Icon arg1) {
		super(arg0, arg1);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Keyboard Controls:\n"
				+ " -UP/DOWN: Increase/decrease speed\n"
				+ " -LEFT/RIGHT: Turn left/turn right\n"
				+ " -SPACE: Fire missile\n\n"
				+ "Control Panel:\n"
				+ " -Pause/Play: Pause the game, allowing for selection and reverse. Play brings game out of pause.\n"
				+ " -Reverse: While game is paused, this button will reverse the direction of all selected tanks.\n"
				+ " -Help: This menu\n"
				+ " -Quit: Flee to Windows");
		System.out.println("Help menu selected.");
	}

}
