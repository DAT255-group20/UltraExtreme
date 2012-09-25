package ultraextreme.view;

import java.util.Map;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.entity.AbstractEntity;
import ultraextreme.model.util.ObjectName;

public class SpriteFactory {
	
	Map<ObjectName, ITextureRegion> textureMap;
	
	/**
	 * Creates a spriteFactory
	 * OBS: should be called during a loadResources because this constructor might get heavy
	 */
	public SpriteFactory() {
		
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
