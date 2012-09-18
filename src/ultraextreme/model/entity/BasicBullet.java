package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * A basic bullet that flies straight forward.
 * 
 * @author Daniel Jonsson, Viktor Anderling
 * 
 */
public class BasicBullet extends AbstractBullet {

	public BasicBullet(double x, double y, PlayerID playerId, Direction direction) {
		super(x, y, playerId, direction);
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(0 * timePassed, 1 * timePassed);
	}
}
