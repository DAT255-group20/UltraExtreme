package ultraextreme.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import org.andengine.entity.scene.Scene;
import org.junit.Test;

import ultraextreme.controller.ControllerEvent.ControllerEventType;

/**
 * 
 * @author Bjorn Persson Mattsson
 * 
 */
public class ControllerEventTest {

	@Test
	public void testControllerEvent() {
		AbstractController testController = new AbstractController() {

			@Override
			public Scene getScene() {
				return null;
			}

			@Override
			public void activateController() {
			}

			@Override
			public void deactivateController() {
			}
		};
		ControllerEvent event = new ControllerEvent(null, null);
		assertNull(event.getSource());
		assertNull(event.getEventType());

		event = new ControllerEvent(testController,
				ControllerEventType.SWITCH_TO_GAME);
		assertEquals(testController, event.getSource());
		assertEquals(ControllerEventType.SWITCH_TO_GAME, event.getEventType());
		assertNotSame(ControllerEventType.SWITCH_TO_HIGHSCORE,
				event.getEventType());

		event = new ControllerEvent(null, ControllerEventType.SWITCH_TO_MENU);
		assertNull(event.getSource());
		assertEquals(ControllerEventType.SWITCH_TO_MENU, event.getEventType());
		assertNotSame(ControllerEventType.SWITCH_TO_GAME, event.getEventType());
	}
}