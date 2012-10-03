package ultraextreme.model.entity;

import javax.vecmath.Vector2d;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * A bullet that constantly changes its direction towards its target, until it
 * runs out of fuel and then continues straight forward.
 * 
 * @author Viktor Anderling
 * 
 */
public class BasicHomingBullet extends AbstractHomingBullet {

	private final Vector2d normDirectionVector;

	/**
	 * Distance left before stopping to track the enemy.
	 */
	private double bulletFuel;

	public BasicHomingBullet(final double x, final double y, final int width,
			final int height, final PlayerID playerId,
			AbstractDestroyableEntity target) {
		super(x, y, width, height, playerId, new Rotation(0),
				ObjectName.BASIC_HOMING_BULLET);
		this.setTarget(target);
		normDirectionVector = new Vector2d();
		updateDirection();
		bulletFuel = Constants.getInstance().getLevelDimension().getY() * 0.8; // Will
																				// track
																				// 80%
																				// of
																				// the
																				// levels
																				// length.

	}

	private void updateDirection() {
		final Position targetPosition = target.getPosition();
		final Position thisPosition = this.getPosition();
		normDirectionVector.normalize(new Vector2d(targetPosition.getX()
				- thisPosition.getX(), targetPosition.getY()
				- thisPosition.getY()));
	}

	@Override
	public void doMovement(float timePassed) {
		if (!((AbstractDestroyableEntity) target).isDestroyed()
				|| bulletFuel < 0) {
			updateDirection();
		}
		final double xMovement = normDirectionVector.x * timePassed
				* this.getSpeedMod();
		final double yMovement = normDirectionVector.y * timePassed
				* this.getSpeedMod();
		bulletFuel = bulletFuel
				- Math.sqrt(xMovement * xMovement + yMovement * yMovement);

		this.move(xMovement, yMovement);
	}

	@Override
	public Vector2d getNormalizedDirection() {
		return normDirectionVector;

	}

}
