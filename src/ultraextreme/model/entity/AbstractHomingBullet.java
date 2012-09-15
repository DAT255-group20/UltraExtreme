package ultraextreme.model.entity;

public abstract class AbstractHomingBullet extends AbstractBullet {

	protected Entity target;

	public void setTarget(Entity target)
	{
		this.target = target;
	}
}