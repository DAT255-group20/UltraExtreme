package ultraextreme.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.ModelInput;
import ultraextreme.model.entity.AbstractBullet;
import ultraextreme.model.util.Position;
import android.util.Log;

public class GameLoop implements IUpdateHandler, PropertyChangeListener {

	private GameScene gameScene;
	
	private IUltraExtremeModel gameModel;
	
	private List<BulletSprite> bulletSprites;
	
	private VertexBufferObjectManager vertexBufferObjectManager;

	public GameLoop(GameScene gameScene, IUltraExtremeModel gameModel,
			List<BulletSprite> bulletSprites,
			VertexBufferObjectManager vertexBufferObjectManager) {
		this.gameScene = gameScene;
		this.gameModel = gameModel;
		this.bulletSprites = bulletSprites;
		this.vertexBufferObjectManager = vertexBufferObjectManager;
	}
	
	@Override
	public void onUpdate(float time) {
		gameModel.update(new ModelInput(1, 1, true, false), time);
		Position p = gameModel.getPlayer().getShip().getPosition();
		//Log.d("DEBUG", "" + p.getX());
		SpriteContainer.playerShip.setX((float) (p.getX()));
		SpriteContainer.playerShip.setY((float) (p.getY()));
		for(BulletSprite b : bulletSprites) {
			b.update();
		}
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName().equals("newBullet")) {
			BulletSprite b = new BulletSprite((AbstractBullet) event.getNewValue(), vertexBufferObjectManager);
			bulletSprites.add(b);
			gameScene.attachChild(b);
			Log.d("Bullet list length View", "" + bulletSprites.size());
		}
	}

}
