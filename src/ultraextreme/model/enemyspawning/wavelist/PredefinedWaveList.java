package ultraextreme.model.enemyspawning.wavelist;

import java.util.ArrayList;
import java.util.List;

import ultraextreme.model.enemyspawning.wave.HorizontalLineWave;
import ultraextreme.model.enemyspawning.wave.VWave;
import ultraextreme.model.enemyspawning.wave.VerticalLineWave;
import ultraextreme.model.enemyspawning.wave.AbstractWave;
import ultraextreme.model.item.BulletManager;

/**
 * This wave list consists of a list of predefined enemy waves.
 * 
 * @author Daniel Jonsson
 * 
 */
public class PredefinedWaveList extends AbstractWaveList {

	private final List<AbstractWave> waves;
	private final List<Float> spawningTimes;

	/**
	 * Create a wave list with predefined waves.
	 * 
	 * @param bulletManager
	 *            Reference to a BulletManager that the enemies will add their
	 *            bullets to.
	 */
	public PredefinedWaveList(final BulletManager bulletManager) {
		super(5);
		this.waves = new ArrayList<AbstractWave>();
		this.spawningTimes = new ArrayList<Float>();
		add(1.0f, new VerticalLineWave(2, 0, 200, -10, bulletManager));
		add(1.0f, new HorizontalLineWave(5, 3, Math.PI / 8, 400, -10,
				bulletManager));
		add(5.0f, new VerticalLineWave(5, Math.PI / 8, 100, -10, bulletManager));
		add(5.0f, new VerticalLineWave(10, -Math.PI / 2, -100, 400,
				bulletManager));
		add(10.0f, new VWave(0, 200, -10, bulletManager));
	}

	/**
	 * Add a new wave and spawning time.
	 * 
	 * @param spawningTime
	 * @param wave
	 */
	private void add(final float spawningTime, final AbstractWave wave) {
		this.waves.add(wave);
		this.spawningTimes.add(spawningTime);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractWave getCurrentWave() {
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
