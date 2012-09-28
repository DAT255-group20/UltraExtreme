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

	private RandomGenerator randomGenerator;

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
		this(numberOfWaves, bulletManager, new RandomGenerator() {
			@Override
			public float nextFloat() {
				Random random = new Random();
				return random.nextFloat();
			}
		});
	}

	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param numberOfWaves
	 *            Maximum number of waves this will return.
	 * @param bulletManager
	 *            Reference to a BulletManager that the enemies will add their
	 *            bullets to.
	 * @param randomGenerator
	 *            A Class that implements RandomGenerator, which will feed this
	 *            class with random numbers.
	 */
	public RandomWaveList(int numberOfWaves, BulletManager bulletManager,
			RandomGenerator randomGenerator) {
		super(numberOfWaves);
		this.randomGenerator = randomGenerator;
		this.counter = 0;
		this.bulletManager = bulletManager;
		this.generateNewWave();
		this.currentSpawningTime = 0;
	}

	/**
	 * Update currentWave with a new random wave.
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
	}
	
	/**
	 * Update the currentSpawningTime with a random number.
	 */
	private void generateNewSpawningTime() {
		currentSpawningTime += randomGenerator.nextFloat() * 2 + 3.5;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		super.next();
		this.generateNewWave();
		this.generateNewSpawningTime();
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
