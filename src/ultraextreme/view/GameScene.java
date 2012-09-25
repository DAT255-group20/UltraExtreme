package ultraextreme.view;

import java.util.LinkedList;
import java.util.List;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.entity.PlayerShip;
import ultraextreme.model.util.Position;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Class responsible for reflecting the model with a graphical representation
 * 
 * @author Johan Gronvall
 * @author Daniel Jonsson
 * 
 */
public class GameScene extends Scene implements SensorEventListener {

	private IUltraExtremeModel gameModel;
	private Rectangle shipSprite;
	private SensorManager sensorManager;
	private List<GameObjectSprite> gameObjectSprites;

	public GameScene(IUltraExtremeModel gameModel,
			VertexBufferObjectManager vertexBufferObjectManager,
			SensorManager sensorManager) {
		this.gameModel = gameModel;
		setBackground(new Background(0.09804f, 0.6274f, 0));
		PlayerShip playerShip = gameModel.getPlayer().getShip();
		Position p = gameModel.getPlayer().getShip().getPosition();
		shipSprite = new Rectangle((float) p.getX(), (float) p.getY(),
				playerShip.getWidth(), playerShip.getHeight(),
				vertexBufferObjectManager);
		
		gameObjectSprites = new LinkedList<GameObjectSprite>();
		SpriteContainer.playerShip = shipSprite;
		attachChild(shipSprite);

		this.sensorManager = sensorManager;
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// shipSprite.setX(shipSprite.getX() + event.values[1]);
	}

	public List<GameObjectSprite> getGameObjectSprites() {
		return gameObjectSprites;
	}
}