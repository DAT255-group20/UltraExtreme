package ultraextreme.model.entity;

import ultraextreme.model.util.Rotation;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.PlayerID;

/**
 * An interface for a Bullet
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IBullet extends IEntity {

	Position getPosition();

	int getWidth();

	int getHeight();

	boolean isOutOfScreen();

	void doMovement(float timeElapsed);

	Rotation getRotation();

	PlayerID getPlayerId();

	void markForRemoval();

	boolean isMarkedForRemoval();

}