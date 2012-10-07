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

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class GameOverScene extends MenuScene {

	public static final int GOTO_MENU = 0;

	public GameOverScene(final Camera camera, final Font font,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super(camera);
		setBackground(new Background(0.9f, 0.1f, 0.1f));
		final IMenuItem gotoMenuButton = new TextMenuItem(GOTO_MENU, font,
				Resources.getInstance().getResource(ResourceName.GOTO_MENU),
				vertexBufferObjectManager);
		gotoMenuButton.setPosition(100, 100);
		gotoMenuButton.setColor(Color.BLACK);
		addMenuItem(gotoMenuButton);
	}
}