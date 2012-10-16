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

public class HighscoreScene extends MenuScene {

	public static final int GOTO_MENU = 0;

	public HighscoreScene(Camera camera, Font font,
			VertexBufferObjectManager vbo) {
		super(camera);
		setBackground(new Background(0.1f, 0.9f, 0.1f));
		final IMenuItem gotoMenuButton = new TextMenuItem(GOTO_MENU, font,
				Resources.getInstance().getResource(ResourceName.GOTO_MENU),
				vbo);
		gotoMenuButton.setPosition(100, 100);
		gotoMenuButton.setColor(Color.BLACK);
		addMenuItem(gotoMenuButton);
	}
}