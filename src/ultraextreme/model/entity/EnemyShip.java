package ultraextreme.model.entity;

/**
 * Class representing a hostile entity which can be destroyed
 * by the player
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class EnemyShip extends DestroyableEntity {

	private int hitPoints;
	
	public EnemyShip(double x, double y, int width, int height, int hitpoints) {
		super(x, y, width, height);
		this.hitPoints = hitpoints;
	}
	
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return hitPoints < 1;
	}
	
}