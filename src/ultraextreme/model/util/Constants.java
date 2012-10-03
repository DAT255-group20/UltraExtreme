package ultraextreme.model.util;

/**
 * This class defines constants in the model such as fixed dimensions etc.
 * 
 * @author Viktor Anderling
 * 
 */
public class Constants {
	// TODO PMD: A class with only private constructors should be final, unless
	// the private constructor is called by a inner class.

	private static Constants INSTANCE;

	// TODO PMD: Variables should start with a lowercase character

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
	 * returns the dropChance of a weaponPickup upon enemy death as a percentage
	 * 
	 * @return
	 */
	public int getWeaponDropChance() {
		return 10;
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
		return 200;
	}
	
	/**
	 * @return The time in seconds of which the will stay invincible after 
	 * receiving damage.
	 */
	public double getShipInvincibilityTime() {
		return 3;
	}
	
	/**
	 * @return The number of lives the ship will start with.
	 */
	public int getInitShipLives() {
		return 3;
	}
}
