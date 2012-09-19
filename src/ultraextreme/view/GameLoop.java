package ultraextreme.view;

import org.andengine.engine.handler.IUpdateHandler;

import ultraextreme.model.IUltraExtremeModel;
import ultraextreme.model.ModelInput;

public class GameLoop implements IUpdateHandler {

	private IUltraExtremeModel gameModel;
	
	public GameLoop(IUltraExtremeModel gameModel) {
		this.gameModel = gameModel;
	}
	@Override
	public void onUpdate(float time) {
		gameModel.update(new ModelInput(1, 1, false, false), time);
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
