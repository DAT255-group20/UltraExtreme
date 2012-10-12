package ultraextreme.model.enemyspawning.wave;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

/**
 * This class is used by RandomWaveList and RectangleWave so there can be
 * rectangles with different enemies.
 * 
 * @author Daniel Jonsson
 * 
 */
public abstract class EnemyProvider {

	/**
	 * Build an enemy.
	 * 
	 * @param spawningPosition
	 *            The enemy ship's spawning position.
	 * @param rotation
	 *            Rotation of the enemy ship.
	 * @return AbstractEnemy
	 */
	public abstract AbstractEnemy getEnemy(Position spawningPosition,
			Rotation rotation);
}
