package a4;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import a4.actionobject.*;

/**
 * The Game class creates the frame from which the game runs, instantiates a GameWorld,
 * and sets up the panels for the game. As the controller, it also instantiates and 
 * assigns listeners and observers.
 */
class Game extends JFrame {
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private CommandPanel commandPanel;
	private JMenuBar menuBar;
	private Timer myTimer;
	private boolean isPaused;
	private SoundCommand soundCommand;
	private PanCommand panCommand;
	
	public Game() {
		int numEnemyTanks = 0;
		int numRocks = 0; 
		int numTrees = 0;
		mv = new MapView();
		sv = new ScoreView();
		isPaused = false;		
		
		numEnemyTanks = 10;
		numRocks = 15;
		numTrees = 15;
		
		gw = new GameWorld(numEnemyTanks, numRocks, numTrees);
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		this.setTitle("Ball's Tank");
		soundCommand = new SoundCommand(gw);
		buildMenuBar();		
		commandPanel = new CommandPanel();
		setCommandPanelListeners();
		
		setSize(1007,989); // This creates a starting MapView size of 900x900
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		this.setJMenuBar(menuBar);
		this.add(sv, BorderLayout.NORTH);
		this.add(commandPanel, BorderLayout.WEST);
		this.add(mv, BorderLayout.CENTER);
		setVisible(true);
		
		mv.addMouseListener(new MousePressedCommand(gw, mv));
		mv.addMouseWheelListener(new ZoomCommand(mv));
		panCommand = new PanCommand(mv);
		mv.addMouseMotionListener(panCommand);
		mv.addMouseListener(panCommand);
		buildKeyBindings();
		myTimer = new Timer(20, new TickCommand(gw));
		myTimer.start();
	}
	
	private void buildMenuBar() {
		menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem mItem = new JMenuItem("New");
		fileMenu.add(mItem);
		mItem = new JMenuItem("Save");
		fileMenu.add(mItem);
		mItem = new JMenuItem("Undo");
		fileMenu.add(mItem);
		JCheckBoxMenuItem soundMenu = new JCheckBoxMenuItem("Sound", gw.getSoundState());
		soundMenu.addActionListener(soundCommand);
		fileMenu.add(soundMenu);
		mItem = new JMenuItem("About");
		mItem.addActionListener(new AboutCommand());
		fileMenu.add(mItem);
		mItem = new JMenuItem("Quit");
		mItem.addActionListener(new QuitCommand());
		fileMenu.add(mItem);
		menuBar.add(fileMenu);
	}
	
	private void buildKeyBindings() {
		FirePlayerMissileCommand fireCommand = new FirePlayerMissileCommand(gw);
		FireGrenadeCommand grenadeCommand = new FireGrenadeCommand(gw);
		FirePlasmaCommand plasmaCommand = new FirePlasmaCommand(gw);
		RightTurnCommand rightTurnCommand = new RightTurnCommand(gw);
		LeftTurnCommand leftTurnCommand = new LeftTurnCommand(gw);
		SpeedUpCommand speedUpCommand = new SpeedUpCommand(gw);
		SlowDownCommand slowDownCommand = new SlowDownCommand(gw);
		
		int mapName = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap imap = mv.getInputMap(mapName);
		KeyStroke spaceKey = KeyStroke.getKeyStroke(' ');
		KeyStroke gKey = KeyStroke.getKeyStroke('g');
		KeyStroke pKey = KeyStroke.getKeyStroke('p');
		KeyStroke upKey = KeyStroke.getKeyStroke("UP");
		KeyStroke downKey = KeyStroke.getKeyStroke("DOWN");
		KeyStroke rightKey = KeyStroke.getKeyStroke("RIGHT");
		KeyStroke leftKey = KeyStroke.getKeyStroke("LEFT");
		
		imap.put(spaceKey, "fire");
		imap.put(gKey, "grenade");
		imap.put(pKey, "plasma");
		imap.put(rightKey, "right");
		imap.put(leftKey, "left");
		imap.put(upKey, "faster");
		imap.put(downKey, "slower");
		
		ActionMap amap = mv.getActionMap();
		amap.put("fire", fireCommand);
		amap.put("grenade", grenadeCommand);
		amap.put("plasma", plasmaCommand);
		amap.put("right", rightTurnCommand);
		amap.put("left", leftTurnCommand);
		amap.put("faster", speedUpCommand);
		amap.put("slower", slowDownCommand);
		this.requestFocus();
	}
	
	private void setCommandPanelListeners() {
		commandPanel.setActionListener("pausePlay", new PausePlayAction());
		commandPanel.setActionListener("reverse", new ReverseCommand(gw));
		commandPanel.setActionListener("help", new HelpCommand());
		commandPanel.setActionListener("quit", new QuitCommand());
	}
	
	public class PausePlayAction extends AbstractAction {
		private boolean muted;
		
		public PausePlayAction() {
			muted = false;
		}

		public PausePlayAction(String arg0) {
			super(arg0);
		}

		public PausePlayAction(String arg0, Icon arg1) {
			super(arg0, arg1);
		}
		
		public void actionPerformed(ActionEvent arg0) {
			if (isPaused) {
				myTimer.start();
				commandPanel.unpause();
				isPaused = false;
				gw.setPaused(false);
				soundCommand.actionPerformed(null);
				soundCommand.actionPerformed(null);
				gw.deselectAll();
			}
			else {
				myTimer.stop();
				commandPanel.pause();
				isPaused = true;
				gw.setPaused(true);
				gw.mute();
			}
		}

	}
	
}