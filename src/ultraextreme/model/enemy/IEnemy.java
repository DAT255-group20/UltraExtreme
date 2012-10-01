package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;

/**
 * Interface for self-thinking enemies
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
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
	void update(float timePassed);

	// TODO Write javadoc
	EnemyShip getShip();

	/**
	 * returns true if this enemy has been destroyed
	 * 
	 * @return true if this enemy has been destroyed
	 */
	boolean isDead();
	/**
	 * true if and only if this enemy should be spawning a pickup
	 * @return true if and only if this enemy should be spawning a pickup
	 */
	boolean shouldSpawnPickup();

	// TODO Write javadoc
	AbstractWeapon getWeapon();

	int getScoreValue();
}