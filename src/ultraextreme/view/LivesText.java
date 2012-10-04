package ultraextreme.view;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IPlayer;
import ultraextreme.model.IPlayerListener;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Position;

/**
 * A text displaying the players current lives.
 * 
 * @author Viktor Anderling
 * 
 */
public class LivesText extends Text implements IPlayerListener {

	public LivesText(Position textPos, Font font, VertexBufferObjectManager vbo) {
		// Creates with empty characters to "allocate" memory for the object
		// According to
		// https://sites.google.com/site/matimdevelopment/creating-text
		super((float) textPos.getX(), (float) textPos.getY(), font, "Lives: "
				+ Constants.getInitShipLives() + "         ", vbo);
	}

	@Override
	public void playerUpdate(IPlayer player) {
		setText("Lives: " + player.getLives());
	}
}
