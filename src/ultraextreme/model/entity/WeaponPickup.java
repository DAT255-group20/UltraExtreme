package ultraextreme.model.entity;

import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Rotation;

/**
 * A pickupable weapon or bomb.
 * 
 * @author Johan Gronvall
 * 
 */
public class WeaponPickup extends AbstractEntity {
	private final AbstractWeapon weapon;
	private static final int WIDTH = 10;

	private static final double SPEED_MOD = Constants.getInstance()
			.getPickupSpeedModifier();

	public WeaponPickup(final double x, final double y,
			final AbstractWeapon weapon) {
		super(x, y, WIDTH, WIDTH, new Rotation(0), weapon.getName());
		this.weapon = weapon;
	}

	@Override
	public double getSpeedMod() {
		return SPEED_MOD;
	}

	/**
	 * returns the weapon this entity is representing as an item
	 * 
	 * @return the weapon this entity is representing as an item
	 */
	public AbstractWeapon getItem() {
		return weapon;
	}

}
