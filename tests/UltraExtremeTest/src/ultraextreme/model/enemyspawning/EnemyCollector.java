package ultraextreme.model.enemyspawning;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;

public class EnemyCollector implements PropertyChangeListener {
	
	private List<IEnemy> enemies;
	
	public EnemyCollector() {
		enemies = new ArrayList<IEnemy>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals(EnemySpawner.NEW_ENEMY)) {
			enemies.add((IEnemy) event.getNewValue());
		}
	}
	
	public List<IEnemy> getSpawnedEnemies() {
		return enemies;
	}

}
