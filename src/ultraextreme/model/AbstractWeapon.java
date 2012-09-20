package ultraextreme.model;

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

	private BulletManager bulletManager;
	
	public AbstractWeapon(BulletManager bulletManager) {
		this.bulletManager = bulletManager;
	}
	
	/*
	 * Weapon skapar sin Bullet och skickar in PlayerID Weapon l�gger till
	 * Bulleten till BulletProdQueue GameModel antingen lyssnar p�
	 * BulletProdQueue eller fr�gar efter nya Bullets varje update F�r varje
	 * ny Bullet { Om Bullet �r m�ls�kande (instanceof HominBullet) { S�k
	 * reda p� n�rmsta m�l med hj�lp av den PlayerID som bulleten har (f�r
	 * att undvika att fiender skjuter p� fiender osv) } L�gg till Bullet i
	 * listan som inneh�ller alla Bullets }
	 */
	//bulletProdqueue.addBullet(new BasicBullet(shipPosition.getX(), shipPosition.getY(), width, height, playerId));
	public abstract void fireShot(Position shipPosition, PlayerID playerId, Direction direction);

	protected BulletManager getBulletManager() {
		return bulletManager;
	}

}