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
	
	/** Test the setEmployeeExperience method
	 *  This is most possibilities that can happen,
	 *  and since the only two choices are EXPERIENCED
	 *  or PROBATIONARY there is no need to test invalid 
	 *  values
	 *  @author Michael Philotoff 
	 */
	@Test
	public void testsetEmployeeExperience() {
		
		//test setting from PROBATIONARY to EXPERIENCED
		try{
				calcProbationary.setEmployeeExperience(EmployeeExperience.EXPERIENCED);		
			} catch(Exception e){
				System.out.println(e);
				//fails
			}
		
		//test setting from EXPERIENCED to PROBATIONARY		
		try{			
				calcProbationary.setEmployeeExperience(EmployeeExperience.PROBATIONARY);			
			} catch(Exception e){
				System.out.println(e);
				//fails
			}
		
		//test setting from EXPERIENCED to EXPERIENCED		
		try{		
			calcExperienced.setEmployeeExperience(EmployeeExperience.EXPERIENCED);	
		} catch(Exception e){
			System.out.println(e);
			//fails
		}
		
		//test setting from PROBATIONARY to PROBATIONARY
		try{			
			calcProbationary.setEmployeeExperience(EmployeeExperience.PROBATIONARY);	
		} catch(Exception e){
			System.out.println(e);
			//fails
		}
	}
	
	/** Test the setcalculateCommission method
	 *  This test the lower bounds of it the area around
	 *  the lower bounds and a random larger number
	 *  @author Michael Philotoff 
	 */
	@Test
	public void testcalculateCommission() {
		
		//testing of calculateCommission when sale number of sales equals null
		assertEquals(0.0,calcProbationary.calculateCommission(),DELTA_MONEY);
		assertEquals(0.0,calcExperienced.calculateCommission(),DELTA_MONEY);
		
		//test 1 cent below the lower bound value
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 1999.99);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 4999.99);
		
		assertEquals(0.0,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(0.0,calcExperienced.calculateBonusCommission(),DELTA_MONEY);
		
		//testing the lower bounds
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 2000);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 5000);
		
		assertEquals(0.0,calcProbationary.calculateCommission(),DELTA_MONEY);
		assertEquals(0.0,calcExperienced.calculateCommission(),DELTA_MONEY);
		
		//testing 1 cent past the lower bounds
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 2000.01);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 5000.01);
		
		assertEquals(60.00,calcProbationary.calculateCommission(),DELTA_MONEY);
		assertEquals(400.00,calcExperienced.calculateCommission(),DELTA_MONEY);
		
		
		//testing 1 dollar pass the lower bounds
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 2001);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 5001);
		
		assertEquals(120.03,calcProbationary.calculateCommission(),DELTA_MONEY);
		assertEquals(800.08,calcExperienced.calculateCommission(),DELTA_MONEY);
		
		//testing a random sales number since there is no upper bounds 
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 1000000);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 1000000);
		
		assertEquals(30120.03,calcProbationary.calculateCommission(),DELTA_MONEY);
		assertEquals(80800.08,calcExperienced.calculateCommission(),DELTA_MONEY);


	}
	
	/** Test the setcalculateBonusCommission method
	 *  This test the lower bounds of it the area around
	 *  the lower bounds and a random larger number
	 *  @author Michael Philotoff 
	 */
	@Test
	public void testcalculateBonusCommissions() {
		
		//test when null
		assertEquals(0.0,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(0.0,calcExperienced.calculateBonusCommission(),DELTA_MONEY);
		
		//test 1 cent below the lower bound value
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 49999.99);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 99999.99);
		
		assertEquals(0.0,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(0.0,calcExperienced.calculateBonusCommission(),DELTA_MONEY);
		
		//test the lower bound value
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 50000);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 100000);
		
		assertEquals(0.0,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(0.0,calcExperienced.calculateBonusCommission(),DELTA_MONEY);
		
		//test 1 cent past the lower bounds
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 50000.01);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 100000.01);
		
		assertEquals(0.00,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(0.00,calcExperienced.calculateBonusCommission(),DELTA_MONEY);
		
		//test 1 dollar past the lower bounds
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 50001);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 100001);
		
		assertEquals(0.01,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(0.01,calcExperienced.calculateBonusCommission(),DELTA_MONEY);
		
		//test a larger number since there is no upper bounds
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 100000000);
		calcExperienced.addSale(SaleType.CONSULTING_ITEM, 100000000);
		
		assertEquals(499750.01,calcProbationary.calculateBonusCommission(),DELTA_MONEY);
		assertEquals(1498500.02,calcExperienced.calculateBonusCommission(),DELTA_MONEY);

	}
	
	/** Test the functionality of CommissionCalculator constructor.
	 * This function should create a CommisionCalculator object
	 * with given employee name and employee experience. This should 
	 * work properly for all variants of experience and any string for
	 * employee name.
	 * @author Greg Carkin
	 */
	@Test
	public void testCommissionCalculator(){
		CommissionCalculator calculator;
		
		//test valid inputs
		for(EmployeeExperience experience: EmployeeExperience.values()){
			try{
				calculator = new CommissionCalculator("a", experience);
				calculator = new CommissionCalculator("0", experience);
				calculator = new CommissionCalculator("", experience);
				calculator = new CommissionCalculator("&", experience);
				calculator = new CommissionCalculator("Z9a%", experience);
				calculator = new CommissionCalculator("Z9000@#$%&#@#$aaaaaa@#$2!@#aaa", experience);
			}catch (Exception e){
				fail("CommissionCalculator constructor threw an exception");
			}
		}
	
	}
	/** Test the functionality of addSale.
	 * This function should work properly with any SaleType 
	 * and any dollar value 0 or greater. 
	 * @author Greg Carkin
	 */
	@Test
	public void testAddSale(){
		
		//test valid inputs
		
		//test adding basic items
		calcProbationary.addSale(SaleType.BASIC_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 0.00, 0.001);
		calcProbationary.addSale(SaleType.BASIC_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 0.01, 0.001);
		calcProbationary.addSale(SaleType.BASIC_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 1.01, 0.001);
		
		//test adding maintenance items
		calcProbationary.addSale(SaleType.MAINTENANCE_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 1.01, 0.001);
		calcProbationary.addSale(SaleType.MAINTENANCE_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 1.02, 0.001);
		calcProbationary.addSale(SaleType.MAINTENANCE_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 2.02, 0.001);
		
		//test adding replacement items
		calcProbationary.addSale(SaleType.REPLACEMENT_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 2.02, 0.001);
		calcProbationary.addSale(SaleType.REPLACEMENT_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 2.03, 0.001);
		calcProbationary.addSale(SaleType.REPLACEMENT_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 3.03, 0.001);
		
		//test adding consulting items
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 3.03, 0.001);
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 3.04, 0.001);
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 4.04, 0.001);
		
		
		//test with negative sale
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, -0.01);
		assertEquals(calcProbationary.getTotalSales(), 4.04, 0.001);
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, -1000.00);
		assertEquals(calcProbationary.getTotalSales(), 4.04, 0.001);
	
		
		
	}
	/** Test the functionality of getTotalSales.
	 * This function should return a sum of all sales in transactions.
	 * It should work properly for any sale accepted by addSale.
	 * @author Greg Carkin
	 */
	@Test
	public void testGetTotalSales(){
		
		//test adding basic items
		calcProbationary.addSale(SaleType.BASIC_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 0.00, 0.001);
		calcProbationary.addSale(SaleType.BASIC_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 0.01, 0.001);
		calcProbationary.addSale(SaleType.BASIC_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 1.01, 0.001);
		
		//test adding maintenance items
		calcProbationary.addSale(SaleType.MAINTENANCE_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 1.01, 0.001);
		calcProbationary.addSale(SaleType.MAINTENANCE_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 1.02, 0.001);
		calcProbationary.addSale(SaleType.MAINTENANCE_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 2.02, 0.001);
		
		//test adding replacement items
		calcProbationary.addSale(SaleType.REPLACEMENT_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 2.02, 0.001);
		calcProbationary.addSale(SaleType.REPLACEMENT_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 2.03, 0.001);
		calcProbationary.addSale(SaleType.REPLACEMENT_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 3.03, 0.001);
		
		//test adding consulting items
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 0.00);
		assertEquals(calcProbationary.getTotalSales(), 3.03, 0.001);
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 0.01);
		assertEquals(calcProbationary.getTotalSales(), 3.04, 0.001);
		calcProbationary.addSale(SaleType.CONSULTING_ITEM, 1.00);
		assertEquals(calcProbationary.getTotalSales(), 4.04, 0.001);
	}
}
