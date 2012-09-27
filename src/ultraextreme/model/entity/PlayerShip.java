package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Rotation;


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
		super(x, y, width, height, new Rotation(0), ObjectName.PLAYERSHIP);
	}

	public PlayerShip() {
		super(0, 0, 50, 50, new Rotation(0), ObjectName.PLAYERSHIP);
	}

	@Override
	public boolean isDestroyed() {
		// Auto-generated method stub
		return false;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}
}