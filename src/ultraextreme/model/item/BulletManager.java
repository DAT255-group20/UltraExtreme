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
 * @author Bjorn Persson Mattsson, Daniel Jonsson
 * 
 */
public class BulletManager {

	private boolean bombIsDropped = false;

	private final Map<PlayerID, List<IBullet>> bulletsMap = new HashMap<PlayerID, List<IBullet>>();

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Initializes an empty bullet manager.
	 */
	public BulletManager() {
		for (PlayerID pID : PlayerID.values()) {
			bulletsMap.put(pID, new ArrayList<IBullet>());
		}
	}

	/**
	 * Adds a bullet to the bullet manager.
	 * 
	 * @param b
	 *            Bullet to be added.
	 */
	public void addBullet(final AbstractBullet b) {
		bulletsMap.get(b.getPlayerId()).add(b);
		// TODO Not entirely sure that using a raw string is so good /Plankton
		pcs.firePropertyChange("add", null, b);
	}

	/**
	 * @return A list of all bullets in the bullet manager.
	 */
	public List<IBullet> getBullets() {
		final List<IBullet> output = new ArrayList<IBullet>();
		for (List<IBullet> list : bulletsMap.values()) {
			output.addAll(list);
		}
		return output;
	}

	/**
	 * Returns a list of all bullets shot by the provided player.
	 * 
	 * @param player
	 *            The shooter of the bullets.
	 * @return A list of all bullets shot by the provided player
	 */
	public List<IBullet> getBulletsFrom(final PlayerID player) {
		final List<IBullet> output = new ArrayList<IBullet>();
		output.addAll(bulletsMap.get(player));
		return output;
	}

	/**
	 * Removes all bullets that are off the gameScreen
	 */
	public void clearBulletsOffScreen() {
		for (List<IBullet> list : bulletsMap.values()) {
			for (int i = 0; i < list.size(); i++) {
				final IBullet b = list.get(i);
				if (b.isMarkedForRemoval() || (b.isOutOfScreen(100))) {
					pcs.firePropertyChange("remove", null, b);
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
		for (List<IBullet> list : bulletsMap.values()) {
			list.clear();
		}
	}

	/**
	 * Clears the bullet manager of all bullets from the player.
	 * 
	 * @param player
	 *            The owner of the bullets
	 */
	public void clearAllBulletsFrom(final PlayerID player) {
		bulletsMap.get(player).clear();
	}

	/**
	 * Informs the bullet manager that a bomb has been dropped.
	 */
	public void dropBomb() {
		this.bombIsDropped = true;
	}

	/**
	 * @return true if and only if a bomb has been dropped since last call to
	 *         this method.
	 */
	public boolean isBombDropped() {
		final boolean output = this.bombIsDropped;

		if (output) {
			this.bombIsDropped = false;
		}
		return output;
	}

	/**
	 * Adds a property change listener.
	 * 
	 * @param listener
	 *            The listener.
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	/**
	 * Removes the property change listner.
	 * 
	 * @param listener
	 *            The listener.
	 */
	public void removeListener(final PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}