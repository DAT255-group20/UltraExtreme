package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;

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

	public EnemyShip getShip();

	/**
	 * returns true if this enemy has been destroyed
	 * 
	 * @return true if this enemy has been destroyed
	 */
	boolean isDead();
}