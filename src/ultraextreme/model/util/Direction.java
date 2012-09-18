package ultraextreme.model.util;

/**
 * This enumerator defines a direction in which an entity may move.
 * 
 * @author Viktor Anderling
 *
 */
public enum Direction {
	UP(1, 1),
	DOWN(-1, -1);
	
	private int xModifier;
	private int yModifier;
	
	private Direction(int xModifier, int yModifier) {
		this.xModifier = xModifier;
		this.yModifier = yModifier;
	}
	
	public int getXMod() {
		return xModifier;
	}
	
	public int getYMod() {
		return yModifier;
	}
}
