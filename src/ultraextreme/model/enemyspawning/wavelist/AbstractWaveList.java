package ultraextreme.model.enemyspawning.wavelist;

/**
 * 
 * @author Daniel Jonsson
 *
 */
public abstract class AbstractWaveList implements WaveSpawningList {

	/**
	 * The number of waves that this wave list has spawned.
	 */
	private int currentWaveNumber;

	/**
	 * Total number of waves that this list contains.
	 */
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
