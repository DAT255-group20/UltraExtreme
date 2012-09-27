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
 * @author Viktor Anderling
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
		super(0, 0, 50, 50, new Rotation(0), ObjectName.PLAYERSHIP);
	}
	
	/**
	 * Checks so that the ship wont move out of the level along the x-axis.
	 * 
	 * @param deltaX
	 * 				The given movement along the x-axis.
	 * 
	 * @return True if movement with the given distance wont take the ship 
	 * out of the level, else false.
	 */
	public boolean canMoveX(double deltaX) {
		Dimension dimension = Constants.getInstance().getLevelDimension();
		Position position = this.getCenteredPosition();
		double newX = deltaX + position.getX();
		return newX + getWidth() / 2 < dimension.getX() && newX - getWidth() / 2 > 0;
	}
	
	/**
	 * Checks so that the ship wont move out of the level along the y-axis.
	 * 
	 * @param deltaX
	 * 				The given movement along the y-axis.
	 * 
	 * @return True if movement with the given distance wont take the ship 
	 * out of the level, else false.
	 */
	public boolean canMoveY(double deltaY) {
		Dimension dimension = Constants.getInstance().getLevelDimension();
		Position position = this.getCenteredPosition();
		double newY = deltaY + position.getY();
		return newY + getHeight() / 2 < dimension.getY() && newY - getHeight() / 2 > 0;
	}
	
	public boolean canMove(double deltaX, double deltaY) {
		Dimension dimension = Constants.getInstance().getLevelDimension();
		Position position = this.getCenteredPosition();
		return dimension.getX() - (deltaX + position.getX() + getWidth() / 2) > 0 
				&& dimension.getY() - (deltaY + position.getY() + getHeight() / 2) > 0;
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

	@Override
	public void receiveDamage(int damage) {
		// TODO Auto-generated method stub
	}
}
