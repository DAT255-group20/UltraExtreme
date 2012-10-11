package ultraextreme.model.item;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Viktor Anderling
 */
public class SpreadWeaponTest extends TestCase {

	BulletManager bulletManager;
	BasicSpreadWeapon spreadWeapon;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		this.resetInstanceVariables();
	}

	private void resetInstanceVariables() {
		bulletManager = new BulletManager();
		spreadWeapon = new BasicSpreadWeapon(bulletManager);
	}

	@Test
	public void testFire() {
		fail("Not yet implemented");
	}

	@Test
	public void testShallowClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testBasicSpreadWeapon() {
		fail("Not yet implemented");
	}

}
