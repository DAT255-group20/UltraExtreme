package ultraextreme.model.entity;

import ultraextreme.model.util.Position;

public interface IEntity {

	boolean collidesWith(IEntity other);

	Position getPosition();

	int getHeight();

	int getWidth();
	
	/**
	 * Returns true if and only if the entity is entirely outside of the screen
	 * 
	 * @return true if and only if the entity is entirely outside of the screen
	 */
	boolean isOutOfScreen();
}
