package ultraextreme.model.enemyspawning.wavelist;

import java.util.Random;

import ultraextreme.model.enemyspawning.wave.HorizontalLineWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.enemyspawning.wave.VerticalLineWave;
import ultraextreme.model.enemyspawning.wave.Wave;
import ultraextreme.model.item.BulletManager;

/**
 * This wave list return a specified number of random enemy waves.
 * 
 * @author Daniel Jonsson
 * 
 */
public class RandomWaveList extends AbstractWaveList {

	private Wave currentWave;

	private float currentSpawningTime;

	private BulletManager bulletManager;

	private int counter;

	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param numberOfWaves
	 *            Maximum number of waves this will return.
	 * @param bulletManager
	 *            Reference to a BulletManager that the enemies will add their
	 *            bullets to.
	 */
	public RandomWaveList(int numberOfWaves, BulletManager bulletManager) {
		super(numberOfWaves);
		this.counter = 0;
		this.bulletManager = bulletManager;
		this.generateNewWave();
	}

	/**
	 * Update currentWave and currentSpawningTime with new random values.
	 */
	private void generateNewWave() {
		// TODO: This needs some more work.
		counter %= 3;
		switch (counter) {
		case 0:
			currentWave = new VWave(0, 200, -10, bulletManager);
			break;
		case 1:
			currentWave = new HorizontalLineWave(5, 3, Math.PI / 8, 400, -10,
					bulletManager);
			break;
		case 2:
			currentWave = new VerticalLineWave(2, 0, 200, -10, bulletManager);
			break;
		}
		counter++;
		Random random = new Random();
		currentSpawningTime += random.nextFloat() * 2 + 3.5;
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
