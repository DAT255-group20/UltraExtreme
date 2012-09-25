package ultraextreme.controller;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;

import ultraextreme.view.MainMenuScene;

public class MainMenuController extends AbstractController implements
		IOnMenuItemClickListener {

	private MainMenuScene scene;

	public MainMenuController()
	{
	}
	
	@Override
	public boolean onMenuItemClicked(MenuScene menuScene, IMenuItem menuItem,
			float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {
		case MainMenuScene.MENU_START:
			// Switch to gamescene
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