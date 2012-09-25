package ultraextreme.model.util;

import javax.vecmath.Vector2d;

/**
 * This immutable class defines a direction in which an entity may move.
 * 
 * @author Viktor Anderling
 * 
 */
public class Direction {

	/**
	 * The angle measured in radians.
	 * As it gets larger it will turn counterclockwise.
	 */
	private double angle;

	/**
	 * Creates a direction with the chosen angle.
	 * 
	 * @param angle
	 * 			The chosen angle.
	 */
	public Direction(double angle) {
			this.angle = angle;
	}
	
	/**
	 * Gets the coordinates rotated by the angle.
	 * If the angle is 0, the same coordinates as the input will be returned.
	 * 
	 * @param x
	 * 			The x-value of the coordinate.
	 * @param y
	 * 			The y-value of the coordinate.
	 * 
	 * @return The rotated form of the coordinate counterclockwise.
	 */
	public Vector2d getRotatedCoordinates(double x, double y) {
		return new Vector2d(x * Math.cos(angle) - y * Math.sin(angle), 
				x * Math.sin(angle) + y * Math.cos(angle));
	}
}
