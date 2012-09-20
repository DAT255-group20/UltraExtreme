package ultraextreme.model;

import java.util.List;

import ultraextreme.model.entity.AbstractBullet;

/**
 * An interface for a GameModel that only has get methods.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public interface IUltraExtremeModel {

	// TODO: Only for testing. perhaps
	public BulletManager getBulletManager();

	IPlayer getPlayer();

	List<AbstractBullet> getBullets();
}