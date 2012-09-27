package ultraextreme.model.enemyspawning.wave;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemy.IEnemy;

public class EnemyCollector implements WaveListener {
	
	private boolean hasWaveEnded = false;
	
	private List<IEnemy> enemies = new ArrayList<IEnemy>();

	@Override
	public void waveEnded(Wave wave) {
		this.hasWaveEnded = true;
	}
	
	public boolean hasWaveEnded() {
		return hasWaveEnded;
	}

	@Override
	public void enemySpawned(IEnemy enemy) {
		enemies.add(enemy);
	}
	
	public List<IEnemy> getSpawnedEnemies() {
		return this.enemies;
	}

}
