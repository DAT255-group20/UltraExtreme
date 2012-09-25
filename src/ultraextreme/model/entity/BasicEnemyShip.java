package ultraextreme.model.entity;

import ultraextreme.model.util.Rotation;

public class BasicEnemyShip extends EnemyShip {

	private static final int side = 50;
	private static final int hitPoints = 5;

	public BasicEnemyShip(double x, double y, Rotation direction) {
		super(x, y, side, side, direction, hitPoints);
	}

}
