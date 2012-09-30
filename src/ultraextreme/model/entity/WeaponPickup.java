package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * A pickupable weapon or bomb. What kind of weapon this pickup represents is
 * saved as its ObjectName
 * 
 * @author Johan Gronvall
 * 
 */
public class WeaponPickup extends AbstractEntity {
	private static final int width = 10;

	private static final double speedMod = Constants.getInstance()
			.getPickupSpeedModifier();

	public WeaponPickup(double x, double y, ObjectName weaponType) {
		super(x, y, width, width, new Rotation(0), weaponType);
	}

	public WeaponPickup(Position position, ObjectName name) {
		super(position, width, width, name);
	}

	@Override
	public double getSpeedMod() {
		return speedMod;
	}
}