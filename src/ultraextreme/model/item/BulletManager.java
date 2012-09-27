package ultraextreme.model.item;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.util.PlayerID;

/**
 * Contains all bullets in the game.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * 
 */
public class BulletManager {

	private boolean isBombDropped = false;

	private Map<PlayerID, List<IBullet>> bulletsMap = new HashMap<PlayerID, List<IBullet>>();

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	


	public BulletManager() {
		for (PlayerID pID : PlayerID.values())
		{
			bulletsMap.put(pID, new ArrayList<IBullet>());
		}
	}

	/**
	 * Adds a bullet to the creation queue.
	 * 
	 * @param b
	 *            Bullet to be added.
	 */
	public void addBullet(AbstractBullet b) {
		bulletsMap.get(b.getPlayerId()).add(b);
		
		// TODO Not entirely sure that using a raw string is so good /Plankton
		pcs.firePropertyChange("add", null, b);
	}

	/**
	 * @return A list of all bullets in the bullet manager.
	 */
	public List<IBullet> getBullets() {
		List<IBullet> output = new ArrayList<IBullet>();
		for (List<IBullet> list : bulletsMap.values())
		{
			output.addAll(list);
		}
		return output;
	}
	
	/**
	 * Returns a list of all bullets shot by the provided player.
	 * @param player The shooter of the bullets.
	 * @return A list of all bullets shot by the provided player
	 */
	public List<IBullet> getBulletsFrom(PlayerID player)
	{
		// TODO Should not return the reference
		return bulletsMap.get(player);
	}

	/**
	 * Removes all bullets that are outside the gameScreen
	 */
	public void clearBulletsOffScreen() {
		for (List<IBullet> list : bulletsMap.values())
		{
			for (int i = 0; i < list.size(); i++) {
				if ((list.get(i).isOutOfScreen())) {
					//TODO Change to fit reversed Y axis. (do tests)
					pcs.firePropertyChange("remove", null, list.get(i));
					list.remove(i);
					i--;
				}
			}
		}
	}

	/**
	 * Clears the bullet manager of all bullets.
	 */
	public void clearAllBullets() {
		for (List<IBullet> list : bulletsMap.values())
		{
			list.clear();
		}
	}
	
	/**
	 * Clears the bullet manager of all bullets from the player.
	 * @param player The owner of the bullets
	 */
	public void clearAllBulletsFrom(PlayerID player)
	{
		bulletsMap.get(player).clear();
	}

	/**
	 * Informs the class that a bomb has been dropped.
	 */
	public void dropBomb() {
		this.isBombDropped = true;
	}

	/**
	 * @return true if and only if a bomb has been dropped since last call to
	 *         this method.
	 */
	public boolean isBombDropped() {
		boolean output = this.isBombDropped;

		if (output) {
			this.isBombDropped = false;
		}
		return output;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}