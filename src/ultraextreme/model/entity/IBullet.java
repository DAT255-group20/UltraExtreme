package ultraextreme.model.entity;

import ultraextreme.model.util.Direction;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.PlayerID;

/**
 * An interface for a Bullet
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IBullet {

	Position getPosition();

	int getWidth();

	int getHeight();

	boolean isOutOfScreen();

	void doMovement(float timeElapsed);

	Direction getDirection();

	PlayerID getPlayerId();

}