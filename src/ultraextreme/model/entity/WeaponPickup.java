package ultraextreme.model.entity;

import ultraextreme.model.util.Constants;

/**
 * An weapon pickup item.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class WeaponPickup extends AbstractEntity {

	private static double speedMod = Constants.getInstance().getPickupSpeedModifier();
	
	@Override
	public double getSpeedMod() {
		return speedMod;
	}

}
