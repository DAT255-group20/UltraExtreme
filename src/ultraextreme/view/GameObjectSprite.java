package ultraextreme.view;

import javax.vecmath.Vector2d;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.math.MathUtils;

import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.Position;

public class GameObjectSprite extends Sprite {

	/**
	 * Reference to a bullet in the model.
	 */
	private AbstractEntity entity;
	
	private Vector2d directionVector;
	
	private static Dimension screenDimension;
	private static final Dimension modelDimension = Constants.getInstance().getLevelDimension();

	public GameObjectSprite(AbstractEntity entity,
			VertexBufferObjectManager pVertexBufferObjectManager,
			ITextureRegion texture) {

		super((float) entity.getPosition().getX(), (float) entity.getPosition()
				.getY(), entity.getWidth(), entity.getHeight(), texture,
				pVertexBufferObjectManager);
		this.entity = entity;
		if(screenDimension == null) {
			screenDimension = modelDimension;
		}
	}

	/**
	 * Update the bullet sprite with data from the model.
	 */
	public void update() {
		Position newPosition = screenDimension.scalePosition(modelDimension, entity.getPosition());
		this.setX((float) newPosition.getX());
		this.setY((float) newPosition.getY());
		if(entity instanceof AbstractBullet) {
			Vector2d newVector = entity.getNormalizedDirection();
			if(!(newVector.x == 0 && newVector.y == 0)) {
				directionVector = newVector;
			}
			float newAngle = MathUtils.radToDeg((float) (Math.atan(directionVector.y / directionVector.x)));
			if(directionVector.x < 0) {
				newAngle = newAngle + 180f;
			}
			this.setRotation(newAngle + 90f);
		}
	}

	/**
	 * returns the entity this sprite is representing
	 */
	public AbstractEntity getEntity() {
		return entity;
	}
	
	public static void setScreenDimension(Dimension dimension) {
		screenDimension = dimension;
	}
}
