package ultraextreme.view;

import org.andengine.entity.text.Text;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import ultraextreme.model.IPlayer;
import ultraextreme.model.IPlayerListener;

public class ScoreText extends Text implements IPlayerListener  {

	public ScoreText(float x, float y, Font font, VertexBufferObjectManager vbo)
	{
		super(x, y, font, "No score yet", vbo);
	}

	@Override
	public void playerUpdate(IPlayer player) {
		setText("Score: " + player.getScore());
	}
}