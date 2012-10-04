package ultraextreme.view;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IPlayer;
import ultraextreme.model.IPlayerListener;
import ultraextreme.model.util.Position;
import ultraextreme.util.Resources;
import ultraextreme.util.Resources.ResourceName;

public class ScoreText extends Text implements IPlayerListener {

	public ScoreText(Position textPos, Font font, VertexBufferObjectManager vbo) {
		// Creates with empty characters to "allocate" memory for the object
		// According to
		// https://sites.google.com/site/matimdevelopment/creating-text
		super((float) textPos.getX(), (float) textPos.getY(), font, Resources
				.getInstance().getResource(ResourceName.SCORE) + " 0       ",
				vbo);
	}

	@Override
	public void playerUpdate(IPlayer player) {
		setText(Resources.getInstance().getResource(ResourceName.SCORE) + " "
				+ +player.getScore());
	}
}