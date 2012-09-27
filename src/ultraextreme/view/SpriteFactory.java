package ultraextreme.view;

import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.SimpleBaseGameActivity;

import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.ObjectName;

/**
 * Class in charge of creating new GameObjectSprites
 * 
 * @author Johan Gronvall
 * 
 */
public class SpriteFactory {

	Map<ObjectName, ITextureRegion> textureMap;

	// TODO not yet implemented offsets
	Map<ObjectName, Integer> offsetMap;

	private BitmapTextureAtlas textureAtlas;

	/**
	 * Creates a spriteFactory OBS: should be called during a loadResources
	 * because this constructor might get heavy
	 */
	public SpriteFactory(SimpleBaseGameActivity activity) {
		textureMap = new HashMap<ObjectName, ITextureRegion>();
		// TODO does this work? might not pickup what i want
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		TextureManager textureManager = activity.getTextureManager();
		this.textureAtlas = new BitmapTextureAtlas(textureManager, 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		TextureRegion playerShip = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity,
						"ship_placeholder.png", 0, 0);
		textureMap.put(ObjectName.PLAYERSHIP, playerShip);

		TextureRegion playerBullet = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity,
						"bullet_placeholder.png", 40, 0);
		textureMap.put(ObjectName.BASIC_BULLET, playerBullet);

		TextureRegion BasicEnemy = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity,
						"enemy_placeholder.png", 0, 40);
		textureMap.put(ObjectName.BASIC_ENEMYSHIP, BasicEnemy);

		textureManager.loadTexture(textureAtlas);
	}

	/**
	 * Creates and returns a sprite of the specified type
	 * 
	 * @param entity
	 *            The entity which this sprite is to follow
	 * @param vbom
	 *            the VertexBufferOBjectManager
	 * @param objectName
	 *            what kind of sprite (picture) is desired
	 * @return a new GameOBjectSprite
	 */
	public GameObjectSprite getNewSprite(AbstractEntity entity,
			VertexBufferObjectManager vbom) {
		textureMap.get(entity.getObjectName());
		return new GameObjectSprite(entity, vbom, textureMap.get(entity
				.getObjectName()));
	}
}
