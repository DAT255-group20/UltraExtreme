package ultraextreme.model.enemy;

import junit.framework.TestCase;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.Position;
/**
 * Test for a hitAndRunEnemy
 * @author Johan Gronvall
 *
 */
public class HitAndRunEnemyTest extends TestCase {
	Position startPoint;
	Position endPoint;
	Position firePoint;
	HitAndRunEnemy enemy;
	BulletManager manager;
	
	public void setUp() {
		manager = new BulletManager();
		WeaponFactory.initialize(manager);
		startPoint = new Position(5, 10);
		endPoint = new Position(20, 20);
		firePoint = new Position(15, 15);
		enemy = new HitAndRunEnemy(startPoint, firePoint, endPoint);
	}

	public void testGoTowardPosition() {
		enemy.goTowardPosition(firePoint, (float) 0.01);
		assertTrue(enemy.getShip().getPosition().getX() > 5.0);
		assertTrue(enemy.getShip().getPosition().getY() > 10.0);
		
		enemy.goTowardPosition(firePoint, 1000);
		assertEquals(enemy.getShip().getPosition(), endPoint);
		
		Position negativePosition = new Position(-5,-5);
		enemy.goTowardPosition(negativePosition, (float) 0.005);
		assertTrue(enemy.getShip().getPosition().getX() < 15);
		assertTrue(enemy.getShip().getPosition().getY() < 15);
		
		enemy.goTowardPosition(negativePosition, 1000);
		assertEquals(enemy.getShip().getPosition(), negativePosition);
	}

	public void testUpdate() {
		boolean stopped = false;
		int secondsStopped = 0;
		boolean supposedToStartMoving = false;
		boolean startedMovingAgain = false;
		boolean shot = false;
		for(int i = 0; i < 10000; i++) {
			enemy.update((i*0.1f));
			if(enemy.getShip().getPosition().equals(new Position(15,15))) {
				secondsStopped+=0.1;
			}
			if (secondsStopped == enemy.getWaitingTime() && stopped==false) {
				stopped = true;
				supposedToStartMoving = true;
				shot = (!manager.getBullets().isEmpty());
			}
			if (supposedToStartMoving) {
				if(enemy.getShip().getPosition().equals(endPoint))
				startedMovingAgain = true;
			}
		}
		assertTrue(stopped);
		assertTrue(startedMovingAgain);
		assertTrue(shot);
	}
	public void testHitAndRunEnemyConstructor() {
		assertNotNull(enemy);
		assertNotNull(enemy.getWeapon());
		assertEquals(startPoint, enemy.getShip().getPosition());
	}
}
