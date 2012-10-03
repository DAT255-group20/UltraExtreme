package ultraextreme.model.item;

/**
 * A class that wants to know when an ItemBar has been updated/changed needs to
 * implement this add add itself ass a listener to the ItemBar.
 * 
 * @author Daniel Jonsson
 * 
 */
public interface ItemBarUpdatedListener {

	/**
	 * An item bar has been updated.
	 * 
	 * @param itemBar
	 *            The item bar that has been updated.
	 */
	public void updatedItemBar(ItemBar itemBar);
}
