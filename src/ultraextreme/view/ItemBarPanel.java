package ultraextreme.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.item.ItemBar;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

public class ItemBarPanel extends Sprite implements PropertyChangeListener {

	private ItemBar itemBar;

	public ItemBarPanel(ItemBar itemBar, SpriteFactory spriteFactory,
			VertexBufferObjectManager vertexBufferObjectManager,
			Position position, float scaling) {
		super((float) position.getX() * scaling, (float) position.getY()
				* scaling, 750 * scaling, 75 * scaling, spriteFactory
				.getItemBarTexture(), vertexBufferObjectManager);
		this.itemBar = itemBar;
		this.attachChild(new Sprite(10, 10, spriteFactory
				.getItemTexture(ObjectName.BASIC_WEAPON),
				vertexBufferObjectManager));
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {

	}

}
