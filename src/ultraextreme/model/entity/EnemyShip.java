package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Rotation;

/**
 * Class representing a hostile entity which can be destroyed by the player
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class EnemyShip extends AbstractDestroyableEntity {

	private int hitPoints;

	private static double speedMod = Constants.getInstance()
			.getEnemySpeedModifier();

	public EnemyShip(final double x, final double y, final int width,
			final int height, final Rotation rotation, int hitpoints,
			ObjectName objectName) {
		super(x, y, width, height, rotation, objectName);
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
	@Override
	public void receiveDamage(int damage) {
		hitPoints -= damage;
	}

	public boolean isOutsideOfWorld() {
		// adding a marginal to screen dimension since the enemy can be right
		// off the screen (eg. spawning)
		double marginal = 150;
		Dimension screen = Constants.getInstance().getLevelDimension();
		return getPosition().getY() - getHeight() < -marginal
				|| getPosition().getX() - getWidth() < -marginal
				|| getPosition().getX() > screen.getX() + marginal
				|| getPosition().getY() > screen.getY() + marginal;
	}
}