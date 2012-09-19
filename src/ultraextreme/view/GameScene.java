package ultraextreme.view;

import java.util.List;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.entity.AbstractBullet;
/**
 * Class responsible for reflecting the model with a graphical representation
 * @author Johan Gronvall
 *
 */
public class GameScene extends Scene implements IOnSceneTouchListener {

	private IUltraExtremeModel gameModel;
//	private Map<BulletID, RectangularShape> bulletSpriteMap;
	private Rectangle shipSprite;

	public GameScene(IUltraExtremeModel gameModel,
			VertexBufferObjectManager vertexBufferObjectManager) {
		this.gameModel = gameModel;
//		Sprite basicBulletSprite = new RectangularShape(); //todo sprite specification
//		bulletSpriteMap.put(BulletID.BASIC_BULLET, basicBulletSprite);
		setBackground(new Background(0.09804f, 0.6274f, 0));	
		shipSprite = new Rectangle(10, 10, 100, 200, vertexBufferObjectManager);
		attachChild(shipSprite);
		setOnSceneTouchListener(this);
	}

	private void drawBullets(List<AbstractBullet> bulletList) {
//		for(AbstractBullet bullet : bulletList) {
//			bulletSpriteMap.get(bullet.getBulletID())
//		}
	}

	@Override
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}