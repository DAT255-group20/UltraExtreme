package ultraextreme.model.util;

/**
 * This class defines constants in the model such as fixed dimensions etc.
 * 
 * @author Viktor Anderling
 * 
 */
public class Constants {

	private static Constants INSTANCE;

	private Constants() {
		INSTANCE = this;
	}

	/**
	 * @return This instance.
	 */
	public static Constants getInstance() {
		if (INSTANCE == null) {
			return new Constants();
		} else {
			return INSTANCE;
		}
	}

	/**
	 * @return The relative dimension of a arbitrary level.
	 */
	public Dimension getLevelDimension() {
		return new Dimension(900, 1600);
	}

	/**
	 * @return A positive double that impacts how fast the enemy ships will move
	 */
	public double getEnemySpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the player ships will
	 *         move
	 */
	public double getPlayerSpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the upgrade items will
	 *         move
	 */
	public double getPickupSpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the bullets will move
	 */
	public double getBulletSpeedModifier() {
		return 1;
	}
}
