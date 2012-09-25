package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
import ultraextreme.view.MainMenuScene;

public class MainMenuController extends AbstractController implements
		IOnMenuItemClickListener {

	private MainMenuScene scene;

	public MainMenuController(Camera camera, Font font, VertexBufferObjectManager vertexBufferObjectManager)
	{
		scene = new MainMenuScene(camera, font, vertexBufferObjectManager);
		scene.setOnMenuItemClickListener(this);
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene menuScene, IMenuItem menuItem,
			float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {
		case MainMenuScene.MENU_START:
			fireEvent(new ControllerEvent(this, ControllerEventType.SWITCH_TO_GAME));
			break;

		default:
			break;
		}
		return true;
	}

	@Override
	public Scene getScene() {
		return scene;
	}
}