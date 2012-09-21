package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Direction;

/**
 * The player's ship.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class PlayerShip extends DestroyableEntity {

	private static double speedMod = Constants.getInstance()
			.getPlayerSpeedModifier();

	public PlayerShip(double x, double y, int width, int height) {
		super(x, y, width, height, Direction.UP);
		// TODO Auto-generated constructor stub
	}

	public PlayerShip() {
		super(0, 0, 50, 50, Direction.UP);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}

}
