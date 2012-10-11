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

import java.util.Random;

import ultraextreme.model.enemyspawning.wave.AbstractWave;
import ultraextreme.model.enemyspawning.wave.HorizontalLineWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.enemyspawning.wave.VerticalLineWave;
import ultraextreme.model.item.BulletManager;
import ultraextreme.model.util.Constants;
import android.util.Log;

/**
 * This wave list return a specified number of random enemy waves.
 * 
 * @author Daniel Jonsson
 * 
 */
public class RandomWaveList extends AbstractWaveList {

	private AbstractWave currentWave;

	private float currentSpawningTime;

	private BulletManager bulletManager;

	private int counter;

	private AbstractRandomGenerator randomGenerator;
	
	private int screenWidth;

	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param numberOfWaves
	 *            Maximum number of waves this will return.
	 * @param bulletManager
	 *            Reference to a BulletManager that the enemies will add their
	 *            bullets to.
	 */
	public RandomWaveList(final int numberOfWaves,
			final BulletManager bulletManager) {
		this(numberOfWaves, bulletManager, new AbstractRandomGenerator() {
			@Override
			public float nextFloat() {
				final Random random = new Random();
				return random.nextFloat();
			}
		});
	}

	/**
	 * Create a wave list that returns random waves with random spawn times.
	 * 
	 * @param numberOfWaves
	 *            Maximum number of waves this will return.
	 * @param bulletManager
	 *            Reference to a BulletManager that the enemies will add their
	 *            bullets to.
	 * @param randomGenerator
	 *            A Class that implements RandomGenerator, which will feed this
	 *            class with random numbers.
	 */
	public RandomWaveList(final int numberOfWaves,
			final BulletManager bulletManager,
			final AbstractRandomGenerator randomGenerator) {
		super(numberOfWaves);
		this.screenWidth = (int) Constants.getLevelDimension().getX();
		this.randomGenerator = randomGenerator;
		this.counter = 0;
		this.bulletManager = bulletManager;
		this.generateNewWave();
		this.currentSpawningTime = 0;
	}

	/**
	 * Update currentWave with a new random wave.
	 */
	private void generateNewWave() {
		counter %= 3;
		switch (counter) {
		case 0:
			float x;
			x = (randomGenerator.nextFloat() * (screenWidth - 740)) + 370;
			Log.e("kalle", "" + x);
			currentWave = new VWave(0, (int)x, -100, bulletManager);
			break;
		case 1:
			currentWave = new HorizontalLineWave(1, 3, Math.PI / 8, 400, -100,
					bulletManager);
			break;
		case 2:
			currentWave = new VerticalLineWave(2, 0, 200, -100, bulletManager);
			break;
		default:
			break;
		}
		counter++;
	}

	/**
	 * Update the currentSpawningTime with a random number.
	 */
	private void generateNewSpawningTime() {
		currentSpawningTime += randomGenerator.nextFloat() * 2 + 3.5;
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
}
