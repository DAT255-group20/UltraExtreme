package ultraextreme.model.item;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.entity.AbstractBullet;

/**
 * Contains a queue of all bullets that are to be added to the game.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class BulletManager {

	private boolean isBombDropped = false;

	private List<AbstractBullet> bullets;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public BulletManager() {
		bullets = new ArrayList<AbstractBullet>();
	}

	/**
	 * Adds a bullet to the creation queue.
	 * 
	 * @param b
	 *            Bullet to be added.
	 */
	public void addBullet(AbstractBullet b) {
		bullets.add(b);
		// TODO Not entirely sure that using a raw string is so good /Plankton
		pcs.firePropertyChange("newBullet", null, b);
	}

	/**
	 * @return A list of all bullets in the bullet manager.
	 */
	public List<AbstractBullet> getBullets() {
		return bullets;
	}

	/**
	 * Removes all bullets that are outside the gameScreen
	 */
	public void clearBulletsOffScreen() {
		// OBS: Using a for each loop would result in an error
		for (int i = 0; i < bullets.size(); i++) {
			if ((bullets.get(i).isOutOfScreen())) {
				bullets.remove(i);
			}
		}
	}

	/**
	 * Clear the list of bullets.
	 */
	public void clear() {
		bullets.clear();
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
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}
}