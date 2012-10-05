package ultraextreme.view;

import javax.vecmath.Vector2d;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.math.MathUtils;

import ultraextreme.model.entity.IBullet;
import ultraextreme.model.entity.IEntity;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.Position;

/**
 * 
 * @author Johan Gronvall
 * 
 */
public class GameObjectSprite extends Sprite {

	/**
	 * Reference to a bullet in the model.
	 */
	private final IEntity entity;
	
	private boolean isBlinked;

	private Vector2d directionVector;

	// TODO PMD: Possible unsafe assignment to a non-final static field in a
	// constructor.
	private static Dimension screenDimension;
	private static final Dimension MODEL_DIMENSION = Constants
			.getLevelDimension();

	public GameObjectSprite(final IEntity entity,
			final VertexBufferObjectManager vertexBufferObjectManager,
			final ITextureRegion texture) {

		super((float) entity.getPosition().getX(), (float) entity.getPosition()
				.getY(), entity.getWidth(), entity.getHeight(), texture,
				vertexBufferObjectManager);
		this.entity = entity;
		this.isBlinked = false;
		if (screenDimension == null) {
			screenDimension = MODEL_DIMENSION;
		}
	}

	/**
	 * Update the bullet sprite with data from the model.
	 */
	public void update() {
		final Position newPosition = screenDimension.scalePosition(
				MODEL_DIMENSION, entity.getPosition());
		this.setX((float) newPosition.getX());
		this.setY((float) newPosition.getY());
		if (entity instanceof IBullet) {
			final Vector2d newVector = entity.getNormalizedDirection();
			if (!(newVector.x == 0 && newVector.y == 0)) {
				directionVector = newVector;
			}
			float newAngle = MathUtils.radToDeg((float) (Math
					.atan(directionVector.y / directionVector.x)));
			if (directionVector.x < 0) {
				newAngle = newAngle + 180f;
			}
			this.setRotation(newAngle + 90f);
		}
	}
	
	/**
	 * Switches the color of this sprite between two.
	 */
	public void blink() {
		if(!isBlinked) {
			this.setColor(0.5f, 0.5f, 0.5f);
			isBlinked = true;
		} else {
			this.setColor(1f, 1f, 1f);
			isBlinked = false;
		}
	}

	/**
	 * returns the entity this sprite is representing
	 */
	public IEntity getEntity() {
		return entity;
	}

	public static void setScreenDimension(final Dimension dimension) {
		screenDimension = dimension;
	}
}
