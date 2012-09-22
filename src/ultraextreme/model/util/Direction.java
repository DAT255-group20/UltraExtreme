package ultraextreme.model.util;

/**
 * This enumerator defines a direction in which an entity may move.
 * 
 * @author Viktor Anderling
 * 
 */
public enum Direction {
	UP(1, 1, 0, 0), DOWN(-1, -1, 0, 0), LEFT(0, 0, -1, 1), RIGHT(0, 0, 1, -1);

	/**
	 * A matrix stored with axis modifiers.
	 */
	private double[][] modifierArr;

	/**
	 * For example, if the x-value is to become the y-value, the xToYMod will be
	 * 1. Or if x value is to be negated, xToXMod will be -1.
	 * 
	 * @param xToXMod
	 *            The way the x-value will effect the x-value.
	 * @param yToYMod
	 *            The way the y-value will effect the y-value.
	 * @param xToYMod
	 *            The way the x-value will effect the y-value.
	 * @param yToXMod
	 *            The way the y-value will effect the x-value.
	 */
	private Direction(double xToXMod, double yToYMod, double xToYMod,
			double yToXMod) {
		this.modifierArr = new double[2][2];
		modifierArr[0][0] = xToXMod;
		modifierArr[0][1] = -yToYMod;
		modifierArr[1][0] = xToYMod;
		modifierArr[1][1] = yToXMod;
	}

	/**
	 * @return A matrix stored with axis modifiers.
	 */
	public double[][] getMatrixMod() {
		return modifierArr;
	}
}
