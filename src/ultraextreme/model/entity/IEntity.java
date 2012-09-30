package ultraextreme.model.entity;

import ultraextreme.model.util.Position;

public interface IEntity {

	boolean collidesWith(IEntity other);

	Position getPosition();

	int getHeight();

	int getWidth();

	/**
	 * Determines whether the entity is off the screen.
	 * @return true if and only if the entity is entirely outside of the screen
	 */
	boolean isOutOfScreen();
	
	/**
	 * Determines whether the entity is off the screen + the provided marginal. For an example, say that the screen is 100x150 pixels. Then, if the marginal is 0, this method would return true if and only if the entity is entirely outside a rectangle of 100x150 pixels. If the margin was 20, then the rectangle to compare with would be 120x170 pixels. If the margin was -10, the rectangle to compare with would be 90x140 pixels.
	 * @return true if and only if the entity is entirely outside the screen borders + the marginal
	 */
	boolean isOutOfScreen(double marginal);
}
