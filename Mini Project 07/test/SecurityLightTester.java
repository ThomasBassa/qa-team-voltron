package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import SecurityLightController.*;


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

	/** An implementation of the light device interface, seen in an inner class at the end
	 * of this suite. */
	private Lamp lamp;

	/** Used to create a brand new state machine, observer, and light before executing any
	 * test. The conditions expected after this occurs are tested in
	 * {@link #initializationTest()}.
	 * @throws Exception if something bad happens, hopefully not */
	@Before
	public void setUp() throws Exception {
		light = new LightControllerStateMachine();
		obs = new LightStateObserver();
		lamp = new Lamp();

		light.setLight(lamp);
		light.setTmr(new LightTimer(light));
		light.subscribe(obs);
	}

	/** A test to ensure that the state machine and its observer started correctly after
	 * {@link #setUp()} is called. The state machine should be in the off + day state, and
	 * its controlled light should be off. If this test fails, the starting state for the
	 * other tests may not be correct.
	 * @author Thomas Bassa */
	@Test
	public void initializationTest() {
		//Machine should be in LAMP_OFF_DAYLIGHT state
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
		//Light should be OFF
		lamp.assertStateEquals(Lamp.LampState.OFF);
	}


	/** Test to check state transition from manual off to manual on
	 * @author Greg */
	@Test
	public void testManualLight() {
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_ON);
		obs.assertStateEquals(LightStateObserver.LAMP_ON_FULL_BRIGHTNESS);

		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_OFF);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
	}


	/** Test to check state transition from manual off to manual on to darkened back to
	 * lightened
	 * @author Greg */
	@Test
	public void testManualLightToDark() {
		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_ON);
		obs.assertStateEquals(LightStateObserver.LAMP_ON_FULL_BRIGHTNESS);

		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		obs.assertStateEquals(LightStateObserver.LAMP_ON_NIGHTIME_BRIGHTNESS);

		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_LIGHTENED);
		obs.assertStateEquals(LightStateObserver.LAMP_ON_FULL_BRIGHTNESS);

		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_OFF);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
	}

	/** Test to check state transition from day to night back to day
	 * @author Greg */
	@Test
	public void testDayToNight() {
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);

		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_LIGHTENED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
	}

	/** Test to check state transition from day to night then manual on, manual off back to
	 * day
	 * @author Greg */
	@Test
	public void testManualDayToNight() {
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);

		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_ON);
		obs.assertStateEquals(LightStateObserver.LAMP_ON_NIGHTIME_BRIGHTNESS);

		light.signalAction(LightControllerCommandInterface.MANUAL_SWITCH_OFF);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);

		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_LIGHTENED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
	}

	/** Test to check state transition from day to night then alarm tripped then alarm is
	 * cleared and goes back to night.
	 * @author Greg */
	@Test
	public void testAlarm() {
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);

		light.signalAction(LightControllerCommandInterface.SECURITY_ALARM_TRIPPED);
		obs.assertStateEquals(LightStateObserver.INTRUSION_DETECTED);

		light.signalAction(LightControllerCommandInterface.LAMP_TIMER_EXPIRED);
		obs.assertStateEquals(LightStateObserver.INTRUSION_DETECTED);

		light.signalAction(LightControllerCommandInterface.LAMP_TIMER_EXPIRED);
		obs.assertStateEquals(LightStateObserver.INTRUSION_DETECTED);

		light.signalAction(LightControllerCommandInterface.ALARM_CLEARED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);
	}

	/** Test to check state transition from day to night then motion is detected. Light
	 * timer expires then motion is detected again. The security alarm is tripped then the
	 * alarm is cleared.
	 * @author Greg */
	@Test
	public void testMotionDetected() {
		light.signalAction(LightControllerCommandInterface.LIGHT_SENSOR_DARKENED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);

		light.signalAction(LightControllerCommandInterface.MOTION_DETECTED);
		obs.assertStateEquals(LightStateObserver.MOTION_DETECTED);

		light.signalAction(LightControllerCommandInterface.LAMP_TIMER_EXPIRED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);

		light.signalAction(LightControllerCommandInterface.MOTION_DETECTED);
		obs.assertStateEquals(LightStateObserver.MOTION_DETECTED);

		light.signalAction(LightControllerCommandInterface.SECURITY_ALARM_TRIPPED);
		obs.assertStateEquals(LightStateObserver.INTRUSION_DETECTED);

		light.signalAction(LightControllerCommandInterface.ALARM_CLEARED);
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_NIGHTIME);
	}

	/** An implementation of the state machine's observer interface, which allows us to
	 * actually observe the changes in the state machine. Also provides some useful
	 * methods to perform tests on the state. */
	private static class LightStateObserver implements
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
		 * @see LightControllerStateMachineObserverInterface#updateLightState(int) */
		@Override
		public void updateLightState(int newState) {
			state = newState;
			assertValidState();
		}

		/** Check whether the current state of this observer is valid using JUnit
		 * assertions. */
		public void assertValidState() {
			boolean isValid = false;
			for(int validState : validStates) {
				if(state == validState) isValid = true;
			}
			if(!isValid) {
				fail("Invalid observed state! Equals: " + state);
			}
		}

		/** A simple getter for the last seen state.
		 * @return the last observed state */
		public int getState() {
			return state;
		}

		/** Convienience method for testing the last observed state with a JUnit assertion */
		public void assertStateEquals(int expectedState) {
			assertEquals(expectedState, state);
		}
	}

	/** An implementation of the light device interface, which is controlled by the state
	 * machine. Provides methods to test the state of the light itself. */
	private static class Lamp implements LightDeviceInterface {

		/** An enumeration of possible lamp states. */
		public enum LampState {
			OFF, ON, NIGHT, INVALID;
		}

		/** The current lamp state, which is invalid on creation. */
		private LampState state = LampState.INVALID;

		@Override
		public void turnLightOff() {
			state = LampState.OFF;
		}

		@Override
		public void turnLightOnFullBrightness() {
			state = LampState.ON;
		}

		@Override
		public void turnLightOnNightimeBrightness() {
			state = LampState.NIGHT;
		}

		/** Simple getter for the Lamp's state. */
		public LampState getState() {
			return state;
		}

		/** Convienience method to test the Lamp's state using a JUnit assertion. */
		public void assertStateEquals(LampState expected) {
			assertEquals(expected, state);
		}
	}
}
