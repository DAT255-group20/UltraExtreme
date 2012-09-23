package ultraextreme.model.entity;

import javax.vecmath.Vector2d;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

public class BasicHomingBullet extends AbstractHomingBullet {

	private Vector2d vector;

	public BasicHomingBullet(double x, double y, int width, int height,
			PlayerID playerId, DestroyableEntity target) {
		super(x, y, width, height, playerId, Direction.UP);
		this.setTarget(target);
		vector = new Vector2d();
		updateDirection();

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
		if (!((DestroyableEntity) target).isDestroyed()) {
			updateDirection();
		}
		this.move(vector.x * timePassed * this.getSpeedMod(), vector.y
				* timePassed * this.getSpeedMod());
	}

}
