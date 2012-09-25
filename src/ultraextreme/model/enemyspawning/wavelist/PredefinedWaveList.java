package ultraextreme.model.enemyspawning.wavelist;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemyspawning.wave.Wave;
import ultraextreme.model.item.BulletManager;

public class PredefinedWaveList extends AbstractWaveList {

	private List<Wave> waves;
	private List<Float> spawningTimes;

	/**
	 * Create a wave list with predefined waves.
	 */
	public PredefinedWaveList(BulletManager bulletManager) {
		super(4);
		this.waves = new ArrayList<Wave>();
		this.spawningTimes = new ArrayList<Float>();
		// Some example values
		add(1.0f, new Wave(bulletManager));
		add(13.0f, new Wave(bulletManager));
		add(25.0f, new Wave(bulletManager));
		add(39.0f, new Wave(bulletManager));
//		add(1.0f, WaveFactory.getWave());
//		add(3.0f, WaveFactory.getWave());
//		add(5.0f, WaveFactory.getWave());
//		add(9.0f, WaveFactory.getWave());
	}

	/**
	 * Add a new wave and spawning time.
	 * 
	 * @param spawningTime
	 * @param wave
	 */
	private void add(float spawningTime, Wave wave) {
		this.waves.add(wave);
		this.spawningTimes.add(spawningTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Wave getCurrentWave() {
		return this.waves.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public float getCurrentSpawningTime() {
		return this.spawningTimes.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void next() {
		super.next();
		this.waves.remove(0);
		this.spawningTimes.remove(0);
	}
}
