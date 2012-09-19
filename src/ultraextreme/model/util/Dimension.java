package ultraextreme.model.util;

/**
 * Defines an immutable positive dimension with an
 * x-value and an y-value with decimals.
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
	 * 		A positive double.
	 * @param y
	 * 		A positive double.
	 */
	public Dimension(double x, double y) {
		if(x < 0 || y < 0) {
			throw new IllegalArgumentException("Negative input value");
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
}
