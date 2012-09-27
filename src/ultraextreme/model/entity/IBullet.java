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

	boolean isOutOfScreen();

	void doMovement(float timeElapsed);

	Rotation getRotation();

	PlayerID getPlayerId();

	void markForRemoval();

	boolean isMarkedForRemoval();

	int getDamage();

}