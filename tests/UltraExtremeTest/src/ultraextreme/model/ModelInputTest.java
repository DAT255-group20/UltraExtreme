package ultraextreme.model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ModelInputTest {

	@Test
	public void testModelInput() {
		double dX1 = 34.324;
		double dX2 = 62.135;
		double dY1 = 8974.12;
		double dY2 = 213.54;
		boolean fireWeapons1 = true;
		boolean fireWeapons2 = false;
		boolean dropBomb1 = false;
		boolean dropBomb2 = true;

		ModelInput input = new ModelInput(dX1, dY1, fireWeapons1, dropBomb1);

		assertTrue(input.dX == dX1);
		assertTrue(input.dY == dY1);
		assertTrue(input.fireWeapons == fireWeapons1);
		assertTrue(input.dropBomb == dropBomb1);

		input = new ModelInput(dX2, dY2, fireWeapons2, dropBomb2);

		assertFalse(input.dX == dX1);
		assertFalse(input.dY == dY1);
		assertFalse(input.fireWeapons == fireWeapons1);
		assertFalse(input.dropBomb == dropBomb1);

		assertTrue(input.dX == dX2);
		assertTrue(input.dY == dY2);
		assertTrue(input.fireWeapons == fireWeapons2);
		assertTrue(input.dropBomb == dropBomb2);
	}
}