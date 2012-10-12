package ultraextreme.model.enemy;

import junit.framework.TestCase;
import ultraextreme.model.util.ObjectName;
import ultraextreme.model.util.Position;

public class ParabolaEnemyTest extends TestCase {
	private ParabolaEnemy enemy;
	
	public void setUp() {
		Position startPos = new Position(200, 0);
		Position endPos = new Position(0, 150);
		enemy = new ParabolaEnemy(startPos, endPos, ObjectName.BASIC_WEAPON);
	}
	
	public void testUpdate() {
		
		for(int i = 0; i < 10000; i++) {
			
		}
	}
}
