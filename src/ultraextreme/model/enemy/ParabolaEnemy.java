package ultraextreme.model.enemy;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;
/**
 * Class representing an enemy which moves in a parabola-like pattern
 * this is simulated by making the enemy move in an elliptic pattern
 * 
 * @author Johan Gronvall
 */
public class ParabolaEnemy extends AbstractEnemy {
	private double angle;
	private Position endPosition;
	private Position startingPosition;
	private float angleSpeed;
	
	
	protected ParabolaEnemy(EnemyShip ship, AbstractWeapon weapon) {
		super(ship, weapon);
	}
	
	/**
	 * Creates a new ParabolaEnemy
	 * OBS: endPosition should be placed outside screen so that the enemy gets removed
	 * @param startingPos where the ParabolaEnemy will appear
	 * @param endPosition where the ParabolaEnemy will move towards
	 * @param weaponName what kind of weapon this enemy will wield
	 */
	public ParabolaEnemy(Position startingPosition, Position endPosition, ObjectName weaponName) {
		super(new EnemyShip(startingPosition, 10, 10, 15, weaponName),
				WeaponFactory.getInstance().getNewWeapon(weaponName));
		
		this.endPosition = endPosition;
		this.startingPosition = startingPosition;
		
		this.angleSpeed = 30f;
		
		
		
		this.angle = 3*Math.PI/4;
		double startX = startingPosition.getX();
		double startY = startingPosition.getY();
		double endX = endPosition.getX();
		double endY = endPosition.getY();
		
		//set startingAngle and angleSpeed depending on
		//what positions were given to simulate the movement of
		//a semi-ellipse
		if (startX < endX && startY < endY) {
			this.angle = Math.PI;
			
		}else if (startX < endX && startY > endY) {
			this.angle = Math.PI*3/4;
			
		}else if (startX > endX && startY < endY) {
			angleSpeed*=-1;
			this.angle=0;
			
		}else if (startX > endX && startY > endY) {
			this.angle=Math.PI*3/4;
			this.angleSpeed*=-1;
		}
	}

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getScoreValue() {
		return 40;
	}

	
	/**
	 * update called to make this enemy move in the parabola like pattern
	 * aswell as make it fire at a set interval
	 */
	@Override
	public void update(float timePassed) {
		angle += angleSpeed*timePassed;
		Position newPosition = this.getShip().getPositionClone();
		newPosition.setX(startingPosition.getX()*Math.cos(angle));
		newPosition.setY(endPosition.getY()*Math.sin(angle));
		this.getShip().setPosition(newPosition);
		this.getWeapon().fire(this.getShip().getCenteredPositionClone(), PlayerID.ENEMY, new Rotation(0), timePassed);
	}
}
