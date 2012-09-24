package ultraextreme.controller;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import ultraextreme.view.MainMenuScene;

public class MainMenuController extends AbstractController implements
		IOnMenuItemClickListener {

	private MainMenuScene scene;

	@Override
	public boolean onMenuItemClicked(MenuScene arg0, IMenuItem arg1,
			float arg2, float arg3) {
		// TODO MainMenuController.onMenuItemClicked()
		return false;
	}

	@Override
	public Scene getScene() {
		// TODO MainMenuController.getScene()
		return null;
	}

}