package ultraextreme.model.enemyspawning.wave;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemy.IEnemy;

/**
 * This is only used in some Wave tests since a listener is needed.
 * 
 * @author Daniel Jonsson
 * 
 */
public class EnemyCollector implements WaveListener {

	private boolean hasWaveEnded = false;

	private List<IEnemy> enemies = new ArrayList<IEnemy>();

	@Override
	public void waveEnded(AbstractWave wave) {
		this.hasWaveEnded = true;
	}

	public boolean hasWaveEnded() {
		return hasWaveEnded;
	}

	@Override
	public void enemySpawned(AbstractEnemy enemy) {
		enemies.add(enemy);
	}

	public List<IEnemy> getSpawnedEnemies() {
		return this.enemies;
	}

}
