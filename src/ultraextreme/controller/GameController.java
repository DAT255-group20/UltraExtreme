package ultraextreme.controller;

import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import ultraextreme.model.GameModel;
import ultraextreme.view.GameScene;

/**
 * Controller for the game model.
 * @author Bjorn Persson Mattsson
 *
 */
public class GameController extends AbstractController implements IOnSceneTouchListener {

	private GameScene scene;
	private GameModel gameModel;

	@Override
	public boolean onSceneTouchEvent(Scene arg0, TouchEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}
}