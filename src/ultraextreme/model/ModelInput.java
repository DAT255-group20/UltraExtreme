package ultraextreme.model;

/**
 * A container class for all input information that goes into the model.
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ModelInput {

	/** The player movement change on the x axis. */
	public final double dX;

	/** The player movement change on the y axis. */
	public final double dY;

	/** True if and only if the player is firing his weapons. */
	public final boolean fireWeapons;

	/** True if and only if the player is dropping a bomb. */
	public final boolean dropBomb;

	/**
	 * Creates a new immutable ModelInput.
	 * 
	 * @param dX
	 *            The player movement change on the x axis
	 * @param dY
	 *            The player movement change on the y axis
	 * @param fireWeapons
	 *            True if and only if the player is firing his weapons
	 * @param dropBomb
	 *            True if and only if the player is dropping a bomb
	 */
	public ModelInput(final double dX, final double dY,
			final boolean fireWeapons, final boolean dropBomb) {
		this.dX = dX;
		this.dY = dY;
		this.fireWeapons = fireWeapons;
		this.dropBomb = dropBomb;
	}
}