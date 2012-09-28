package ultraextreme.model.enemyspawning.wavelist;

/**
 * This can be given to the RandomWaveList's constructor, so you can customize
 * the random numbers it will use. This makes the list more testable.
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class RandomGenerator {

	/**
	 * Return a float between 0.0 and 1.0.
	 * 
	 * @return A random float between 0.0 and 1.0.
	 */
	public abstract float nextFloat();
}
