package ultraextreme.view;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.util.GLState;

public class SensibleSprite extends Sprite {

	public SensibleSprite(float pX, float pY, float pWidth, float pHeight,
			ITextureRegion pTextureRegion,
			ISpriteVertexBufferObject pSpriteVertexBufferObject) {
		super(pX, pY, pWidth, pHeight, pTextureRegion,
				pSpriteVertexBufferObject);
		// TODO Auto-generated constructor stub
	}

	// A method which FUCKING DRAWS THE MOTHERFUCKING SPRITE
	@Override
	public void draw(final GLState pGLState, final Camera pCamera) {
		super.draw(pGLState, pCamera);
	}

}
