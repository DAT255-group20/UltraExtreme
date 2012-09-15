package ultraextreme.model.util;

import javax.vecmath.Vector2d;

/**
 * A 2d position that is used in the model.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Position {

	private Vector2d coordinates;

	// TODO Skriv konstruktorer
	public double getX() {
		return coordinates.x;
	}

	public double getY() {
		return coordinates.y;
	}
}