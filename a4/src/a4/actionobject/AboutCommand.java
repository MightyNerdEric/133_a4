package a4.actionobject;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

public class AboutCommand extends AbstractAction {

	public AboutCommand() {
		
	}

	public AboutCommand(String name) {
		super(name);
		
	}

	public AboutCommand(String name, Icon icon) {
		super(name, icon);
		
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("About selected");
		JOptionPane.showMessageDialog(null, "Eric Ball\nCSc 133\nBall's Tank v 0.03");
	}

}
