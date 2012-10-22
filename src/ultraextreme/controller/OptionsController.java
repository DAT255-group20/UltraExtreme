package ultraextreme.controller;

import java.io.File;

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

	/**
	 * Reference to the scene.
	 */
	private final OptionsScene scene;

	/**
	 * Reference to the High scores database handler, so it's possible to reset
	 * the database from the Options view.
	 */
	private HighscoreDBOpenHelper dbOpenHelper;

	/**
	 * Reference to the OptionsDatabse, so it's possible to change difficulty
	 * level from the Options view.
	 */
	private OptionsDatabase optionsDatabase;
	
	/**
	 * What the difficulty is currently set to.
	 */
	private Difficulty currentSetDifficulty;

	/**
	 * 
	 * @param camera
	 * @param vertexBufferObjectManager
	 * @param optionsDatabase
	 * @param dbOpenHelper
	 */
	public OptionsController(final Camera camera,
			final VertexBufferObjectManager vertexBufferObjectManager,
			OptionsDatabase optionsDatabase, HighscoreDBOpenHelper dbOpenHelper) {
		super();
		this.dbOpenHelper = dbOpenHelper;
		this.optionsDatabase = optionsDatabase;
		currentSetDifficulty = optionsDatabase.getDifficultyLevel();
		scene = new OptionsScene(camera, vertexBufferObjectManager, currentSetDifficulty);
		scene.setOnMenuItemClickListener(this);
	}

	@Override
	public boolean onMenuItemClicked(final MenuScene menuScene,
			final IMenuItem menuItem, float menuItemLocalX, float menuItemLocalY) {
		switch (menuItem.getID()) {

		case OptionsScene.CHANGE_DIFFICULTY:
			Difficulty newDifficulty;
			switch (currentSetDifficulty) {
			case NORMAL:
				newDifficulty = Difficulty.HARD;
				break;
			case HARD:
				newDifficulty = Difficulty.EXTREME;
				break;
			case EXTREME:
				newDifficulty = Difficulty.ULTRAEXTREME;
				break;
			case ULTRAEXTREME:
				newDifficulty = Difficulty.NORMAL;
				break;
			default:
				newDifficulty = Difficulty.NORMAL;
				break;
			}
			scene.updateDifficultyButton(newDifficulty);
			optionsDatabase.setDifficultyLevel(newDifficulty);
			currentSetDifficulty = newDifficulty;
			break;

		case OptionsScene.RESET_HIGH_SCORES:

			// Delete the high score database file
			File db = new File(dbOpenHelper.getWritableDatabase().getPath());
			dbOpenHelper.close();
			db.delete();
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
