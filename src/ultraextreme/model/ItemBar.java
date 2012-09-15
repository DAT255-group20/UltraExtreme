package ultraextreme.model;

import ultraextreme.model.util.PlayerID;

/**
 * An inventory containing weapons and bombs.
 * @author Bjorn Persson Mattsson
 *
 */
public class ItemBar {

	Weapon weapon;
	Bomb bomb;
	PlayerID playerID;
	BulletProductionQueue bulletManager;
}
