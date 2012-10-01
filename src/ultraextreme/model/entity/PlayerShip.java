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
	 * The time the ship will be invincible after receiving damage.
	 */
	private static double invTime = Constants.getInstance()
			.getShipInvincibilityTime();
	
	private int lives;
	
	/**
	 * A count down for the ships invincibility.
	 */
	private double invCountDown;

	public PlayerShip(final double x, final double y, final int width,
			final int height) {
		super(x, y, width, height, new Rotation(0), ObjectName.PLAYERSHIP);
		lives = 3;
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
	public boolean isDestroyed() {
		return lives <= 0;
	}
	
	public boolean isInvincible() {
		return invCountDown > 0;
	}
	
	public boolean justGotInvincible() {
		return invCountDown == invTime;
	}
	
	public void countDownInvincibility(double timeElapsed) {
		invCountDown = invCountDown - timeElapsed;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}

	@Override
	public void receiveDamage(int damage) {
		if(invCountDown <= 0) {
			lives = lives - damage;
			invCountDown = invTime;
		}
	}
}
