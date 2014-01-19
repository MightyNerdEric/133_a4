package a4;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import a4.gameobject.*;

public class MapView extends JPanel implements IObserver {
	private Iterator graphicsObjects;
	private GameObjectCollection goc;
	private GameWorldProxy gwp;
	private AffineTransform vtm;
	private AffineTransform w2ndScale;
	private AffineTransform w2ndTranslate;
	private double scale;
	private double winLeft;
	private double winBottom;
	private Point winCenter;
		
	public MapView() {
		setBorder(new EtchedBorder());
		setBackground(Color.white);
		setVisible(true);
		scale = 1;
		winLeft = 0;
		winBottom = 0;
		winCenter = new Point(450, 450);
		
		//Build VTM
		vtm = new AffineTransform();
		buildDefaultVTM();
		
	}

	public void update(IObservable gwp, Object arg) {
		this.gwp = (GameWorldProxy)gwp;
		goc = this.gwp.getGWCollection();
		graphicsObjects = goc.iterator();
		repaint();
	}

	public void update(IObservable gwp) {
		this.gwp = (GameWorldProxy)gwp;
		goc = this.gwp.getGWCollection();
		graphicsObjects = goc.iterator();
		repaint();
	}
	
	private void buildDefaultVTM() {
		vtm.translate(0, 900);
		vtm.scale(1, -1);
	}
	
	public void buildVTM() {
		AffineTransform worldToND = new AffineTransform();
		AffineTransform NDtoScreen = new AffineTransform();
		
		worldToND.scale((double)1/getWidth()/scale, (double)1/this.getHeight()/scale);
		worldToND.translate(-winLeft, -winBottom);
		NDtoScreen.translate(0, this.getHeight());
		NDtoScreen.scale(this.getWidth(), -this.getHeight());
		
		vtm = (AffineTransform) NDtoScreen.clone();
		vtm.concatenate(worldToND);
	}
	
	public AffineTransform getInverseVTM() {
		AffineTransform inverseVTM;
		buildVTM();
		inverseVTM = (AffineTransform) vtm.clone();
		try {
			inverseVTM.invert();
		} catch (NoninvertibleTransformException e) {
			e.printStackTrace();
		}
		return inverseVTM;
	}
	
	public void scale(double newScale) {
		this.scale *= newScale;
		winLeft = winCenter.x - ((double)this.getWidth()/2)*scale;
		winBottom = winCenter.y - ((double)this.getHeight()/2)*scale;
		
		goc = gwp.getGWCollection();
		graphicsObjects = goc.iterator();
		repaint();
	}
	
	public void pan(int dx, int dy) {
		winLeft += dx;
		winBottom -= dy;
		winCenter.translate(dx, -dy);
		
		goc = gwp.getGWCollection();
		graphicsObjects = goc.iterator();
		repaint();
	}
	
	/**
	 * Overrides default paintComponent. Calls super.paintComponent(g) to paint the box,
	 * and then manually paints each GameWorld object.
	 * 
	 * @param g The graphics context for this MapView
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		AffineTransform saveAT = g2d.getTransform();
		buildVTM();
		g2d.transform(vtm);
		if (graphicsObjects != null) {
			while(graphicsObjects.hasNext()) {
				GameObject go = (GameObject)graphicsObjects.next();
				go.toString();
				if (go instanceof Missile)
					((Missile)go).draw(g2d);
				else if (go instanceof Rock)
					((Rock)go).draw(g2d);
				else if (go instanceof Tank)
					((Tank)go).draw(g2d);
				else if (go instanceof Tree)
					((Tree)go).draw(g2d);
			}
		}
		g2d.setTransform(saveAT);
	}

}
