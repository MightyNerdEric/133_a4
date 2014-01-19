package a4.actionobject;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import a4.GameWorld;
import a4.MapView;

public class MousePressedCommand implements MouseListener {
	private Point2D clickLocation;
	private GameWorld gw;
	private AffineTransform inverseVTM;
	private MapView mv;
	
	public MousePressedCommand(GameWorld gw, MapView mv) {
		this.gw = gw;
		this.mv = mv;
	}

	public void mouseClicked(MouseEvent event) {
		if (gw.isPaused()) {
			inverseVTM = mv.getInverseVTM();
			clickLocation = inverseVTM.transform(event.getPoint(), null);
			
			if (event.isControlDown())
				gw.multiSelect(clickLocation);
			else
				gw.select(clickLocation);
		}
	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {

	}

	public void mousePressed(MouseEvent e) {

	}

	public void mouseReleased(MouseEvent e) {
		
	}

}
