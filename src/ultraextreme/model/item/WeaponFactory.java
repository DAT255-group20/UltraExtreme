package ultraextreme.model.item;

import java.util.HashMap;
import java.util.Map;

import ultraextreme.model.util.ObjectName;
/**
 * 
 * @author Johan Gronvall
 * A factoryclass for weapons
 * in charge of creating weapons 
 */
public class WeaponFactory {
	private Map<ObjectName, AbstractWeapon> weaponMap;
	
	public WeaponFactory(BulletManager manager) {
		weaponMap = new HashMap<ObjectName, AbstractWeapon>();
		weaponMap.put(ObjectName.BASIC_WEAPON, new BasicWeapon(manager));
		weaponMap.put(ObjectName.SPINNING_SPREAD_WEAPON,
				new SpinningSpreadWeapon(manager));
		weaponMap.put(ObjectName.BOMB, new Bomb(manager));
	}
	
	public AbstractWeapon getNewWeapon(ObjectName objectName) {
		return weaponMap.get(objectName).shallowClone();
	}
}
