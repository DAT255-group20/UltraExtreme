package ultraextreme.view;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import android.util.Log;

public class MainMenuScene extends MenuScene {

	public static final int MENU_START = 0;

	public MainMenuScene(Camera camera, Font font,
			VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		// TODO Should probably use baseGameActivity.getString(R.string.start)
		// instead of hard coding
		IMenuItem startButton = new TextMenuItem(MENU_START, font,
				"Start game", vertexBufferObjectManager);
		startButton.setPosition(100, 100);
		addMenuItem(startButton);
		Log.d("DEBUG", "onCreateScene");
	}
}