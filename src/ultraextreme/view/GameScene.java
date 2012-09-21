package ultraextreme.view;

import java.util.LinkedList;
import java.util.List;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.entity.PlayerShip;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Class responsible for reflecting the model with a graphical representation
 * 
 * @author Johan Gronvall
 * 
 */
public class GameScene extends Scene implements SensorEventListener {

	private IUltraExtremeModel gameModel;
	// private Map<BulletID, RectangularShape> bulletSpriteMap;
	private Rectangle shipSprite;
	private SensorManager sensorManager;
	private List<BulletSprite> bulletSprites;

	public GameScene(IUltraExtremeModel gameModel,
			VertexBufferObjectManager vertexBufferObjectManager,
			SensorManager sensorManager) {
		this.gameModel = gameModel;
		// Sprite basicBulletSprite = new RectangularShape(); //todo sprite
		// specification
		// bulletSpriteMap.put(BulletID.BASIC_BULLET, basicBulletSprite);
		setBackground(new Background(0.09804f, 0.6274f, 0));
		PlayerShip playerShip = gameModel.getPlayer().getShip();
		shipSprite = new Rectangle(10, 10, playerShip.getWidth(),
				playerShip.getHeight(), vertexBufferObjectManager);
		bulletSprites = new LinkedList<BulletSprite>();
		SpriteContainer.playerShip = shipSprite;
		attachChild(shipSprite);

		this.sensorManager = sensorManager;
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}

	private void drawBullets(List<AbstractBullet> bulletList) {
		// for(AbstractBullet bullet : bulletList) {
		// bulletSpriteMap.get(bullet.getBulletID())
		// }
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// shipSprite.setX(shipSprite.getX() + event.values[1]);
	}

	public List<BulletSprite> getBulletSprites() {
		return bulletSprites;
	}
}