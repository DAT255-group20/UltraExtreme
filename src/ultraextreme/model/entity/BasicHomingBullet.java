package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

public class BasicHomingBullet extends AbstractHomingBullet {

	public BasicHomingBullet(double x, double y, int width, int height,
			PlayerID playerId, DestroyableEntity target) {
		super(x, y, width, height, playerId, Direction.UP);
		this.setTarget(target);
	}

	@Override
	public void doMovement(float timePassed) {
		// TODO Test before optimizing code!
		Position targetPosition = target.getPosition();
		Position thisPosition = this.getPosition();
		double deltaX = targetPosition.getX() - thisPosition.getX();
		double deltaY = targetPosition.getY() - thisPosition.getY();
		double moveX = 0;
		double moveY = 0;
		double speedMod = this.getSpeedMod();

		if (deltaX * deltaY == 0) {
			if (deltaX + deltaY == 0) {
				moveX = 0;
				moveY = 0;
			} else if (deltaX == 0) {
				moveX = 0;
				moveY = timePassed * this.getSpeedMod();
			} else if (deltaY == 0) {
				moveY = 0;
				moveX = timePassed * this.getSpeedMod();
			}
		} else {
			double k = Math.abs(Math.min(deltaY, deltaX)
					/ Math.max(deltaY, deltaX));
			if (deltaY < deltaX) {
				moveX = 1;
				moveY = k;
			} else {
				moveY = 1;
				moveX = k;
			}
		}
		this.move(moveX * Math.signum(deltaX) * timePassed * speedMod, moveY
				* Math.signum(deltaY) * timePassed * speedMod);
	}

}
