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

import ultraextreme.model.entity.IEntity;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Dimension;
import ultraextreme.model.util.ObjectName;

/**
 * Class in charge of creating new GameObjectSprites
 * 
 * @author Johan Gronvall
 * 
 */
public class SpriteFactory {

	private Map<ObjectName, ITextureRegion> textureMap;

	// TODO not yet implemented offsets
	private Map<ObjectName, Integer> offsetMap;

	// TODO PMD: Perhaps 'screenDimension' could be replaced by a local
	// variable.
	private Dimension screenDimension; // TODO implement scaling in this class?
	// TODO PMD: Avoid unused private fields such as 'MODEL_DIMENSION'.
	private static final Dimension MODEL_DIMENSION = Constants
			.getLevelDimension();

	/**
	 * The item bar's texture.
	 */
	private ITextureRegion itemBarTexture;

	/**
	 * The items' textures.
	 */
	private Map<ObjectName, ITextureRegion> itemTextures;

	private static SpriteFactory instance;

	/**
	 * Creates a spriteFactory OBS: should be called during a loadResources
	 * because this constructor might get heavy
	 */
	private SpriteFactory(final SimpleBaseGameActivity activity) {
		screenDimension = new Dimension(activity.getResources()
				.getDisplayMetrics().widthPixels, activity.getResources()
				.getDisplayMetrics().heightPixels);
		GameObjectSprite.setScreenDimension(screenDimension);
		textureMap = new HashMap<ObjectName, ITextureRegion>();
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		final TextureManager textureManager = activity.getTextureManager();
		BitmapTextureAtlas textureAtlas = new BitmapTextureAtlas(
				textureManager, 1024, 1024,
				TextureOptions.BILINEAR_PREMULTIPLYALPHA);

		// init enemies bullets and the player
		final TextureRegion playerShip = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity,
						"ship_placeholder.png", 0, 0);
		textureMap.put(ObjectName.PLAYERSHIP, playerShip);

		final TextureRegion playerBullet = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity,
						"bullet_placeholder.png", 40, 0);
		textureMap.put(ObjectName.BASIC_BULLET, playerBullet);

		final TextureRegion BasicEnemy = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity,
						"enemy_placeholder.png", 0, 40);
		textureMap.put(ObjectName.BASIC_ENEMYSHIP, BasicEnemy);

		// init pickupables
		textureMap.put(ObjectName.BASIC_WEAPON, BasicEnemy);
		textureMap.put(ObjectName.SPINNING_SPREAD_WEAPON, playerShip);

		// Init the item bar texture
		itemBarTexture = BitmapTextureAtlasTextureRegionFactory
				.createFromAsset(textureAtlas, activity, "itembar.png", 80, 0);

		// Init the item textures for items in the itembar
		itemTextures = new HashMap<ObjectName, ITextureRegion>();
		itemTextures.put(ObjectName.BASIC_WEAPON, BasicEnemy); // Test only
		itemTextures.put(ObjectName.SPINNING_SPREAD_WEAPON, playerShip); // Test
																			// only

		// What is this for?(I think it needs to be called to init the atlas, we
		// will never know.. gramlich 2012)
		textureManager.loadTexture(textureAtlas);
	}

	public static void initialize(SimpleBaseGameActivity activity) {
		instance = new SpriteFactory(activity);
	}

	public static SpriteFactory getInstance() {
		if (instance == null) {
			throw new IllegalStateException(
					"SpriteFactory must have been initialized before it is used");
		}
		return instance;
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
	public GameObjectSprite getNewSprite(final IEntity entity,
			final VertexBufferObjectManager vbom) {
		textureMap.get(entity.getObjectName());
		return new GameObjectSprite(entity, vbom, textureMap.get(entity
				.getObjectName()));
	}

	/**
	 * 
	 * @return The texture of the item bar.
	 */
	public ITextureRegion getItemBarTexture() {
		return itemBarTexture;
	}

	/**
	 * 
	 * @param item
	 *            The item you want an image of.
	 * @return An texture of an item that you want to show in the item bar.
	 */
	public ITextureRegion getItemTexture(ObjectName item) {
		return itemTextures.get(item);
	}
}
