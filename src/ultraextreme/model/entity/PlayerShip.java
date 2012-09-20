package ultraextreme.model.entity;

/**
 * The player's ship.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class PlayerShip extends DestroyableEntity {

	public PlayerShip(double x, double y, int width, int height) {
		super(x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	public PlayerShip() {
		super(0, 0, 50, 50);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
