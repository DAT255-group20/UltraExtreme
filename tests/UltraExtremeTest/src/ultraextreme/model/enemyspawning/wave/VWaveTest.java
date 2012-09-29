package ultraextreme.model.enemyspawning.wave;

import junit.framework.TestCase;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class VWaveTest extends TestCase {

	private BulletManager bulletManager;

	private EnemyCollector enemyCollector;

	private AbstractWave wave;

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		enemyCollector = new EnemyCollector();
	}

	private void initWave(double rotation, int x, int y) {
		wave = new VWave(rotation, x, y, bulletManager);
		wave.addListener(enemyCollector);
	}

	/**
	 * Create a new Vwave and see if it spawns its enemies and if the wave
	 * eventually ends.
	 */
	public void testUpdate1() {
		initWave(0, 0, 0);
		
		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 0);
		
		wave.update(0);
		
		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		wave.update(1.98f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		wave.update(0.03f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 3);

		wave.update(2);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		wave.update(2);

		assertTrue(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 7);
	}

	/**
	 * Create a new wave and see if the enemy spawned has the correct
	 * properties.
	 */
	public void testSpawnedEnemyPropteries() {
		initWave(0, 0, 0);
		wave.update(0);
		EnemyShip enemyShip = enemyCollector.getSpawnedEnemies().get(0)
				.getShip();
		assertEquals(enemyShip.getRotation().getAngle(), 0.0);
		assertEquals(enemyShip.getPosition().getX(), 0.0);
		assertEquals(enemyShip.getPosition().getY(), 0.0);

		initWave(2, 100, 200);
		wave.update(0);
		enemyShip = enemyCollector.getSpawnedEnemies().get(1).getShip();
		assertEquals(enemyShip.getRotation().getAngle(), 2.0);
		assertEquals(enemyShip.getPosition().getX(), 100.0);
		assertEquals(enemyShip.getPosition().getY(), 200.0);
	}
}
