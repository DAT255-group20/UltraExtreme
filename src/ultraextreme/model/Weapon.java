package ultraextreme.model;


public class Weapon {

	BulletProductionQueue bulletProdqueue;
	
	public void fireShot()
	{
		/*
		 * Weapon skapar sin Bullet och skickar in PlayerID
		 * Weapon l�gger till Bulleten till BulletProdQueue
		 * GameModel antingen lyssnar p� BulletProdQueue eller fr�gar efter nya Bullets varje update
		 * F�r varje ny Bullet
			{
				Om Bullet �r m�ls�kande (instanceof HominBullet)
				{
					S�k reda p� n�rmsta m�l med hj�lp av den PlayerID som bulleten har (f�r att undvika att fiender skjuter p� fiender osv)
				}
				L�gg till Bullet i listan som inneh�ller alla Bullets
			}
		 */
	}
}