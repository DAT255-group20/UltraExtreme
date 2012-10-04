package ultraextreme.model.util;

/**
 * This class defines constants in the model such as fixed dimensions etc.
 * 
 * @author Viktor Anderling
 * 
 */
public final class Constants {

	public static final String EVENT_ENEMY_KILLED = "enemyKilled";
	
	private Constants() {
	}

	/**
	 * @return The relative dimension of a arbitrary level.
	 */
	public static Dimension getLevelDimension() {
		return new Dimension(900, 1600);
	}

	/**
	 * returns the dropChance of a weaponPickup upon enemy death as a percentage
	 * 
	 * @return
	 */
	public static int getWeaponDropChance() {
		return 10;
	}

	/**
	 * @return A positive double that impacts how fast the enemy ships will move
	 */
	public static double getEnemySpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the player ships will
	 *         move
	 */
	public static double getPlayerSpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the upgrade items will
	 *         move
	 */
	public static double getPickupSpeedModifier() {
		return 1;
	}

	/**
	 * @return A positive double that impacts how fast the bullets will move
	 */
	public static double getBulletSpeedModifier() {
		return 300;
	}

	/**
	 * @return The time in seconds of which the will stay invincible after
	 *         receiving damage.
	 */
	public static double getShipInvincibilityTime() {
		return 3;
	}

	/**
	 * @return The number of lives the ship will start with.
	 */
	public static int getInitShipLives() {
		return 3;
	}
}
