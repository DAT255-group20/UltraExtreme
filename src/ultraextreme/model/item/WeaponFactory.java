package ultraextreme.model.item;

import java.util.HashMap;
import java.util.Map;

import ultraextreme.model.util.ObjectName;

/**
 * A factory class for weapons in charge of creating weapons
 * 
 * @author Johan Gronvall
 * 
 */
public class WeaponFactory {
	private Map<ObjectName, AbstractWeapon> weaponMap;

	private static WeaponFactory instance;

	private WeaponFactory(BulletManager manager) {
		weaponMap = new HashMap<ObjectName, AbstractWeapon>();
		weaponMap.put(ObjectName.BASIC_WEAPON, new BasicWeapon(manager));
		weaponMap.put(ObjectName.SPINNING_SPREAD_WEAPON,
				new SpinningSpreadWeapon(manager));
		weaponMap.put(ObjectName.BOMB, new Bomb(manager));
	}

	public static void initialize(BulletManager bulletManager) {
		instance = new WeaponFactory(bulletManager);
	}

	public static WeaponFactory getInstance() {
		if (instance == null) {
			throw new IllegalStateException(
					"WeaponFactory must have been initialized before it is used");
		}
		return instance;
	}

	public AbstractWeapon getNewWeapon(ObjectName objectName) {
		return weaponMap.get(objectName).shallowClone();
	}
}