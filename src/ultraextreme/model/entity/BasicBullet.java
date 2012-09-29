package ultraextreme.model.entity;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Rotation;

/**
 * A basic bullet that flies straight forward.
 * 
 * @author Daniel Jonsson, Viktor Anderling
 * 
 */
public class BasicBullet extends AbstractBullet {

	public BasicBullet(final double x, final double y, final int width,
			final int height, PlayerID playerId, final Rotation rotation) {
		super(x, y, width, height, playerId, rotation, ObjectName.BASIC_BULLET);
	}

	@Override
	public void doMovement(float timePassed) {
		this.move(0, timePassed * getSpeedMod());
	}
}
