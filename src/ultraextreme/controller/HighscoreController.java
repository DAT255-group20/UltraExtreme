package ultraextreme.controller;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import ultraextreme.view.HighscoreScene;

public class HighscoreController extends AbstractController implements
		IOnMenuItemClickListener {
	private final HighscoreScene scene;

	public HighscoreController() {
		super();
		this.scene = new HighscoreScene();
	}

	@Override
	public void activateController() {
		// Auto-generated method stub
	}

	@Override
	public void deactivateController() {
		// Auto-generated method stub
	}

	@Override
	public Scene getScene() {
		return scene;
	}

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		// TODO Auto-generated method stub
		return false;
	}
}