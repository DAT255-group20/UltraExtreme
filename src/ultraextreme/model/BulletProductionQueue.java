package ultraextreme.model;

import java.util.List;

import ultraextreme.model.entity.AbstractBullet;

/**
 * Contains a queue of all bullets that are to be added to the game.
 * @author Bjorn Persson Mattsson
 *
 */
public class BulletProductionQueue {

	private boolean isBombDropped = false;
	
	/**
	 * Adds a bullet to the creation queue.
	 * @param b Bullet to be added.
	 */
	public void addBullet(AbstractBullet b)
	{
	}
	
	/**
	 * @return A list of all bullets that have been added since last call to this method.
	 */
	public List<AbstractBullet> getNewBullets()
	{
		return null;
	}
	
	/**
	 * Informs the class that a bomb has been dropped.
	 */
	public void dropBomb()
	{
		this.isBombDropped = true;
	}
	
	/**
	 * @return true if and only if a bomb has been dropped since last call to this method.
	 */
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