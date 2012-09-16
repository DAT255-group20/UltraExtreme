package ultraextreme.model;

import ultraextreme.model.entity.Bullet;
import ultraextreme.model.util.PlayerID;

/**
 * An ingame weapon.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class Weapon {

	BulletProductionQueue bulletProdqueue;

	public void fireShot(PlayerID playerId) {
		/*
		 * Weapon skapar sin Bullet och skickar in PlayerID Weapon lägger till
		 * Bulleten till BulletProdQueue GameModel antingen lyssnar på
		 * BulletProdQueue eller frågar efter nya Bullets varje update För varje
		 * ny Bullet { Om Bullet är målsökande (instanceof HominBullet) { Sök
		 * reda på närmsta mål med hjälp av den PlayerID som bulleten har (för
		 * att undvika att fiender skjuter på fiender osv) } Lägg till Bullet i
		 * listan som innehåller alla Bullets }
		 */
		bulletProdqueue.addBullet(new Bullet(playerId));
	}
}