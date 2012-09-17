package ultraextreme.model;

import java.util.List;

import ultraextreme.model.entity.IBullet;

/**
 * An interface for a GameModel that only has get methods.
 * @author Bjorn Persson Mattsson
 *
 */
public interface IUltraExtremeModel {

	IPlayer getPlayer();
	List<IBullet> getBullets();
}