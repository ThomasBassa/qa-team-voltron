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

	/** Used to create a brand new state machine and observer before executing any test.
	 * @throws Exception if something bad happens, hopefully not */
	@Before
	public void setUp() throws Exception {
		light = new LightControllerStateMachine();
		//TODO call setLight?
		//TODO call setTmr?
		obs = new LightStateObserver();
		light.subscribe(obs);
	}

	/** A test to ensure that the state machine and its observer started correctly after
	 * {@link #setUp()} is called. */
	@Test
	public void initializationTest() {
		//The observer should get a valid state after the subscribe in setUp
		obs.assertValidState();
		//Light should be in LAMP_OFF_DAYLIGHT state
		obs.assertStateEquals(LightStateObserver.LAMP_OFF_DAYLIGHT);
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
				fail("The state of the observer is invalid, equals: " + state);
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
}
