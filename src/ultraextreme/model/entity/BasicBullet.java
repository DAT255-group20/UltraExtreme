package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * A basic bullet.
 * 
 * @author Daniel Jonsson, Viktor Anderling
 * 
 */
public class BasicBullet extends AbstractBullet {

	public BasicBullet(PlayerID playerId, Direction direction) {
		super(playerId, direction);
	}
	
	public BasicBullet(PlayerID playerId) {
		this(playerId, Direction.UP);
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(1 * timePassed, 1 * timePassed);
	}
}
