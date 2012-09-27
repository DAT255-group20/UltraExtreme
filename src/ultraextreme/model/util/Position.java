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

	public Position(Position position) {
		this(position.getX(), position.getY());
	}

	public Position(double x, double y) {
		coordinates = new Vector2d(x, y);
	}

	public void setX(double x) {
		coordinates.x = x;
	}

	public void setY(double y) {
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
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof Position))
			return false;
		Position oPosition = (Position) o;
		return coordinates.equals(oPosition.coordinates);
	}
}