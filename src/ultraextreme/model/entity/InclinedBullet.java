package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * A class representing a bullet that has the ability to fly with a slope.
 * 
 * @author Viktor Anderling
 * 
 */
public class InclinedBullet extends AbstractBullet {

	/**
	 * The inclination of the bullet. Can be either positive or negative. The
	 * bigger the absolute value of slope is, the bigger the inclination will
	 * be.
	 */
	private double slope;

	/**
	 * @param slope
	 *            The inclination of the bullet.
	 */
	public InclinedBullet(double x, double y, int width, int height,
			PlayerID playerId, Direction direction, double slope) {
		super(x, y, width, height, playerId, direction);
		this.slope = slope;
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(timePassed * slope * getSpeedMod(), timePassed
				* getSpeedMod());

	}

}
