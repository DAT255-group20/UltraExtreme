package ultraextreme.view;

import java.util.LinkedList;
import java.util.List;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.entity.AbstractBullet;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
/**
 * Class responsible for reflecting the model with a graphical representation
 * @author Johan Gronvall
 *
 */
public class GameScene extends Scene implements IOnSceneTouchListener, SensorEventListener {

	private IUltraExtremeModel gameModel;
//	private Map<BulletID, RectangularShape> bulletSpriteMap;
	private Rectangle shipSprite;
	private SensorManager sensorManager;
	private List<BulletSprite> bulletSprites;

	public GameScene(IUltraExtremeModel gameModel,
			VertexBufferObjectManager vertexBufferObjectManager,
			SensorManager sensorManager) {
		this.gameModel = gameModel;
//		Sprite basicBulletSprite = new RectangularShape(); //todo sprite specification
//		bulletSpriteMap.put(BulletID.BASIC_BULLET, basicBulletSprite);
		setBackground(new Background(0.09804f, 0.6274f, 0));	
		shipSprite = new Rectangle(10, 10, 100, 200, vertexBufferObjectManager);
		bulletSprites = new LinkedList<BulletSprite>();
		SpriteContainer.playerShip = shipSprite;
		attachChild(shipSprite);
		setOnSceneTouchListener(this);
		
		this.sensorManager = sensorManager;
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
		
		// Start the game loop and add it as a listener to the bullet manage
		GameLoop gameLoop = new GameLoop(this, gameModel, bulletSprites, vertexBufferObjectManager);
		gameModel.getBulletManager().addPropertyChangeListener(gameLoop);
		registerUpdateHandler(gameLoop);
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

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		//shipSprite.setX(shipSprite.getX() + event.values[1]);
	}
}