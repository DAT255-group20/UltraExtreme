/* ============================================================
 * Copyright 2012 Bjorn Persson Mattsson, Johan Gronvall, Daniel Jonsson,
 * Viktor Anderling
 *
 * This file is part of UltraExtreme.
 *
 * UltraExtreme is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * UltraExtreme is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UltraExtreme. If not, see <http://www.gnu.org/licenses/>.
 * ============================================================ */

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
 * 
 * @author Johan Gronvall
 * 
 */
public class HitAndRunEnemy extends AbstractEnemy {

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
	private float waitingTime = 6;

	private int speed = 400;

	protected HitAndRunEnemy(EnemyShip ship, AbstractWeapon weapon) {
		super(ship, weapon);
	}

	/**
	 * Constructor for a HitAndRunEnemy
	 * 
	 * @param shipPosition
	 *            where the enemy
	 * @param startingPoint
	 *            where the enemy will spawn
	 * @param firePoint
	 *            where the enemy will stop to shoot
	 * @param endPoint
	 *            where the enemy will run off to
	 */
	public HitAndRunEnemy(Position startingPosition, Position firePoint,
			Position endPoint) {
		super(new EnemyShip(startingPosition, 30, 30, 40,
				ObjectName.HITANDRUN_ENEMYSHIP), WeaponFactory.getInstance()
				.getNewWeapon(ObjectName.SPINNING_WEAPON));
		this.endPoint = endPoint;
		this.firePoint = firePoint;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getScoreValue() {
		return 50;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(float timePassed) {
		if (timeWaited == 0) {
			// while flying into position
			goTowardPosition(firePoint, timePassed);

		} else if (timeWaited > waitingTime) {
			// while retreating
			goTowardPosition(endPoint, timePassed);
		}

		if (this.getShip().getPositionClone().equals(firePoint)) {
			// while shooting
			timeWaited += timePassed;
			this.getWeapon().fire(this.getShip().getPositionClone(),
					PlayerID.ENEMY, new Rotation(0), timePassed);
		}
	}

	/**
	 * sends this enemy's ship towards the assigned Position restricted by the
	 * speed of the ship and the timePassed
	 * 
	 * @param goalPoint
	 *            Position this enemy is to move towards
	 * @param timePassed
	 *            time passed in seconds
	 */
	public void goTowardPosition(Position goalPoint, float timePassed) {
		// public for testing purposes
		Position position = this.getShip().getPositionClone();

		Vector2d directionVector = new Vector2d(goalPoint.getX()
				- position.getX(), goalPoint.getY() - position.getY());
		directionVector.normalize();

		// saves the old position
		Position prePos = new Position(position.getX(), position.getY());

		// moves the position
		position.setX(position.getX() + directionVector.x * speed * timePassed);
		position.setY(position.getY() + directionVector.y * speed * timePassed);

		if (wentPastPoint(prePos.getX(), position.getX(), goalPoint.getX())) {
			position.setX(goalPoint.getX());
		}
		if (wentPastPoint(prePos.getY(), position.getY(), goalPoint.getY())) {
			position.setY(goalPoint.getY());
		}
		this.getShip().setPosition(position);
	}

	private boolean wentPastPoint(double prePos, double newPos, double goalPos) {
		if (prePos > newPos) { // (came from right)
			return goalPos > newPos;
		} else {// if (prePos < newPos) { //(came from left)
			return goalPos < newPos;
		}
	}

	/**
	 * getter used in tests
	 * 
	 * @return the time this kind of enemy stays in place to shoot
	 */
	public float getWaitingTime() {
		return waitingTime;
	}
}