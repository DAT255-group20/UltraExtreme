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
	 * @return The ship of this enemy.
	 */
	EnemyShip getShip();

	/**
	 * @return true if this enemy has been destroyed
	 */
	boolean isDead();

	/**
	 * true if and only if this enemy should be spawning a pickup
	 * 
	 * @return true if and only if this enemy should be spawning a pickup
	 */
	boolean shouldSpawnPickup();

	/**
	 * @return The weapon of this enemy.
	 */
	AbstractWeapon getWeapon();

	/**
	 * @return The score value that is earned by killing this enemy.
	 */
	int getScoreValue();
}