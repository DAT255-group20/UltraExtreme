package ultraextreme.model.entity;

import javax.vecmath.Vector2d;

import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IEntity {

	/**
	 * Determines whether this entity is colliding with another entity.
	 * 
	 * @param other
	 *            The other entity.
	 * @return true if this entity is colliding with the other entity.
	 */
	boolean collidesWith(IEntity other);

	/**
	 * @return The position of this entity.
	 */
	Position getPosition();

	/**
	 * @return The height of this entity.
	 */
	int getHeight();

	/**
	 * @return The width of this entity.
	 */
	int getWidth();

	/**
	 * Determines whether the entity is off the screen.
	 * 
	 * @return true if and only if the entity is entirely outside of the screen
	 */
	boolean isOutOfScreen();

	/**
	 * Determines whether the entity is off the screen + the provided marginal.
	 * For an example, say that the screen is 100x150 pixels. Then, if the
	 * marginal is 0, this method would return true if and only if the entity is
	 * entirely outside a rectangle of 100x150 pixels. If the margin was 20,
	 * then the rectangle to compare with would be 120x170 pixels. If the margin
	 * was -10, the rectangle to compare with would be 90x140 pixels.
	 * 
	 * @return true if and only if the entity is entirely outside the screen
	 *         borders + the marginal
	 */
	boolean isOutOfScreen(double marginal);

	/**
	 * Returns what kind of entity this is as an ObjectName
	 * 
	 * @return what kind of entity this is as an ObjectName
	 */
	ObjectName getObjectName();

	Vector2d getNormalizedDirection();
}