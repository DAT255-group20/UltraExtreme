package ultraextreme.model;

import java.util.List;

import ultraextreme.model.entity.AbstractBullet;


public interface IModel {
	public List<AbstractBullet> getBullets();
	
	public List<IPlayer> getPlayers();
}
