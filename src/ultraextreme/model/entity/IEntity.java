package ultraextreme.model.entity;

import ultraextreme.model.util.Position;

public interface IEntity {

	boolean collidesWith(IEntity other);

	Position getPosition();

	int getHeight();

	int getWidth();
}
