/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

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

	private static Dimension screenDimension;
	private static final Dimension MODEL_DIMENSION = Constants
			.getLevelDimension();

	public static void setScreenDimension(final Dimension dimension) {
		screenDimension = dimension;
	}

	/**
	 * Reference to an entity in the model.
	 */
	private final IEntity entity;

	private boolean isHitBlinked;

	private boolean isInvincibleBlinked;
	private Vector2d directionVector;

	private final Vector2d imageOffset;

	public GameObjectSprite(final IEntity entity,
			final VertexBufferObjectManager vertexBufferObjectManager,
			final ITextureRegion texture, Vector2d imageOffset) {

		// Change the width and height to the entity's width and height to
		// squeeze the picture
		super((float) (entity.getPositionClone().getX() - imageOffset.x),
				(float) (entity.getPositionClone().getY() - imageOffset.y),
				texture.getWidth()
						* ultraextreme.util.Constants.SPRITE_SCALE_FACTOR,
				texture.getHeight()
						* ultraextreme.util.Constants.SPRITE_SCALE_FACTOR,
				texture, vertexBufferObjectManager);

		this.entity = entity;

		this.imageOffset = imageOffset;

		this.isHitBlinked = false;
		this.isInvincibleBlinked = false;

		if (screenDimension == null) {
			screenDimension = MODEL_DIMENSION;
		}
		Vector2d scalingQuotient = screenDimension.getQuotient(MODEL_DIMENSION);
		this.setScale((float) scalingQuotient.x, (float) scalingQuotient.y);
	}

	/**
	 * returns the entity this sprite is representing
	 */
	public IEntity getEntity() {
		return entity;
	}

	/**
	 * Blinks the sprite between two states of colour representing
	 * invincibility.
	 */
	public void invincibilityBlink() {
		setInvincibleBlinked(!isInvincibleBlinked);
	}

	private void setInvincibleBlinked(boolean b) {
		if (b) {
			this.setColor(0f, 0f, 1f);
		} else {
			this.setColor(1f, 1f, 1f);
		}
		isInvincibleBlinked = b;
	}

	/**
	 * Blinks the sprite between two states of colour representing that it is
	 * hit.
	 */
	public void onHitBlink() {
		setOnHitBlink(!isHitBlinked);
	}

	private void setOnHitBlink(boolean b) {
		if (b) {
			this.setColor(1f, 0f, 0f);
		} else {
			this.setColor(1f, 1f, 1f);
		}
		isHitBlinked = b;
	}

	@Override
	public void reset() {
		setInvincibleBlinked(false);
		setOnHitBlink(false);
	}

	/**
	 * Update the bullet sprite with data from the model.
	 */
	public void update() {
		final Position newPosition = screenDimension.scalePosition(
				MODEL_DIMENSION, entity.getPositionClone());
		this.setX((float) (newPosition.getX() - imageOffset.x));
		this.setY((float) (newPosition.getY() - imageOffset.y));

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
}
