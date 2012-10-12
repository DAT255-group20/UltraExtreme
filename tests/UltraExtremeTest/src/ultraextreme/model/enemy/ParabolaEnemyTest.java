package ultraextreme.model.enemy;

import junit.framework.TestCase;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

public class ParabolaEnemyTest extends TestCase {
	private ParabolaEnemy enemy;
	
	public void setUp() {
		Position startPos = new Position();
		Position endPos = new Position();
		enemy = new ParabolaEnemy(startPos, endPos, ObjectName.BASIC_WEAPON);
	}
	
	public void testUpdate() {
		
	}
}
