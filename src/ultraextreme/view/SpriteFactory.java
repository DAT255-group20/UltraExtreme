package ultraextreme.view;

import java.util.HashMap;
import java.util.Map;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.ObjectName;
/**
 * Class in charge of creating new GameObjectSprites
 * @author Johan Gronvall
 *
 */
public class SpriteFactory {
	
	Map<ObjectName, ITextureRegion> textureMap;
	
	//TODO not yet changed
	Map<ObjectName, Integer> offsetMap;
	
	/**
	 * Creates a spriteFactory
	 * OBS: should be called during a loadResources because this constructor might get heavy
	 */
	public SpriteFactory() {
		textureMap = new HashMap<ObjectName, ITextureRegion>();
		ITextureRegion shiptexture = new MTextureRegion();
	}

	 /**
	  * 	 Creates and returns a sprite of the specified type
	  * @param entity The entity which this sprite is to follow
	  * @param vbom the VertexBufferOBjectManager
	  * @param objectName what kind of sprite (picture) is desired
	  * @return a new GameOBjectSprite
	  */
	public GameObjectSprite getNewSprite(AbstractEntity entity, VertexBufferObjectManager vbom, ObjectName objectName) {
		textureMap.get(objectName);
		return new GameObjectSprite(entity, vbom, textureMap.get(objectName));
	}
}
