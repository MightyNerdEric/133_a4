package a4.actionobject;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import a4.MapView;

public class ZoomCommand implements MouseWheelListener {
	private MapView mv;
	
	public ZoomCommand(MapView mv) {
		this.mv = mv;
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		if (notches < 0) {
			mv.scale(0.8);
		}
		else if (notches > 0) {
			mv.scale(1.25);
		}
		else
			System.out.println("Mousewheel registered no notches moved.");
	}

}
