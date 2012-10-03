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
public class PlayerShip extends AbstractDestroyableEntity {

	private static double speedMod = Constants.getInstance()
			.getPlayerSpeedModifier();

	/**
	 * If the ship is hit this update.
	 */
	private boolean justHit;

	/**
	 * Wether or not the ship is destroyed.
	 */
	private boolean destroyed;

	public PlayerShip(final double x, final double y, final int width,
			final int height) {
		super(x, y, width, height, new Rotation(0), ObjectName.PLAYERSHIP);
	}

	public PlayerShip(final double x, double y) {
		this(x, y, 50, 50);
	}

	public PlayerShip() {
		this(0, 0);
	}

	/**
	 * Checks so that the ship wont move out of the level along the x-axis.
	 * 
	 * @param deltaX
	 *            The given movement along the x-axis.
	 * 
	 * @return True if movement with the given distance wont take the ship out
	 *         of the level, else false.
	 */
	public boolean canMoveX(double deltaX) {
		final Dimension dimension = Constants.getInstance().getLevelDimension();
		final Position position = this.getCenteredPosition();
		final double newX = deltaX + position.getX();
		return newX + getWidth() / 2 < dimension.getX()
				&& newX - getWidth() / 2 > 0;
	}

	/**
	 * Checks so that the ship wont move out of the level along the y-axis.
	 * 
	 * @param deltaX
	 *            The given movement along the y-axis.
	 * 
	 * @return True if movement with the given distance wont take the ship out
	 *         of the level, else false.
	 */
	public boolean canMoveY(double deltaY) {
		final Dimension dimension = Constants.getInstance().getLevelDimension();
		final Position position = this.getCenteredPosition();
		double newY = deltaY + position.getY();
		return newY + getHeight() / 2 < dimension.getY()
				&& newY - getHeight() / 2 > 0;
	}

	@Override
	public void move(double x, double y) {
		justHit = false;
		super.move(x, y);
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed() {
		destroyed = true;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}

	/**
	 * Returns true if the ship got hit this update, else false.
	 * 
	 * @return True if the ship got hit this update, else false.
	 */
	public boolean justGotHit() {
		return justHit;
	}

	@Override
	public void receiveDamage(int damage) {
		justHit = true;
	}
}
