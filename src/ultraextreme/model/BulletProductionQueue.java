package ultraextreme.model;

import java.util.List;

import ultraextreme.model.entity.AbstractBullet;

public class BulletProductionQueue {

	private boolean isBombDropped = false;
	
	public void addBullet(AbstractBullet b)
	{
	}
	
	public List<AbstractBullet> getNewBullets()
	{
		return null;
	}
	
	public void dropBomb()
	{
		this.isBombDropped = true;
	}
	
	public boolean isBombDropped()
	{
		boolean output = this.isBombDropped;
		
		if (output)
		{
			this.isBombDropped = false;
		}
		return output;
	}
}