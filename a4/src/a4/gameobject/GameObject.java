package a4.gameobject;

import java.awt.Color;
import java.awt.geom.AffineTransform;

public abstract class GameObject implements IDrawable, ICollider {
	private Color objColor;
	private AffineTransform myTranslate;
	private AffineTransform myScale;
	private AffineTransform myRotate;
	
	public GameObject(float xLoc, float yLoc, Color objColor) {
		myTranslate = new AffineTransform();
		myScale = new AffineTransform();
		myRotate = new AffineTransform();
		
		myTranslate.translate(xLoc, yLoc);
		myScale.scale(1, 1);
		this.objColor = objColor;
	}

	public double getxLoc() {
		return myTranslate.getTranslateX();
	}
	
	public double getyLoc() {
		return myTranslate.getTranslateY();
	}

	public AffineTransform getMyTranslate() {
		return myTranslate;
	}

	public AffineTransform getMyScale() {
		return myScale;
	}

	public AffineTransform getMyRotate() {
		return myRotate;
	}

	public void setMyTranslate(AffineTransform myTranslate) {
		this.myTranslate = myTranslate;
	}

	public void setMyScale(AffineTransform myScale) {
		this.myScale = myScale;
	}

	public void setMyRotate(AffineTransform myRotate) {
		this.myRotate = myRotate;
	}

	public Color getObjColor() {
		return objColor;
	}
}
