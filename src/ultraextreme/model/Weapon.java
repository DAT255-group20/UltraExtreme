package ultraextreme.model;


public class Weapon {

	BulletProductionQueue bulletProdqueue;
	
	public void fireShot()
	{
		/*
		 * Weapon skapar sin Bullet och skickar in PlayerID
		 * Weapon lägger till Bulleten till BulletProdQueue
		 * GameModel antingen lyssnar på BulletProdQueue eller frågar efter nya Bullets varje update
		 * För varje ny Bullet
			{
				Om Bullet är målsökande (instanceof HominBullet)
				{
					Sök reda på närmsta mål med hjälp av den PlayerID som bulleten har (för att undvika att fiender skjuter på fiender osv)
				}
				Lägg till Bullet i listan som innehåller alla Bullets
			}
		 */
	}
}