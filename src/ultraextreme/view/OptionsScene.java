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
import ultraextreme.view.SpriteFactory.OPTIONS_TEXTURES;

public class OptionsScene extends MenuScene {

	// Button IDs
	public static final int CHANGE_DIFFICULTY = 0;

	public OptionsScene(final Camera camera,
			final VertexBufferObjectManager vertexBufferObjectManager,
			Difficulty difficulty) {
		super(camera);

		/*
		 * Add the background.
		 */
		final float screenWidth = camera.getWidth();
		final float screenHeight = camera.getHeight();
		final SpriteBackground background = new SpriteBackground(new Sprite(0,
				0, screenWidth, screenHeight,
				SpriteFactory.getOptionsTexture(OPTIONS_TEXTURES.BACKGROUND),
				vertexBufferObjectManager));
		setBackground(background);

		/*
		 * Scaling
		 */
		final Dimension screenDimension = new Dimension(screenWidth,
				screenHeight);
		// The following resolution is what the background and buttons were made
		// for
		Vector2d scaling = screenDimension
				.getQuotient(new Dimension(800, 1280));

		/*
		 * Add the difficulty button.
		 */
		addButton(CHANGE_DIFFICULTY, 950, OPTIONS_TEXTURES.NORMAL_DIFFICULTY,
				scaling, screenWidth, vertexBufferObjectManager);

		/*
		 * Add the reset button.
		 */
		addButton(CHANGE_DIFFICULTY, 650, OPTIONS_TEXTURES.RESET_BUTTON,
				scaling, screenWidth, vertexBufferObjectManager);
	}

	private void addButton(final int destination, final int y,
			final OPTIONS_TEXTURES texture, final Vector2d scaling,
			final float screenWidth,
			final VertexBufferObjectManager vertexBufferObjectManager) {
		final IMenuItem button = new SpriteMenuItem(destination,
				SpriteFactory.getOptionsTexture(texture),
				vertexBufferObjectManager);
		button.setWidth((float) scaling.x * button.getWidth());
		button.setHeight((float) scaling.y * button.getHeight());
		button.setX((screenWidth - button.getWidth()) / 2);
		button.setY((float) scaling.y * y);
		addMenuItem(button);
	}

	public void updateDifficultyButton(Difficulty difficulty) {

	}
}