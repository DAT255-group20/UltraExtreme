package ultraextreme.view;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.entity.EnemyShip;

public class EnemySprite extends Rectangle {

	/**
	 * Reference to a enemy in the model.
	 */
	private EnemyShip enemyShip;

	public EnemySprite(EnemyShip enemyShip,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super((float) enemyShip.getPosition().getX(), (float) enemyShip
				.getPosition().getY(), enemyShip.getWidth(), enemyShip
				.getHeight(), pVertexBufferObjectManager);
		this.enemyShip = enemyShip;
	}

	/**
	 * Update the enemy sprite with data from the model.
	 */
	public void update() {
		this.setX((float) enemyShip.getPosition().getX());
		this.setY((float) enemyShip.getPosition().getY());
	}
}
