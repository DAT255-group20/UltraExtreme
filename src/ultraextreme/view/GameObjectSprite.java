package ultraextreme.view;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.entity.AbstractEntity;

public class GameObjectSprite extends Sprite {

	/**
	 * Reference to a bullet in the model.
	 */
	private AbstractEntity entity;

	public GameObjectSprite(AbstractEntity entity,
			VertexBufferObjectManager pVertexBufferObjectManager,
			ITextureRegion texture) {

		super((float) entity.getPosition().getX(), (float) entity.getPosition()
				.getY(), entity.getWidth(), entity.getHeight(), texture,
				pVertexBufferObjectManager);
		this.entity = entity;
	}

	/**
	 * Update the bullet sprite with data from the model.
	 */
	public void update() {
		this.setX((float) entity.getPosition().getX());
		this.setY((float) entity.getPosition().getY());
	}

	/**
	 * returns the entity this sprite is representing
	 */
	public AbstractEntity getEntity() {
		return entity;
	}
}
