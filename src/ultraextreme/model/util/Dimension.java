package ultraextreme.model.util;

import javax.vecmath.Vector2d;

/**
 * Defines an immutable positive dimension with an x-value and an y-value with
 * decimals.
 * 
 * @author Viktor Anderling
 * 
 */
public class Dimension {

	private double xValue;
	private double yValue;

	/**
	 * @precon Both x and y must be positive doubles.
	 * 
	 * @param x
	 *            A positive double.
	 * @param y
	 *            A positive double.
	 */
	public Dimension(final double x, final double y) {
		if (x <= 0 || y <= 0) {
			throw new IllegalArgumentException("Non-positive input value");
		} else {
			this.xValue = x;
			this.yValue = y;
		}
	}

	/**
	 * @return The length of the dimension along the x-axis
	 */
	public double getX() {
		return xValue;
	}

	/**
	 * @return The length of the dimension along the y-axis
	 */
	public double getY() {
		return yValue;
	}

	/**
	 * Calculates the quotient between this and the other dimension
	 * 
	 * @param otherDimension
	 * @return A vector with an x-quotient and an y-quotient.
	 */
	public Vector2d getQuotient(final Dimension otherDimension) {
		return new Vector2d(this.getX() / otherDimension.getX(), this.getY()
				/ otherDimension.getY());
	}

	/**
	 * Scales the given position in the given dimension to a position of this
	 * dimension.
	 * 
	 * @param otherDimension
	 *            The dimension of the other position.
	 * @param otherPosition
	 *            The other position that will be scaled.
	 * @return A position scaled from the other position.
	 */
	public Position scalePosition(final Dimension otherDimension,
			final Position otherPosition) {
		if (this.equals(otherDimension)) {
			return otherPosition;
		} else {
			final Vector2d quotientVector = this.getQuotient(otherDimension);
			return new Position(quotientVector.x * otherPosition.getX(),
					quotientVector.y * otherPosition.getY());
		}
	}

	@Override
	public boolean equals(Object o) {
		// TODO PMD: Ensure you override both equals() and hashCode()
		// TODO FindBugs: This class overrides equals(Object),
		// but does not override hashCode(), and inherits the implementation of
		// hashCode() from java.lang.Object (which returns the identity hash
		// code,
		// an arbitrary value assigned to the object by the VM).
		// Therefore, the class is very likely to violate the invariant
		// that equal objects must have equal hashcodes.
		if (this == o) {
			return true;
		} else if (this.getClass() != o.getClass()) {
			// TODO PMD: Avoid if (x != y) ..; else ..;
			return false;
		} else {
			return this.getX() == ((Dimension) o).getX()
					&& this.getY() == ((Dimension) o).getY();
		}
	}
}
