package ultraextreme.model.enemyspawning.wavelist;

import org.andengine.util.VerticalAlign;

import junit.framework.TestCase;
import ultraextreme.model.enemyspawning.wave.HorizontalLineWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.enemyspawning.wave.VerticalLineWave;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class RandomWaveListTest extends TestCase {

	/**
	 * This test will create a new RandomWaveList, generate some waves and check
	 * if they got the correct spawn time and are instance of the right wave.
	 */
	public void testGeneratingNewWaves() {
		BulletManager bulletManager = new BulletManager();
		RandomWaveList waveList = new RandomWaveList(100, bulletManager,
				new RandomGenerator() {
					private int counter;

					@Override
					public float nextFloat() {
						return ++counter;
					}
				});

		assertEquals(waveList.getCurrentSpawningTime(), 5.5f);
		assertTrue(waveList.getCurrentWave() instanceof VWave);

		waveList.next();

		// 5.5 + 7.5 = 13
		assertEquals(waveList.getCurrentSpawningTime(), 13f);
		assertTrue(waveList.getCurrentWave() instanceof HorizontalLineWave);

		waveList.next();

		// 5.5 + 7.5 + 9.5 = 22.5
		assertEquals(waveList.getCurrentSpawningTime(), 22.5f);
		assertTrue(waveList.getCurrentWave() instanceof VerticalLineWave);

		waveList.next();

		// 5.5 + 7.5 + 9.5 + 11.5 = 34
		assertEquals(waveList.getCurrentSpawningTime(), 34f);
		assertTrue(waveList.getCurrentWave() instanceof VWave);

	}

}
