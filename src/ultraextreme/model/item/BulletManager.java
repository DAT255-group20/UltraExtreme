package ultraextreme.model.item;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.IBullet;

/**
 * Contains a queue of all bullets that are to be added to the game.
 * 
 * @author Bjorn Persson Mattsson
 * @author Daniel Jonsson
 * 
 */
public class BulletManager {

	private boolean isBombDropped = false;

	private List<IBullet> bullets;

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	


	public BulletManager() {
		bullets = new ArrayList<IBullet>();
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
		pcs.firePropertyChange("add", null, b);
	}
	public void removeBullet(int index) {
		pcs.firePropertyChange("remove", null, bullets.get(index));
		bullets.remove(index);
	}

	/**
	 * @return A list of all bullets in the bullet manager.
	 */
	public List<IBullet> getBullets() {
		return bullets;
	}

	/**
	 * Removes all bullets that are outside the gameScreen
	 */
	public void clearBulletsOffScreen() {
		for (int i = 0; i < bullets.size(); i++) {
			if ((bullets.get(i).isOutOfScreen())) {
				//TODO Change to fit reversed Y axis. (do tests)
				this.removeBullet(i);
				i--;
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
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}