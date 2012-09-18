package ultraextreme.view;

import java.util.List;
import java.util.Map;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.IBullet;
import ultraextreme.model.util.BulletID;
/**
 * Class responsible for reflecting the model with a graphical representation
 * @author Johan Gronvall
 *
 */
public class GameScene extends Scene {

	private IUltraExtremeModel gameModel;
	private Map<AbstractBullet, Sprite> bulletSpriteMap;

	public GameScene(IUltraExtremeModel gameModel) {
		this.gameModel = gameModel;
		Sprite basicBulletSprite = new Sprite(); //todo sprite specification
		bulletSpriteMap.put(BulletID.BASIC_BULLET, basicBulletSprite);
	}

	private void drawBullets(List<IBullet> bulletList) {
		for(IBullet bullet : bulletList) {
			bulletSpriteMap.get(bullet).
		}
	}
}