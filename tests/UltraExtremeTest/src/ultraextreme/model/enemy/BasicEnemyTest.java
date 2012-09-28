package ultraextreme.model.enemy;

import ultraextreme.model.util.Position;

/**
 * @author Johan Gronvall
 */
public class BasicEnemyTest extends AbstractEnemyTest {

	/**
	 * Tests if the enemy has moved forward and test if he has shot after a
	 * period of 10seconds
	 * 
	 */
	public void testUpdate() {
		Position position1 = enemy.getShip().getPosition();
		enemy.update(10f);
		assertTrue(position1.getY() < enemy.getShip().getPosition().getY());
		assertTrue(!bulletManager.getBullets().isEmpty());
	}
}
