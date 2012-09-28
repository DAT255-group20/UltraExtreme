package ultraextreme.model.enemyspawning;

import junit.framework.TestCase;
import ultraextreme.model.enemyspawning.wavelist.RandomGenerator;
import ultraextreme.model.enemyspawning.wavelist.RandomWaveList;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemySpawnerTest extends TestCase {

	/**
	 * Create a EnemySpawner, give it a RandomWaveList with a custom
	 * RandomGenerator and see if the EnemySpawner behaves correctly.
	 */
	public void testUpdateMethod() {
		BulletManager bulletManager = new BulletManager();
		RandomWaveList waveList = new RandomWaveList(1, bulletManager,
				new RandomGenerator() {
					private int counter = 0;

					@Override
					public float nextFloat() {
						return ++counter;
					}
				});
		EnemySpawner enemySpawner = new EnemySpawner(waveList);
		EnemyCollector enemyCollector = new EnemyCollector();
		enemySpawner.addPropertyChangeListener(enemyCollector);

		assertEquals(enemySpawner.getCurrentWave(), 0);

		enemySpawner.update(0); // A VWave has spawned

		assertEquals(enemySpawner.getCurrentWave(), 1);

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		enemySpawner.update(1.98f); // Timer in VWave is ~1.98

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		enemySpawner.update(0.03f); // Timer in VWave is ~2.01 and 2 enemies
									// spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 3);

		enemySpawner.update(2f); // 2 New enemies spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 5);

		enemySpawner.update(2f); // 2 New enemies spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 7);

		enemySpawner.update(2f); // No new enemies spawn

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 7);
	}
}
