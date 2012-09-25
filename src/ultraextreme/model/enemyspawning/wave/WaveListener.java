package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.IEnemy;

public interface WaveListener {

	public void waveEnded(Wave wave);
	
	public void enemySpawned(IEnemy enemy);
	
}
