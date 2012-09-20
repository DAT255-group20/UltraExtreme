package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;

/**
 * Class representing a hostile entity which can be destroyed by the player
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class EnemyShip extends DestroyableEntity {

	private int hitPoints;

	public EnemyShip(double x, double y, int width, int height, Direction direction, int hitpoints) {
		super(x, y, width, height, direction);
		this.hitPoints = hitpoints;
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return hitPoints < 1;
	}

}