package ultraextreme.view;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.entity.AbstractBullet;

public class BulletSprite extends Rectangle {

	/**
	 * Reference to a bullet in the model.
	 */
	private AbstractBullet bullet;

	public BulletSprite(AbstractBullet bullet,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super((float) bullet.getPosition().getX(), (float) bullet.getPosition()
				.getY(), bullet.getWidth(), bullet.getHeight(),
				pVertexBufferObjectManager);
		this.bullet = bullet;
	}

	/**
	 * Update the bullet sprite with data from the model.
	 */
	public void update() {
		this.setX((float) bullet.getPosition().getX());
		this.setY((float) bullet.getPosition().getY());
	}
}