package ultraextreme.model;

import java.util.List;

public class Ship extends DestroyableEntity {

	PlayerID playerID;
	ItemBar itemBar;
	
	@Override
	public List<Projectile> getProjectilesFired() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}
}
