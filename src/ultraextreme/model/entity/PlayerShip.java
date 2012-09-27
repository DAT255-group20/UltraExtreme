package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
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

	public PlayerShip(double x, double y) {
		this(x, y, 50, 50);
	}
	
	public PlayerShip() {
		this(0, 0);
	}
	
	public boolean canMove(double deltaX, double deltaY) {
		Dimension dimension = Constants.getInstance().getLevelDimension();
		Position position = this.getCenteredPosition();
		return dimension.getX() - (deltaX + position.getX() + getWidth() / 2) > 0 
				&& dimension.getY() - (deltaY + position.getY() + getHeight() / 2) > 0;
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
