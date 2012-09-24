package ultraextreme.model.item;

import ultraextreme.model.entity.IBullet;

public interface BulletManagerListener {

	public void bulletAdded(IBullet bullet);
	
	public void bulletRemoved(IBullet bullet);
}
