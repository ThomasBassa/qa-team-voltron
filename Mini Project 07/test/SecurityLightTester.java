package test;

import static org.junit.Assert.*;

import javax.swing.JFrame;

import org.junit.Before;
import org.junit.Test;

import SecurityLightController.*;
import UI.SecurityLampSimulatedUI;


/** A test suite for the security light logic.
 * @author Thomas Bassa
 * @author Greg Carkin
 * @author Umar Idris
 * @author Michael Philotoff */
public class SecurityLightTester {

	/** A state machine that controls the state of a light. This is the class under test. */
	private LightControllerStateMachine light;

	/** An implementation of the state machine's observer interface, which allows us to
	 * actually observe the changes in the state machine. This is an inner class found at
	 * the end of this test suite. */
	private LightStateObserver obs;

	/** Used to create a brand new state machine and observer before executing any test.
	 * @throws Exception if something bad happens, hopefully not */
	@Before
	public void setUp() throws Exception {
		light = new LightControllerStateMachine();
		//TODO call setLight?
		//TODO call setTmr?
		obs = new LightStateObserver();
		light.subscribe(obs);
		
		JFrame frame = new JFrame("Security Light Controller GUI");
		SecurityLampSimulatedUI cwrb1 = new SecurityLampSimulatedUI(light);
		frame.setContentPane(cwrb1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(300, 0, 20, 10);
		frame.pack();
		frame.setVisible(true);

		light.setLight(cwrb1);
		light.setTmr(new LightTimer(light));
	}

	//Let's write some tests!
	@Test
	public void initializationTest() {
		//The observer should get a valid state after the subscribe in setUp
		obs.assertValidState();
		//Light should be in LAMP_OFF_DAYLIGHT state
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
	}
	
	
	/**
	 * Test to check state transition from manual off to manual on
	 * @author Greg
	 */
	@Test
	public void testManuallight() {
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_ON);
		assertEquals(light.getCurrentState(), 2, 0);
		
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_OFF);
		assertEquals(light.getCurrentState(), 1, 0);
	}
	
	
	/**
	 * Test to check state transition from manual off to manual on
	 * to darkened back to lightened
	 * @author Greg
	 */
	@Test
	public void testManualLightToDark() {
		assertEquals(light.getCurrentState(), 1, 0);
		
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_ON);
		assertEquals(light.getCurrentState(), 2, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		assertEquals(light.getCurrentState(), 8, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_LIGHTENED);
		assertEquals(light.getCurrentState(), 2, 0);
		
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_OFF);
		assertEquals(light.getCurrentState(), 1, 0);
	}
	
	/**
	 * Test to check state transition from day to night back to 
	 * day
	 * @author Greg
	 */
	@Test
	public void testDayToNight() {
		assertEquals(light.getCurrentState(), 1, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		assertEquals(light.getCurrentState(), 4, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_LIGHTENED);
		assertEquals(light.getCurrentState(), 1, 0);
	}
	
	/**
	 * Test to check state transition from day to night then manual 
	 * on, manual off back to day
	 * @author Greg
	 */
	@Test
	public void testManualDayToNight() {
		assertEquals(light.getCurrentState(), 1, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		assertEquals(light.getCurrentState(), 4, 0);
		
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_ON);
		assertEquals(light.getCurrentState(), 8, 0);
		
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_OFF);
		assertEquals(light.getCurrentState(), 4, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_LIGHTENED);
		assertEquals(light.getCurrentState(), 1, 0);
	}
	
	/**
	 * Test to check state transition from day to night then alarm tripped
	 * then alarm is cleared and goes back to night.
	 * @author Greg
	 */
	@Test
	public void testAlarm() {
		assertEquals(light.getCurrentState(), 1, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		assertEquals(light.getCurrentState(), 4, 0);
		
		light.signalAction(LightControllerCommandInterface.SECURITY_ALARM_TRIPPED);
		assertEquals(light.getCurrentState(), 32, 0);
		
		light.signalAction(LightControllerCommandInterface.LAMP_TIMER_EXPIRED);
		assertEquals(light.getCurrentState(), 32, 0);
		
		light.signalAction(LightControllerCommandInterface.LAMP_TIMER_EXPIRED);
		assertEquals(light.getCurrentState(), 32, 0);
		
		light.signalAction(LightControllerCommandInterface.ALARM_CLEARED);
		assertEquals(light.getCurrentState(), 4, 0);
		
	}
	
	/**
	 * Test to check state transition from day to night then motion is detected.
	 * Light timer expires then motion is detected again. The security alarm is tripped
	 * then the alarm is cleared.
	 * @author Greg
	 */
	@Test
	public void testMotionDetected() {
		assertEquals(light.getCurrentState(), 1, 0);
		
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		assertEquals(light.getCurrentState(), 4, 0);
		
		light.signalAction(LightControllerCommandInterface.MOTION_DETECTED);
		assertEquals(light.getCurrentState(), 16, 0);
		
		light.signalAction(LightControllerCommandInterface.LAMP_TIMER_EXPIRED);
		assertEquals(light.getCurrentState(), 4, 0);
		
		light.signalAction(LightControllerCommandInterface.MOTION_DETECTED);
		assertEquals(light.getCurrentState(), 16, 0);
		
		light.signalAction(LightControllerCommandInterface.SECURITY_ALARM_TRIPPED);
		assertEquals(light.getCurrentState(), 32, 0);
		
		light.signalAction(LightControllerCommandInterface.ALARM_CLEARED);
		assertEquals(light.getCurrentState(), 4, 0);

		
	}

	/** An implementation of the state machine's observer interface, which allows us to
	 * actually observe the changes in the state machine. */
	private class LightStateObserver implements
			LightControllerStateMachineObserverInterface {

		/** The last observed state. Initialized to an invalid value. */
		private int state = -54321;

		/** The set of valid states, as defined in
		 * {@link LightControllerStateMachineObserverInterface} */
		private int[] validStates = {LAMP_OFF_DAYLIGHT,
				LAMP_ON_FULL_BRIGHTNESS, LAMP_OFF_NIGHTIME,
				LAMP_ON_NIGHTIME_BRIGHTNESS, MOTION_DETECTED,
				INTRUSION_DETECTED};

		/** Updates the state of the observed light, and checks that the new state is valid
		 * using a JUnit assertion.
		 *  @see LightControllerStateMachineObserverInterface#updateLightState(int) */
		@Override
		public void updateLightState(int newState) {
			state = newState;
			assertValidState();
		}

		/** Check whether the current state of this observer is valid using JUnit assertions.*/
		public void assertValidState() {
			boolean isValid = false;
			for(int validState : validStates) {
				if(state == validState) isValid = true;
			}
			assertTrue(isValid);
		}

		/** A simple getter for the last seen state. 
		 * @return the last observed state*/
		public int getState() {
			return state;
		}

		/** Convienience method for testing the last observed state with a JUnit assertion */
		public void assertStateEquals(int expectedState) {
			assertEquals(expectedState, state);
		}
	}
}
