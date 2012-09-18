package ultraextreme.model.util;

/**
 * This class defines constants in the model
 * such as fixed dimensions etc.
 * 
 * @author Viktor Anderling
 *
 */
public class Constants {

	private Constants INSTANCE;
	
	private Constants() {
		INSTANCE = this;
	}
	
	/**
	 * @return This instance.
	 */
	public Constants getInstance() {
		if(INSTANCE == null) {
			return new Constants();
		} else {
			return INSTANCE;
		}
	}
	
	/**
	 * @return The relative dimension of a arbitrary level.
	 */
	public Dimension getLevelDimension() {
		return new Dimension(900, 1600);		
	}
	
}
