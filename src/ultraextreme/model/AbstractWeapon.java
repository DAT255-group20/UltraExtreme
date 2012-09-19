package ultraextreme.model;

import ultraextreme.model.entity.BasicBullet;
import ultraextreme.model.util.Direction;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;

/**
 * An ingame weapon.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public abstract class AbstractWeapon {

	private BulletProductionQueue bulletProdQueue;
	
	/*
	 * Weapon skapar sin Bullet och skickar in PlayerID Weapon lägger till
	 * Bulleten till BulletProdQueue GameModel antingen lyssnar på
	 * BulletProdQueue eller frågar efter nya Bullets varje update För varje
	 * ny Bullet { Om Bullet är målsökande (instanceof HominBullet) { Sök
	 * reda på närmsta mål med hjälp av den PlayerID som bulleten har (för
	 * att undvika att fiender skjuter på fiender osv) } Lägg till Bullet i
	 * listan som innehåller alla Bullets }
	 */
	//bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(), shipPosition.getY(), width, height, playerId));
	public abstract void fireShot(Position shipPosition, PlayerID playerId, Direction direction);


	
	protected BulletProductionQueue getBulletManager() {
		return bulletProdQueue;
	}

}