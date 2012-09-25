package ultraextreme.model.enemyspawning.wavelist;

import java.util.Random;

import ultraextreme.model.enemyspawning.wave.Wave;
import ultraextreme.model.item.BulletManager;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public class RandomWaveList extends AbstractWaveList {

	private Wave currentWave;
	
	private float currentSpawningTime;
	
	private BulletManager bulletManager;
	
	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param numberOfWaves
	 *            Maximum number of waves this will return.
	 */
	public RandomWaveList(int numberOfWaves, BulletManager bulletManager) {
		super(numberOfWaves);
		this.generateNewWave();
		this.bulletManager = bulletManager;
	}
	
	/**
	 * Update currentWave and currentSpawningTime with new random values.
	 */
	private void generateNewWave() {
		// TODO: This needs some more work.
		//currentWave = new Wave(bulletManager);
		Random random = new Random();
		currentSpawningTime += random.nextFloat() * 2 + 0.5;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		super.next();
		this.generateNewWave();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wave getCurrentWave() {
		return currentWave;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getCurrentSpawningTime() {
		return currentSpawningTime;
	}
}
