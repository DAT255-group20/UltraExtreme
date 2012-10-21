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

/**
 * 
 * @author Daniel Jonsson
 *
 */
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
	public boolean onMenuItemClicked(final MenuScene menuScene,
			final IMenuItem menuItem, float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {
		case OptionsScene.CHANGE_DIFFICULTY:
			scene.updateDifficultyButton(Difficulty.HARD);
			break;

		case OptionsScene.RESET_HIGH_SCORES:
			// FIXME
			break;

		case OptionsScene.RETURN_TO_MAIN_MENU:
			fireEvent(new ControllerEvent(this,
					ControllerEventType.SWITCH_TO_MENU));
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
