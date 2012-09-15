package ultraextreme.model.entity;

import java.util.List;


public abstract class DestroyableEntity extends Entity {

	public abstract List<AbstractBullet> getProjectilesFired();
	
	public abstract boolean isDestroyed();
}
