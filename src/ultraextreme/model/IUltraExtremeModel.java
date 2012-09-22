package ultraextreme.model;

import java.util.List;

import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.player.IPlayer;

/**
 * An interface for a GameModel that only has get methods.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IUltraExtremeModel {

	IPlayer getPlayer();
	List<AbstractBullet> getBullets();
	List<IEnemy> getEnemies();
}