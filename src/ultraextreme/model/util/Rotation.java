package ultraextreme.model.util;

import javax.vecmath.Vector2d;

/**
 * This immutable class defines a rotation in which an entity may move.
 * 
 * @author Viktor Anderling
 * 
 */
public class Rotation {

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
	public Rotation(double angle) {
			this.angle = angle;
	}
	
	 /**
	  * @return The angle of the direction.
	  */
	public double getAngle() {
		return angle;
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
	
	@Override
	public boolean equals(Object o) {
		if(this == o) {
			return true;
		} else if(this.getClass() != o.getClass()) {
			return false;
		} else {
			return this.getAngle() == ((Rotation)o).getAngle();
		}
	}
}
