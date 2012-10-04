package ultraextreme.model.enemyspawning.wavelist;

import org.junit.Test;

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
public class RandomWaveListTest extends TestCase implements AbstractWaveListTest {
	
	private RandomWaveList waveList;

	/**
	 * Reset the instance variable waveList.
	 * @param numberOfWaves
	 */
	private void resetWaveList(int numberOfWaves) {
		waveList = new RandomWaveList(numberOfWaves, new BulletManager());
	}
	
	@Override
	@Test
	public void testGetNumberOfWaves() {
		for (int waves = 1; waves < 100000; ++waves) {
			resetWaveList(waves);
			assertEquals(waves, waveList.getNumberOfWaves());
		}
	}

	@Override
	@Test
	public void testNext() {
		// Run through a number of tests where the maximum number of waves are
		// different
		for (int waves = 1; waves < 1000; ++waves) {
			resetWaveList(waves);
			// Call next() a number of times on the wave list
			for (int i = 1; i < waves; ++i) {
				assertEquals(i, waveList.getCurrentWaveNumber());
				assertTrue(waveList.hasNext());
				waveList.next();
			}
			// Now there shouldn't be anything left in the list
			assertEquals(waves, waveList.getCurrentWaveNumber());
			assertFalse(waveList.hasNext());
		}
	}
	
	/**
	 * This test will create a new RandomWaveList, generate some waves and check
	 * if they got the correct spawn time and are instance of the right wave.
	 */
	public void testGeneratingNewWaves() {
		BulletManager bulletManager = new BulletManager();
		RandomWaveList waveList = new RandomWaveList(100, bulletManager,
				new AbstractRandomGenerator() {
					private int counter;

					@Override
					public float nextFloat() {
						return ++counter;
					}
				});

		// 0
		assertEquals(waveList.getCurrentSpawningTime(), 0f);
		assertTrue(waveList.getCurrentWave() instanceof VWave);

		waveList.next();

		// 5.5
		assertEquals(waveList.getCurrentSpawningTime(), 5.5f);
		assertTrue(waveList.getCurrentWave() instanceof HorizontalLineWave);

		waveList.next();

		// 5.5 + 7.5 = 13
		assertEquals(waveList.getCurrentSpawningTime(), 13f);
		assertTrue(waveList.getCurrentWave() instanceof VerticalLineWave);

		waveList.next();

		// 5.5 + 7.5 + 9.5 = 22.5
		assertEquals(waveList.getCurrentSpawningTime(), 22.5f);
		assertTrue(waveList.getCurrentWave() instanceof VWave);

		waveList.next();

		// 5.5 + 7.5 + 9.5 + 11.5 = 34
		assertEquals(waveList.getCurrentSpawningTime(), 34f);
		assertTrue(waveList.getCurrentWave() instanceof HorizontalLineWave);
	}


}
