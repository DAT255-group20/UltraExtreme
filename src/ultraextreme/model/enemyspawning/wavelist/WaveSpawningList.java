package ultraextreme.model.enemyspawning.wavelist;

import ultraextreme.model.enemyspawning.wave.Wave;

/**
 * A WaveSpawningList has a number of enemy waves (the Wave class), that a
 * EnemySpawner uses to grab waves from.
 * 
 * @author Daniel Jonsson
 * 
 */
public interface WaveSpawningList {

	/**
	 * Get the current wave.
	 * 
	 * @return The current wave.
	 */
	public Wave getCurrentWave();

	/**
	 * Get when the current wave is supposed to start spawning.
	 * 
	 * @return When the wave is supposed to start spawn.
	 */
	public float getCurrentSpawningTime();

	/**
	 * Move the list to the next wave entry. Observe that it's not possible to
	 * go back again, so make sure the last wave has spawned.
	 */
	public void next();

	/**
	 * Get which wave that was the latest to spawn.
	 * 
	 * @return Number of the latest wave.
	 */
	public int getCurrentWaveNumber();

	/**
	 * The maximum of available levels.
	 * 
	 * @return The number of available levels.
	 */
	public int getNumberOfWaves();

	/**
	 * If there exists a next wave.
	 * 
	 * @return If there exists a next wave.
	 */
	public boolean hasNext();

}
