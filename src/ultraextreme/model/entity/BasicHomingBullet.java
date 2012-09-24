package ultraextreme.model.entity;

import javax.vecmath.Vector2d;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * A bullet that constantly changes its direction towards its target, 
 * until it runs out of fuel and then continues straight forward.
 * 
 * @author Viktor Anderling
 *
 */
public class BasicHomingBullet extends AbstractHomingBullet {

	private Vector2d vector;
	
	/**
	 * Distance left before stopping to track the enemy.
	 */
	private double bulletFuel;

	public BasicHomingBullet(double x, double y, int width, int height,
			PlayerID playerId, DestroyableEntity target) {
		super(x, y, width, height, playerId, Direction.UP);
		this.setTarget(target);
		vector = new Vector2d();
		updateDirection();
		bulletFuel = Constants.getInstance().getLevelDimension().getY() * 0.8; // Will track 80% of the levels length.

	}

	private void updateDirection() {
		Position targetPosition = target.getPosition();
		Position thisPosition = this.getPosition();
		vector.normalize(new Vector2d(targetPosition.getX()
				- thisPosition.getX(), targetPosition.getY()
				- thisPosition.getY()));
	}

	@Override
	public void doMovement(float timePassed) {
		if (!((DestroyableEntity) target).isDestroyed() || bulletFuel < 0) {
			updateDirection();
		}
		double xMovement = vector.x * timePassed * this.getSpeedMod();
		double yMovement = vector.y * timePassed * this.getSpeedMod();
		bulletFuel = bulletFuel - Math.sqrt(xMovement * xMovement + yMovement * yMovement) ;
		
		this.move(xMovement, yMovement);
	}

}
