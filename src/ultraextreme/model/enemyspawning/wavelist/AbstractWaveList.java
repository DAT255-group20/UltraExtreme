package ultraextreme.model.enemyspawning.wavelist;

public abstract class AbstractWaveList implements WaveSpawningList {

	private int currentWaveNumber;

	private int numberOfWaves;

	/**
	 * @param numberOfWaves
	 *            The number of available waves.
	 */
	public AbstractWaveList(int numberOfWaves) {
		this.numberOfWaves = numberOfWaves;
		this.currentWaveNumber = 1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		currentWaveNumber++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCurrentWaveNumber() {
		return currentWaveNumber;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getNumberOfWaves() {
		return numberOfWaves;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean hasNext() {
		return currentWaveNumber <= numberOfWaves;
	}
}
