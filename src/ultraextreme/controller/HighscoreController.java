package ultraextreme.controller;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.view.HighscoreScene;

public class HighscoreController extends AbstractController implements
		IOnMenuItemClickListener {
	private final HighscoreScene scene;
	private HighscoreDBOpenHelper dbOpenHelper;

	public HighscoreController(Camera camera, Font font,
			VertexBufferObjectManager vbo, HighscoreDBOpenHelper dbOpenHelper) {
		super();
		this.scene = new HighscoreScene(camera, font, vbo);
		scene.setOnMenuItemClickListener(this);
		this.dbOpenHelper = dbOpenHelper;
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