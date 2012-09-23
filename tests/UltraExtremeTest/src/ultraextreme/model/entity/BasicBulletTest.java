package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class BasicBulletTest extends AbstractBulletTest {

	@Override
	protected AbstractBullet getNewAbstractBullet(double x, double y,
			int width, int height, PlayerID playerId, Direction direction) {
		return new BasicBullet(x, y, width, height, playerId, direction);
	}

	@Override
	public void testDoMovement() {
		double speed = Constants.getInstance().getBulletSpeedModifier();
		AbstractBullet bullet = getNewAbstractBullet(10, 20, 30, 40,
				PlayerID.PLAYER1, Direction.UP);
		
		bullet.doMovement(1);
		assertEquals(bullet.getPosition().getY(), 20 + 1 * speed);
		
		bullet.doMovement(1);
		assertEquals(bullet.getPosition().getY(), 20 + 2 * speed);
		
		bullet.doMovement(10);
		assertEquals(bullet.getPosition().getY(), 20 + 12 * speed);
	}
}
