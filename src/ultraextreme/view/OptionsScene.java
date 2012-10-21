package ultraextreme.view;

import javax.vecmath.Vector2d;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.SpriteBackground;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.util.Difficulty;
import ultraextreme.model.util.Dimension;
import ultraextreme.view.SpriteFactory.OptionsTexture;

public class OptionsScene extends MenuScene {

	// Button IDs
	public static final int CHANGE_DIFFICULTY = 0;
	public static final int RESET_HIGH_SCORES = 1;
	public static final int RETURN_TO_MAIN_MENU = 2;
	
	private IMenuItem difficultyButton;
	private VertexBufferObjectManager vertexBufferObjectManager;
	private Vector2d scaling;
	float screenWidth;
	private IMenuItem[] difficultyButtons;
	private int currentButton;

	public OptionsScene(final Camera camera,
			final VertexBufferObjectManager vertexBufferObjectManager,
			Difficulty difficulty) {
		super(camera);

		/*
		 * Add the background.
		 */
		final float screenWidth = camera.getWidth();
		this.screenWidth = screenWidth;
		final float screenHeight = camera.getHeight();
		final SpriteBackground background = new SpriteBackground(new Sprite(0,
				0, screenWidth, screenHeight,
				SpriteFactory.getOptionsTexture(OptionsTexture.BACKGROUND),
				vertexBufferObjectManager));
		setBackground(background);
		this.vertexBufferObjectManager = vertexBufferObjectManager;

		/*
		 * Scaling
		 */
		final Dimension screenDimension = new Dimension(screenWidth,
				screenHeight);
		// The following resolution is what the background and buttons were made
		// for
		Vector2d scaling = screenDimension
				.getQuotient(new Dimension(800, 1280));
		this.scaling = scaling;

		/*
		 * Add the difficulty button.
		 */
		currentButton = 0;
		difficultyButtons = new IMenuItem[4];
		difficultyButtons[0] = createButton(CHANGE_DIFFICULTY, 350, OptionsTexture.NORMAL_DIFFICULTY,
				scaling, screenWidth, vertexBufferObjectManager);
		addMenuItem(difficultyButtons[0]);
		difficultyButtons[1] = createButton(CHANGE_DIFFICULTY, 350, OptionsTexture.HARD_DIFFICULTY,
				scaling, screenWidth, vertexBufferObjectManager);
		difficultyButtons[2] = createButton(CHANGE_DIFFICULTY, 350, OptionsTexture.EXTREME_DIFFICULTY,
				scaling, screenWidth, vertexBufferObjectManager);
		difficultyButtons[3] = createButton(CHANGE_DIFFICULTY, 350, OptionsTexture.ULTRAEXTREME_DIFFICULTY,
				scaling, screenWidth, vertexBufferObjectManager);

		/*
		 * Add the reset button.
		 */
		addMenuItem(createButton(RESET_HIGH_SCORES, 650, OptionsTexture.RESET_BUTTON,
				scaling, screenWidth, vertexBufferObjectManager));
		
		/*
		 * Add the return button.
		 */
		addMenuItem(createButton(RETURN_TO_MAIN_MENU, 950, OptionsTexture.RETURN_BUTTON,
				scaling, screenWidth, vertexBufferObjectManager));
	}

	/**
	 * Add a button the the scene
	 * @param destination
	 * @param y
	 * @param texture
	 * @param scaling
	 * @param screenWidth
	 * @param vertexBufferObjectManager
	 */
	private IMenuItem createButton(final int destination, final int y,
			final OptionsTexture texture, final Vector2d scaling,
			final float screenWidth,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		final IMenuItem button = new SpriteMenuItem(destination,
				SpriteFactory.getOptionsTexture(texture),
				vertexBufferObjectManager);
		button.setWidth((float) scaling.x * button.getWidth());
		button.setHeight((float) scaling.y * button.getHeight());
		button.setX((screenWidth - button.getWidth()) / 2);
		button.setY((float) scaling.y * y);
		return button;
	}

	public void updateDifficultyButton(Difficulty difficulty) {
//		detachChild(button);
//		button = new SpriteMenuItem(CHANGE_DIFFICULTY,
//				SpriteFactory.getOptionsTexture(OptionsTexture.EXTREME_DIFFICULTY),
//				vertexBufferObjectManager);
//		button.setWidth((float) scaling.x * button.getWidth());
//		button.setHeight((float) scaling.y * button.getHeight());
//		button.setX((screenWidth - button.getWidth()) / 2);
//		button.setY((float) scaling.y * 350);
//		addMenuItem(button);
		detachChild(difficultyButtons[currentButton++]);
		currentButton %= 4;
		addMenuItem(difficultyButtons[currentButton]);
	}
}