package a4;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class CommandPanel extends JPanel {
	private JButton pausePlay = new JButton("Pause");
	private JButton reverse = new JButton("Reverse");
	private JButton help = new JButton("Help");
	private JButton quit = new JButton("Quit");

	public CommandPanel() {
		pausePlay.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		reverse.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		help.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		quit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		
		this.add(pausePlay);
		this.add(reverse);
		this.add(help);
		this.add(quit);
		
		reverse.setEnabled(false);
		
		setBorder(new TitledBorder("Commands:"));
		setLayout(new GridLayout(20, 1));
		setVisible(true);
	}

	public CommandPanel(LayoutManager layout) {
		super(layout);
		
		pausePlay.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		reverse.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		help.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		quit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		
		this.add(pausePlay);
		this.add(reverse);
		this.add(help);
		this.add(quit);
		
		reverse.setEnabled(false);
		
		setBorder(new TitledBorder("Commands:"));
		setLayout(new GridLayout(20, 1));
		setVisible(true);
	}

	public CommandPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		
		pausePlay.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		reverse.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		help.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		quit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		
		this.add(pausePlay);
		this.add(reverse);
		this.add(help);
		this.add(quit);
		
		reverse.setEnabled(false);
		
		setBorder(new TitledBorder("Commands:"));
		setLayout(new GridLayout(20, 1));
		setVisible(true);
	}

	public CommandPanel(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		
		pausePlay.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		reverse.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		help.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		quit.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),"none");
		
		this.add(pausePlay);
		this.add(reverse);
		this.add(help);
		this.add(quit);
		
		reverse.setEnabled(false);
		
		setBorder(new TitledBorder("Commands:"));
		setLayout(new GridLayout(20, 1));
		setVisible(true);
	}

	public void setActionListener(String name, AbstractAction a) {
		switch (name) {
			case "pausePlay":
				pausePlay.addActionListener(a);
				break;
			case "reverse":
				reverse.addActionListener(a);
				break;
			case "help":
				help.addActionListener(a);
				break;
			case "quit":
				quit.addActionListener(a);
				break;
		}
	}
	
	public void pause() {
		reverse.setEnabled(true);
		pausePlay.setText("Play");
	}
	
	public void unpause() {
		reverse.setEnabled(false);
		pausePlay.setText("Pause");
	}
}
