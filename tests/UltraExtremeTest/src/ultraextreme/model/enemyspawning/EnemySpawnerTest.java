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

	public void testUpdateMethod() {
		BulletManager bulletManager = new BulletManager();
		RandomWaveList waveList = new RandomWaveList(1, bulletManager,
				new RandomGenerator() {
					private int counter;

					@Override
					public float nextFloat() {
						return ++counter;
					}
				});
		EnemySpawner enemySpawner = new EnemySpawner(waveList);
		EnemyCollector enemyCollector = new EnemyCollector();
		enemySpawner.addPropertyChangeListener(enemyCollector);

		enemySpawner.update(5.4f);

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 0);

		enemySpawner.update(0.1f); // A VWave has spawned

		assertEquals(enemyCollector.getSpawnedEnemies().size(), 1);

		enemySpawner.update(1.88f); // Timer in VWave is ~1.98

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
