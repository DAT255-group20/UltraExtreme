package ultraextreme.view;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.entity.IBullet;

public class BulletSprite extends Rectangle {

	/**
	 * Reference to a bullet in the model.
	 */
	private IBullet bullet;

	public BulletSprite(IBullet bullet,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super((float) bullet.getPosition().getX(), (float) bullet.getPosition()
				.getY(), bullet.getWidth(), bullet.getHeight(),
				pVertexBufferObjectManager);
		this.bullet = bullet;
	}
	
	/**
	 * Get the bullet that this sprite gets its data from.
	 * @return The bullet this sprite gets its data from.
	 */
	public IBullet getIBullet() {
		return bullet;
	}
	
	/**
	 * Update the bullet sprite with data from the model.
	 */
	public void update() {
		this.setX((float) bullet.getPosition().getX());
		this.setY((float) bullet.getPosition().getY());
	}
}