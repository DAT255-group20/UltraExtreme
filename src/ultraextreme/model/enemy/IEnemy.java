package ultraextreme.model.enemy;

/**
 * Interface for self-thinking enemies
 * 
 * @author Johan Gronvall
 * 
 */
public interface IEnemy {
	/**
	 * Update method called once per loop, updating the enemy's behavior
	 * 
	 * @param timePassed
	 *            since last update in (seconds?)
	 */
	public void update(float timePassed);
}
