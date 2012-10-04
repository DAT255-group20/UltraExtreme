package ultraextreme.view;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.TextMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

import android.util.Log;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class MainMenuScene extends MenuScene {

	public static final int MENU_START = 0;

	public MainMenuScene(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);
		setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		final IMenuItem startButton = new TextMenuItem(MENU_START, font,
				Resources.getInstance().getResource(ResourceName.START_GAME),
				vertexBufferObjectManager);
		startButton.setPosition(100, 100);
		startButton.setColor(Color.BLACK);
		addMenuItem(startButton);
		Log.d("DEBUG", "onCreateScene");
	}
}