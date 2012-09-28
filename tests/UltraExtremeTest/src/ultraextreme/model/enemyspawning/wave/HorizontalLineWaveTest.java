package ultraextreme.model.enemyspawning.wave;

import junit.framework.TestCase;
import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class HorizontalLineWaveTest extends TestCase {

	private BulletManager bulletManager;

	private EnemyCollector enemyCollector;

	private Wave wave;

	@Override
	public void setUp() {
		bulletManager = new BulletManager();
		enemyCollector = new EnemyCollector();
	}

	private void initWave(int numOfEnemies, int numOfLines, double rotation,
			int x, int y) {
		wave = new HorizontalLineWave(numOfEnemies, numOfLines, rotation, x, y,
				bulletManager);
		wave.addListener(enemyCollector);
	}

	/**
	 * Create a new wave containing 1 enemy and see if it spawns and if the wave
	 * ends.
	 */
	public void testUpdate1() {
		initWave(1, 1, 0, 0, 0);
		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 0);
		wave.update(0);
		assertTrue(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);
	}

	/**
	 * Create a new wave containing 3 rows with 5 enemies in each, then see if
	 * they spawn correctly.
	 */
	public void testUpdate2() {
		initWave(5, 3, 0, 0, 0);
		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 0);

		wave.update(0.01f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		wave.update(1.98f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		wave.update(0.03f);

		assertFalse(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 10);

		wave.update(2);

		assertTrue(enemyCollector.hasWaveEnded());
		assertEquals(enemyCollector.getSpawnedEnemies().size(), 15);
	}

	/**
	 * Create a new wave and see if the enemy spawned has the correct
	 * properties.
	 */
	public void testSpawnedEnemyPropteries() {
		initWave(1, 1, 0, 0, 0);
		wave.update(0);
		EnemyShip enemyShip = enemyCollector.getSpawnedEnemies().get(0)
				.getShip();
		assertEquals(enemyShip.getRotation().getAngle(), 0.0);
		assertEquals(enemyShip.getPosition().getX(), 0.0);
		assertEquals(enemyShip.getPosition().getY(), 0.0);

		initWave(1, 1, 2, 100, 200);
		wave.update(0);
		enemyShip = enemyCollector.getSpawnedEnemies().get(1).getShip();
		assertEquals(enemyShip.getRotation().getAngle(), 2.0);
		assertEquals(enemyShip.getPosition().getX(), 100.0);
		assertEquals(enemyShip.getPosition().getY(), 200.0);
	}
}
