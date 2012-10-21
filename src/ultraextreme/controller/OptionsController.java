package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.controller.ControllerEvent.ControllerEventType;
import ultraextreme.model.util.Difficulty;
import ultraextreme.view.OptionsScene;

public class OptionsController extends AbstractController implements
		IOnMenuItemClickListener {
	
	private final OptionsScene scene;

	public OptionsController(final Camera camera,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		super();
		scene = new OptionsScene(camera, vertexBufferObjectManager, Difficulty.NORMAL);
		scene.setOnMenuItemClickListener(this);
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public void activateController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deactivateController() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButtonPressed() {
		fireEvent(new ControllerEvent(this, ControllerEventType.SWITCH_TO_MENU));
	}

}
