package ultraextreme.model.entity;

import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * An interface for a Bullet
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IBullet extends IEntity {

	@Override
	Position getPosition();

	@Override
	int getWidth();

	@Override
	int getHeight();

	/**
	 * @return The rotation of the bullet.
	 */
	Rotation getRotation();

	/**
	 * @return The ID of the player that shot the bullet.
	 */
	PlayerID getPlayerId();

	/**
	 * @return true if the bullet is marked for removal.
	 */
	boolean isMarkedForRemoval();

	/**
	 * @return The amount of damage that this bullet inflicts.
	 */
	int getDamage();

	/**
	 * Marks this bullet for removal.
	 */
	void markForRemoval();
}