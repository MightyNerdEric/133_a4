package a4.gameobject;

public interface ICollider {
	public boolean collidesWith(ICollider otherObject);
	public void handleCollision(ICollider otherObject);
}