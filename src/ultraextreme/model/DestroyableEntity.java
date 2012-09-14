package ultraextreme.model;

import java.util.List;

public abstract class DestroyableEntity extends Entity {

	public abstract List<Projectile> getProjectilesFired();
	
	public abstract boolean isDestroyed();
}
