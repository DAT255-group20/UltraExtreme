package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.ObjectName;

/**
 * Class representing a hostile entity which can be destroyed by the player
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class EnemyShip extends DestroyableEntity {

	private int hitPoints;

	private static double speedMod = Constants.getInstance()
			.getEnemySpeedModifier();

	public EnemyShip(double x, double y, int width, int height,
			Direction direction, int hitpoints, ObjectName objectName) {
		super(x, y, width, height, direction, objectName);
		this.hitPoints = hitpoints;
	}

	@Override
	public boolean isDestroyed() {
		return hitPoints < 1;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}

	/**
	 * Damages an enemy by subtracting the given parameter from the Enemy's
	 * hitPoints
	 * 
	 * @param damage
	 *            how many hitPoints are to be lost
	 */
	public void damage(int damage) {
		hitPoints -= damage;
	}

}