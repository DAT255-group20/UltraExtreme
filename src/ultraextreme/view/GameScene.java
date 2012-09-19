package ultraextreme.view;

import java.util.List;
import java.util.Map;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.shape.RectangularShape;
import org.andengine.entity.sprite.Sprite;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.util.BulletID;
/**
 * Class responsible for reflecting the model with a graphical representation
 * @author Johan Gronvall
 *
 */
public class GameScene extends Scene {

	private IUltraExtremeModel gameModel;
	private Map<BulletID, RectangularShape> bulletSpriteMap;

	public GameScene(IUltraExtremeModel gameModel) {
		this.gameModel = gameModel;
		Sprite basicBulletSprite = new RectangularShape(); //todo sprite specification
		bulletSpriteMap.put(BulletID.BASIC_BULLET, basicBulletSprite);
	}

	private void drawBullets(List<AbstractBullet> bulletList) {
		for(AbstractBullet bullet : bulletList) {
			bulletSpriteMap.get(bullet.getBulletID())
		}
	}
}