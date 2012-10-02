package ultraextreme.model;

import java.util.List;

import ultraextreme.model.enemy.IEnemy;
import ultraextreme.model.entity.IBullet;

/**
 * An interface for a GameModel that only has get methods.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * @author Johan Gronvall
 * 
 */
public interface IUltraExtremeModel {

	// TODO Add javadoc
	IPlayer getPlayer();

	List<IBullet> getBullets();

	List<IEnemy> getEnemies();
	
	void registerPlayerListener(IPlayerListener listener);
	void unregisterPlayerListener(IPlayerListener listener);
}