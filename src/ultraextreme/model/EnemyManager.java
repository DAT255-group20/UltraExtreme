package ultraextreme.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;

public class EnemyManager {
	
	public static final String NEW_ENEMY = "n";

	private List<IEnemy> enemies;
	
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	
	public EnemyManager() {
		enemies = new ArrayList<IEnemy>();
	}
	
	public List<IEnemy> getEnemies() {
		return enemies;
	}
	
	public void addEnemy(IEnemy enemy) {
		enemies.add(enemy);
		pcs.firePropertyChange(EnemyManager.NEW_ENEMY, null, enemy);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}
}
