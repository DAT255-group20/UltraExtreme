package ultraextreme.model.util;

import javax.vecmath.Vector2d;

/**
 * A 2d position that is used in the model.
 * 
 * @author Bjorn Persson Mattsson, Viktor Anderling
 * 
 */
public class Position {

	private Vector2d coordinates;

	public Position() {
		this(0, 0);
	}

	public Position(final Position position) {
		this(position.getX(), position.getY());
	}

	public Position(final double x, final double y) {
		coordinates = new Vector2d(x, y);
	}

	public void setX(final double x) {
		coordinates.x = x;
	}

	public void setY(final double y) {
		coordinates.y = y;
	}

	public void setPosition(Position position) {
		coordinates.x = position.getX();
		coordinates.y = position.getY();
	}

	public double getX() {
		return coordinates.x;
	}

	public double getY() {
		return coordinates.y;
	}

	@Override
	public boolean equals(Object o) {
		// TODO PMD: Ensure you override both equals() and hashCode()
		// TODO FindBugs: This class overrides equals(Object),
		// but does not override hashCode(), and inherits the implementation of
		// hashCode() from java.lang.Object (which returns the identity hash code,
		// an arbitrary value assigned to the object by the VM).
		// Therefore, the class is very likely to violate the invariant
		// that equal objects must have equal hashcodes.
		
		// TODO PMD: Avoid using if statements without curly braces
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Position))
			return false;
		final Position oPosition = (Position) o;
		return coordinates.equals(oPosition.coordinates);
	}
}