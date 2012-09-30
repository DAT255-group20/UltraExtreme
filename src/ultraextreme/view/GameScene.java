package ultraextreme.view;

import java.util.LinkedList;
import java.util.List;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.item.ItemBar;
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

	private final IUltraExtremeModel gameModel;
	private GameObjectSprite shipSprite;

	private final SensorManager sensorManager;
	private final List<GameObjectSprite> gameObjectSprites;
	private ItemBarPanel itemBarPanel;

	public GameScene(final IUltraExtremeModel gameModel,
			final VertexBufferObjectManager vertexBufferObjectManager,
			final SensorManager sensorManager,
			final SpriteFactory spriteFactory, float scaling) {
		super();

		this.gameModel = gameModel;
		setBackground(new Background(0, 0, 0));

		gameObjectSprites = new LinkedList<GameObjectSprite>();
		final GameObjectSprite playerSprite = spriteFactory.getNewSprite(
				gameModel.getPlayer().getShip(), vertexBufferObjectManager);

		gameObjectSprites.add(playerSprite);
		attachChild(playerSprite);

		ItemBar itemBar = gameModel.getPlayer().getItemBar();
		itemBarPanel = new ItemBarPanel(itemBar, spriteFactory,
				vertexBufferObjectManager, new Position(75, 1400), scaling);
		attachChild(itemBarPanel);

		this.sensorManager = sensorManager;
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, final int accuracy) {
		// Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// shipSprite.setX(shipSprite.getX() + event.values[1]);
	}

	public List<GameObjectSprite> getGameObjectSprites() {
		return gameObjectSprites;
	}

	public ItemBarPanel getItemBarPanel() {
		return itemBarPanel;
	}
}