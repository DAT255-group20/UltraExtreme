package ultraextreme.controller;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ControllerEvent {

	private final AbstractController source;
	private final ControllerEventType eventType;

	public enum ControllerEventType {
		SWITCH_TO_GAME, SWITCH_TO_MENU, SWITCH_TO_HIGHSCORE;
	}

	public ControllerEvent(final AbstractController source,
			final ControllerEventType eventType) {
		this.source = source;
		this.eventType = eventType;
	}

	public AbstractController getSource() {
		return source;
	}

	public ControllerEventType getEventType() {
		return eventType;
	}
}