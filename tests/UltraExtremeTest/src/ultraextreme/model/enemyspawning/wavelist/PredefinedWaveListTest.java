package ultraextreme.model.enemyspawning.wavelist;

import junit.framework.TestCase;

import org.junit.Test;

import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 * 
 */
public class PredefinedWaveListTest extends TestCase implements
		AbstractWaveListTest {

	private PredefinedWaveList waveList;

	@Override
	public void setUp() {
		resetWaveList();
	}

	/**
	 * Reset the instance variable waveList.
	 */
	private void resetWaveList() {
		waveList = new PredefinedWaveList(new BulletManager());
	}

	@Override
	@Test
	public void testGetNumberOfWaves() {
		resetWaveList();
		assertEquals(5, waveList.getNumberOfWaves());
	}

	@Test
	public void testNext() {
		// Call next() a number of times on the wave list
		for (int i = 1; i < waveList.getNumberOfWaves(); ++i) {
			assertEquals(i, waveList.getCurrentWaveNumber());
			assertTrue(waveList.hasNext());
			waveList.next();
		}
		// Now there shouldn't be anything left in the list
		assertEquals(waveList.getNumberOfWaves(),
				waveList.getCurrentWaveNumber());
		assertFalse(waveList.hasNext());
	}
	
	public void testGenerateWaves() {
		fail("Need some implementation!!!1 maybe");
	}

}
