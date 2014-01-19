package a4;

import java.awt.Color;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class ScoreView extends JPanel implements IObserver {
	private JLabel timeLabel;
	private JLabel time;
	private JLabel livesLabel;
	private JLabel lives;
	private JLabel scoreLabel;
	private JLabel score;
	private JLabel soundLabel;
	private JLabel sound;
	private int timeElapsed;

	public ScoreView() {
		timeElapsed = 0;
		timeLabel = new JLabel("Elapsed Time: ");
		time = new JLabel("0");
		livesLabel = new JLabel("          Lives Left: ");
		lives = new JLabel("0");
		scoreLabel = new JLabel("          Score: ");
		score = new JLabel("0");
		soundLabel = new JLabel("          Sound: ");
		sound = new JLabel("On");
		this.add(timeLabel);
		this.add(time);
		this.add(livesLabel);
		this.add(lives);
		this.add(scoreLabel);
		this.add(score);
		this.add(soundLabel);
		this.add(sound);
		
		this.setBorder(new LineBorder(Color.blue, 1));
		this.setSize(1000, 200);
		this.setVisible(true);
	}
	
	public void update(IObservable o, Object arg) {
		if (((GameWorldProxy)o).getTime() % 1000 == 0)
			timeElapsed++;
		String newTime = ((Integer)timeElapsed).toString();
		String newLives = ((Integer)((GameWorldProxy)o).getLives()).toString();
		String newScore = ((Integer)((GameWorldProxy)o).getPoints()).toString();
		time.setText(newTime);
		lives.setText(newLives);
		score.setText(newScore);
		sound.setText(((GameWorldProxy)o).getSoundState() ? "On" : "Off");
	}

	public void update(IObservable o) {
		if (((GameWorldProxy)o).getTime() % 1000 == 0)
			timeElapsed++;
		String newTime = ((Integer)timeElapsed).toString();
		String newLives = ((Integer)((GameWorldProxy)o).getLives()).toString();
		String newScore = ((Integer)((GameWorldProxy)o).getPoints()).toString();
		time.setText(newTime);
		lives.setText(newLives);
		score.setText(newScore);
		sound.setText(((GameWorldProxy)o).getSoundState() ? "On" : "Off");
	}

}
