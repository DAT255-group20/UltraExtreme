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

package ultraextreme.model.enemyspawning.wavelist;

import java.security.SecureRandom;
import java.util.Random;

import ultraextreme.model.enemy.AbstractEnemy;
import ultraextreme.model.enemy.BasicEnemy;
import ultraextreme.model.enemy.HitAndRunEnemy;
import ultraextreme.model.enemy.ParabolaEnemy;
import ultraextreme.model.enemyspawning.wave.AbstractEnemyProvider;
import ultraextreme.model.enemyspawning.wave.AbstractWave;
import ultraextreme.model.enemyspawning.wave.RectangleWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.util.Constants;
import ultraextreme.model.util.Difficulty;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;
import ultraextreme.model.util.Rotation;

public class RandomWaveList extends AbstractWaveList {

	/**
	 * How much the difficulty will be increased for each wave. Should be a
	 * number greater than 1.
	 */
	private double difficultyRiseSpeed;

	/**
	 * Modifies the duration between waves. Higher value means higher
	 * difficulty.
	 */
	private double currentDifficultyMod;

	private AbstractWave currentWave;

	private float currentSpawningTime;

	private AbstractRandomGenerator randomGenerator;

	private int screenWidth;

	/**
	 * Create a wave list that returns random waves with random spawn times, and
	 * with normal difficulty.
	 */
	public RandomWaveList() {
		this(Difficulty.NORMAL);
	}

	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param difficulty
	 *            Difficulty of the spawning waves
	 */
	public RandomWaveList(final Difficulty difficulty) {
		this(difficulty, new AbstractRandomGenerator() {
			@Override
			public float nextFloat() {
				// Using SecureRandom instead of Random because Random was
				// notably not random enough. I'm not sure, but it may differ
				// between different devices.
				final Random random = new SecureRandom();
				return random.nextFloat();
			}
		});
	}

	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param difficulty
	 *            Difficulty of the spawning waves
	 * @param randomGenerator
	 *            A Class that implements RandomGenerator, which will feed this
	 *            class with random numbers.
	 */
	public RandomWaveList(final Difficulty difficulty,
			final AbstractRandomGenerator randomGenerator) {
		scaleToDifficulty(difficulty);
		this.currentDifficultyMod = 1;
		this.screenWidth = (int) Constants.getLevelDimension().getX();
		this.randomGenerator = randomGenerator;
		this.generateNewWave();
		this.currentSpawningTime = 0;
	}

	private void scaleToDifficulty(Difficulty difficulty) {
		switch (difficulty) {
		case NORMAL:
			difficultyRiseSpeed = 0.02;
			break;

		case HARD:
			difficultyRiseSpeed = 0.04;
			break;

		case EXTREME:
			difficultyRiseSpeed = 0.07;
			break;

		case ULTRAEXTREME:
			difficultyRiseSpeed = 0.1;
			break;

		default:
			difficultyRiseSpeed = 0;
		}
	}

	/**
	 * Update currentWave with a new random wave.
	 */
	private void generateNewWave() {
		// Randomize which wave will spawn
		int wave = (int) (randomGenerator.nextFloat() * 4);
		switch (wave) {
		/**
		 * VWave spawned with some randomness along the x axis
		 */
		case 0:
			float x;
			x = (randomGenerator.nextFloat() * (screenWidth - 700)) + 350;
			currentWave = new VWave(0, (int) x, -100);
			break;
		/**
		 * A horizontal line of 3 HitAndRunEnemies that fly to the middle of the
		 * screen and then back
		 */
		case 1:
			currentWave = new RectangleWave(3, 1, 0, 300, -100,
					new AbstractEnemyProvider() {
						// Counter so the enemies will fly to different
						// positions
						private int counter = 0;

						@Override
						public AbstractEnemy getEnemy(
								Position spawningPosition, Rotation rotation) {
							Position firePoint = new Position(
									250 + counter * 200, 800);
							Position endPoint = new Position(450, -1000);
							++counter;
							counter %= 3;
							return new HitAndRunEnemy(spawningPosition,
									firePoint, endPoint,
									ObjectName.SPINNING_WEAPON);
						}
					});
			break;

		/**
		 * Vertical line of 3 enemies with a slight randomized rotation and
		 * position
		 */
		case 2:
			float rotation = (randomGenerator.nextFloat() * 6 - 3) / 8;
			x = 450 + (randomGenerator.nextFloat() * 800 - 400);
			currentWave = new RectangleWave(1, 3, rotation, (int) x, -100,
					new AbstractEnemyProvider() {
						@Override
						public AbstractEnemy getEnemy(
								Position spawningPosition, Rotation rotation) {
							return new BasicEnemy(spawningPosition, rotation,
									ObjectName.BASIC_WEAPON);
						}
					});
			break;

		/**
		 * 3 parabola enemies with spread weapons that spawns one after another
		 */
		case 3:
			currentWave = new RectangleWave(1, 3, 0, 100, -100,
					new AbstractEnemyProvider() {
						@Override
						public AbstractEnemy getEnemy(
								Position spawningPosition, Rotation rotation) {
							return new ParabolaEnemy(spawningPosition,
									new Position(400, 400), new Position(900,
											-400), ObjectName.SPREAD_WEAPON);
						}
					});
			break;

		default:
			break;
		}
	}

	/**
	 * Update the currentSpawningTime with a random number.
	 */
	private void generateNewSpawningTime() {
		currentSpawningTime += (randomGenerator.nextFloat() * 2 + 10)
				/ currentDifficultyMod;
		currentDifficultyMod = currentDifficultyMod + difficultyRiseSpeed;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		super.next();
		this.generateNewWave();
		this.generateNewSpawningTime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractWave getCurrentWave() {
		return currentWave;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getCurrentSpawningTime() {
		return currentSpawningTime;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasNext() {
		return true; // Always returning true since the wave list is endless
	}
}
