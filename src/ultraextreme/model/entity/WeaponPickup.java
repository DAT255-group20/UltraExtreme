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
	private AbstractWeapon weapon;
	private static final int width = 10;

	private static final double speedMod = Constants.getInstance()
			.getPickupSpeedModifier();

	public WeaponPickup(double x, double y, AbstractWeapon weapon) {
		super(x, y, width, width, new Rotation(0), weapon.getName());
		this.weapon = weapon;
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
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
