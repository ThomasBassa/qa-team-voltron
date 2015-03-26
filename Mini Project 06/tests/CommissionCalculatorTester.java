package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import commissionCalc.CommissionCalculator;
import commissionCalc.EmployeeExperience;
import commissionCalc.SaleType;
import commissionCalc.SalesTransaction;


/** Test suite for the Commission Calculator, as part of QA Mini Project 6.
 * @author Thomas Bassa
 * @author Greg Carkin
 * @author Umar Idris
 * @author Michael Philotoff */
public class CommissionCalculatorTester {

	/** A CommissionCalculator for a probationary employee. Initialized in setUp. */
	private CommissionCalculator calcProbationary;

	/** A CommissionCalculator for an experienced employee. Initialized in setUp. */
	private CommissionCalculator calcExperienced;

	/** Monetary values should be accurate to the cent. */
	public static final double DELTA_MONEY = 0.01; //TODO Should this be 0.005?

	/** Other values are assumed to be accurate to four decimal places. */
	public static final double DELTA_OTHER = 0.0001; //TODO Should halve?

	/** Performs the following set up before each test is executed:
	 * Creates two CommissionCalculator objects: one probationary, one experienced.
	 * @throws Exception on encountering an issue (shouldn't happen) */
	@Before
	public void setUp() throws Exception {
		//Perform necessary setup before ALL tests here...
		calcProbationary = new CommissionCalculator("Joe Newb",
				EmployeeExperience.PROBATIONARY);
		calcExperienced = new CommissionCalculator(
				"Voltron: Defender of the Universe",
				EmployeeExperience.EXPERIENCED);
	}

	//For each test, Javadoc comment what the expected I/O is...

	/** Test the functionality of getMinimumSales.
	 * This function should return 2000 for a probationary employee,
	 * and 5000 for an experienced one.
	 * @author Thomas Bassa */
	@Test
	public void testMinimumSales() {
		assertEquals(2000.00, calcProbationary.getMinimumSales(), DELTA_MONEY);
		assertEquals(5000.00, calcExperienced.getMinimumSales(), DELTA_MONEY);
	}

	/** Test the functionality of getName.
	 * This function should simply return a String equivalent to that provided
	 * in the object constructor.
	 * @author Thomas Bassa */
	@Test
	public void testName() {
		//Be wary of the string constant use...
		//Be certain these match the strings in setUp.
		assertEquals("Joe Newb", calcProbationary.getName());
		assertEquals("Voltron: Defender of the Universe", calcExperienced.getName());
	}
	
	/** Test the SalesTransaction constructor.
	 * This should work properly for all variants in SaleType,
	 * and with any positive (or zero) value.
	 * Providing negative values should throw an exception
	 * @author Thomas Bassa */
	@Test
	public void testConstructSalesTransaction() {
		@SuppressWarnings("unused")
		SalesTransaction transaction;
		
		//Try all possible SaleTypes. There aren't that many.
		for(SaleType type: SaleType.values()) {
			
			//Test valid constructors near zero bound
			try {
				transaction = new SalesTransaction(type, 0);
				transaction = new SalesTransaction(type, 0.01);
				transaction = new SalesTransaction(type, 10);
				transaction = new SalesTransaction(type, Double.MAX_VALUE);
			} catch (Exception e) {
				fail("Valid transaction constructor threw an exception.");
			}
			
			//Test invalid constructors
			try {
				transaction = new SalesTransaction(type, -.01);
				fail("Transaction created with negative 1 cent.");
			} catch (Exception e) {
				//Pass
			}
			
			try {
				transaction = new SalesTransaction(type, -10);
				fail("Transaction created with -10");
			} catch (Exception e) {
				//Pass
			}
			
			try {
				//Double.MIN_VALUE is the smallest positive... so negate MAX.
				transaction = new SalesTransaction(type, -Double.MAX_VALUE);
				fail("Transaction created with really big negative number");
			} catch (Exception e) {
				//Pass
			}
		}
	}
}
