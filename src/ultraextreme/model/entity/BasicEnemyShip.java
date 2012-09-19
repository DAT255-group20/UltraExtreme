package ultraextreme.model.entity;

public class BasicEnemyShip extends EnemyShip {

	private static final int side = 50;
	private static final int hitPoints = 5;
	
	public BasicEnemyShip(double x, double y) {
		super(x, y, side, side, hitPoints);
	}

}
