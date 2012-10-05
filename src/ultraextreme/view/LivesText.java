package ultraextreme.view;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IPlayer;
import ultraextreme.model.IPlayerListener;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Position;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

/**
 * A text displaying the players current lives.
 * 
 * @author Viktor Anderling
 * 
 */
public class LivesText extends Text implements IPlayerListener {

	public LivesText(Position textPos, Font font, VertexBufferObjectManager vbo) {
		super((float) textPos.getX(), (float) textPos.getY(), font, Resources
				.getInstance().getResource(ResourceName.LIVES)
				+ " "
				+ +Constants.getInitShipLives(), vbo);
	}

	@Override
	public void playerUpdate(IPlayer player) {
		setText(Resources.getInstance().getResource(ResourceName.LIVES) + " "
				+ +player.getLives());
	}
}
