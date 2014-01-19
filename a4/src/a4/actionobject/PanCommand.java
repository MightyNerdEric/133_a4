package a4.actionobject;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import a4.MapView;

public class PanCommand implements MouseListener, MouseMotionListener {
	private MapView mv;
	private Point startPoint;
	private Point currentPoint;
	int dx;
	int dy;
	
	public PanCommand(MapView mv) {
		this.mv = mv;
	}

	public void mouseDragged(MouseEvent e) {
		currentPoint = e.getPoint();
		dx = startPoint.x - currentPoint.x;
		dy = startPoint.y - currentPoint.y;
		mv.pan(dx, dy);
		startPoint = currentPoint;
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		startPoint = e.getPoint();
		mv.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
	}

	public void mouseReleased(MouseEvent e) {
		mv.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}
