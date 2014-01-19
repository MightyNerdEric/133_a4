package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class QuitCommand extends AbstractAction {

	public QuitCommand() {
		
	}

	public QuitCommand(String arg0) {
		super(arg0);
		
	}

	public QuitCommand(String arg0, Icon arg1) {
		super(arg0, arg1);
		
	}

	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Quit selected.");
		int i = JOptionPane.showConfirmDialog(null, "Embrace Cowardice?", "Flee to Windows", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (i == JOptionPane.YES_OPTION)
			System.exit(0);
	}

}
