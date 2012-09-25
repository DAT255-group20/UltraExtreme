package ultraextreme.model.util;

import javax.vecmath.Vector2d;
import junit.framework.TestCase;

public class DirectionTest extends TestCase {

	Rotation rotation;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testGetRotatedCoordinates() {
		rotation = new Rotation(0);
		Vector2d vec = rotation.getRotatedCoordinates(1, 1);
		assertTrue(vec.x == 1 && vec.y == 1);
		
		double epsilon = 0.0000000000001;
		
		rotation = new Rotation(Math.PI);
		vec = rotation.getRotatedCoordinates(1, 1);
		assertTrue(-1 - vec.x < epsilon && -1 - vec.y < epsilon);
		
		rotation = new Rotation(Math.PI/2);
		vec = rotation.getRotatedCoordinates(1, 1);
		assertTrue(-1 - vec.x < epsilon && 1 - vec.y < epsilon);	
	}
}
