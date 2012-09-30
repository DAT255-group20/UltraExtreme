package ultraextreme.view;

import java.beans.PropertyChangeEvent;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.ItemBar;
import ultraextreme.model.item.ItemBarUpdatedListener;
import ultraextreme.model.util.Position;

public class ItemBarPanel extends Sprite implements ItemBarUpdatedListener {

	private SpriteFactory spriteFactory;

	private VertexBufferObjectManager vertexBufferObjectManager;

	public ItemBarPanel(ItemBar itemBar, SpriteFactory spriteFactory,
			VertexBufferObjectManager vertexBufferObjectManager,
			Position position, float scaling) {
		super((float) position.getX() * scaling, (float) position.getY()
				* scaling, 750 * scaling, 75 * scaling, spriteFactory
				.getItemBarTexture(), vertexBufferObjectManager);
		this.spriteFactory = spriteFactory;
		this.vertexBufferObjectManager = vertexBufferObjectManager;
		updateItemBar(itemBar);
	}

	/**
	 * Make the item bar update itself.
	 */
	public void updateItemBar(ItemBar itemBar) {
		// Clean the item bar from sprites
		this.detachChildren();
		// Repopulate it with sprites
		for (int i = 0; i < itemBar.getItems().size(); i++) {
			AbstractWeapon item = itemBar.getItems().get(i);
			int x = i * 60 + 10;
			int y = 10;
			this.attachChild(new Sprite(x, y, spriteFactory.getItemTexture(item
					.getName()), vertexBufferObjectManager));
		}
	}

	@Override
	public void updatedItemBar(ItemBar itemBar) {
		updateItemBar(itemBar);
	}

}