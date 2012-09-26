package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.IEnemy;

/**
 * A class that wants to get updates from a wave should implement this
 * interface. Mainly made for the EnemySpawner class, which handles the active
 * waves.
 * 
 * @author Daniel Jonsson
 * 
 */
public interface WaveListener {

	/**
	 * A wave that has ended (i.e. spawned all its enemies).
	 * 
	 * @param wave
	 *            Reference to the wave that has ended.
	 */
	public void waveEnded(Wave wave);

	/**
	 * A Wave has spawned an enemy.
	 * 
	 * @param enemy
	 *            The enemy that has spawned.
	 */
	public void enemySpawned(IEnemy enemy);

}
