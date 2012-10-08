package ultraextreme.model.enemy;

import javax.vecmath.Vector2d;

import ultraextreme.model.entity.EnemyShip;
import ultraextreme.model.item.AbstractWeapon;
import ultraextreme.model.item.WeaponFactory;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.PlayerID;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;
/**
 * Class representing an enemy which flies in, attacks, and then retreats.
 * @author Johan Gronvall
 *
 */
public class HitAndRunEnemy extends AbstractEnemy {
	
	/**
	 * startingPoint where the enemy will spawn
	 */
	private Position startingPosition;
	
	/**
	 * endPoint where the enemy will run off to
	 */
	private Position endPoint;
	
	/**
	 * firePoint where the enemy will stop to shoot
	 */
	private Position firePoint;
	
	private float timeWaited = 0;
	
	/**
	 * for how long the enemy will be firing in seconds
	 */
	private float waitingTime = 5;
	
	private int speed = 10;
	
	protected HitAndRunEnemy(EnemyShip ship, AbstractWeapon weapon) {
		super(ship, weapon);
	}
	/**
	 * Constructor for a HitAndRunEnemy
	 * 
	 * @param shipPosition where the enemy 
	 * @param startingPoint where the enemy will spawn
	 * @param firePoint where the enemy will stop to shoot
	 * @param endPoint where the enemy will run off to
	 */
	public HitAndRunEnemy(Position startingPosition, Position firePoint,
			Position endPoint) {
		super(new EnemyShip(startingPosition, 30, 30, 5,
				ObjectName.HITANDRUN_ENEMY), WeaponFactory.getInstance().
				getNewWeapon(ObjectName.SPINNING_SPREAD_WEAPON));
		this.endPoint = endPoint;
		this.firePoint = firePoint;
		this.startingPosition = startingPosition;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getScoreValue() {
		return 100;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(float timePassed) {
		if (timeWaited == 0) {
			//while flying into position
			goTowardPosition(firePoint, timePassed);
			
		} else if(timeWaited > waitingTime) {
			//while retreating
			goTowardPosition(endPoint, timePassed);	
		}
		
		if(this.getShip().getPosition().equals(firePoint)) {
				//while shooting
			timeWaited += timePassed;
			this.getWeapon().fire(this.getShip().getPosition(),
						PlayerID.ENEMY, new Rotation(0), timePassed);
		}
	}
	
	/**
	 * sends this enemy's ship towards the assigned Position
	 * restricted by the speed of the ship and the timePassed 
	 * @param goalPoint Position this enemy is to move towards
	 * @param timePassed time passed in seconds
	 */
	public void goTowardPosition(Position goalPoint, float timePassed) {
		Position position = this.getShip().getPosition();
		Vector2d directionVector = new Vector2d(
				position.getX()-goalPoint.getX(),
				position.getY()-goalPoint.getY());
		
		directionVector.normalize();
		position.setX(directionVector.x*speed*timePassed);
		position.setY(directionVector.y*speed*timePassed);
	}
}