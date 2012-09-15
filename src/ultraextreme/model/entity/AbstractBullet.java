package ultraextreme.model.entity;

import ultraextreme.model.util.PlayerID;

public abstract class AbstractBullet extends Entity {

	PlayerID playerID;
	
	public abstract void doMovement(int timePassed);
}