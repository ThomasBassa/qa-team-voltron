package commissionCalc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/** This class handles the calculatioon of employee commissions. */
public class CommissionCalculator implements iCommissionCalculator {

	/* TRB
	private static final double MINIMUM_PROBATIONARY_SALES_FOR_COMMISSION = 2000.00;
	private static final double MINIMUM_EXPERIENCED_SALES_FOR_COMMISSION = 5000.00;

	private static final double MINIMUM_PROBATIONARY_SALES_FOR_BONUS_COMMISSION = 50000.00;
	private static final double MINIMUM_EXPERIENCED_SALES_FOR_BONUS_COMMISSION = 100000.00;
	This section removed in favor of storing the data in EmployeeExperience enum
	*/

	/** This method will construct a new instance of a commission calculator.
	 * @param employeeName
	 *            This is the name of the employee.
	 * @param employeeExperience
	 *            This is the experience level of the employee from the
	 *            parameters defined in the enumeration. */
	public CommissionCalculator(String employeeName, EmployeeExperience employeeExperience) {
		super();
		this.employeeName = employeeName;
		this.employeeExperience = employeeExperience;
	}

	/** This variable holds the transactions that this salesman has made this
	 * month and should be considered for commission calculation. */
	private List<SalesTransaction> transactions = new ArrayList<SalesTransaction>();

	/** This is the name of the employee. */
	private String employeeName;

	/** This variable keeps information about how much experience the given
	 * employee has. */
	private EmployeeExperience employeeExperience;

	@Override
	//TRB public void addSale(int salesType, double dollarAmount) {
	//TRB Replace with enum type.
	public void addSale(SaleType salesType, double dollarAmount) {
		try {
			// Instantiate a new instance of a sale.
			SalesTransaction s = new SalesTransaction(salesType, dollarAmount);

			// Add it to the list of sales for this month.
			this.transactions.add(s);
		} catch (Exception e) {}
	}

	@Override
	public double getTotalSales() {
		double totalSales = 0.0;

		Iterator<SalesTransaction> iter = this.transactions.iterator();

		while(iter.hasNext()) {
			SalesTransaction s = iter.next();
			totalSales += s.getTransactionAmount();
		}
		return totalSales;
	}

	@Override
	public void setEmployeeExperience(EmployeeExperience employeeExperience) {
		//TRB if((employeeExperience == iCommissionCalculator.EXPERIENCED)
		//TRB		|| (employeeExperience == iCommissionCalculator.PROBATIONARY)) {
		//TRB Replaced with enumeration, therefore no check of value needed.
		this.employeeExperience = employeeExperience;
		//}
	}

	@Override
	public double calculateCommission() {
		/* TRB
		final double commissionRatesForProbationaryEmployee[] = {2, 3, 1, 3};
		final double commissionRatesForExperiencedEmployee[] = {0.04, 0.06, 0.015, 0.08};

		double commissionTable[];

		// Setup based on the employee type type value of commissions that
		// should be paid out.
		if(this.employeeExperience == iCommissionCalculator.PROBATIONARY) {
			commissionTable = commissionRatesForProbationaryEmployee;
		} else if(this.employeeExperience == iCommissionCalculator.EXPERIENCED) {
			commissionTable = commissionRatesForExperiencedEmployee;
		} else {
			commissionTable = null;
		}
		TRB The data in the constant arrays was corrected (from inconsistent whole numbers
		versus pre-divided percentages) and moved to the SaleType enum.
		TRB The if/else chain was rendered pointless by the EmployeeExperience enum. */

		// Now that the tables are set, determine the minimum amount for a commission.
		double minimumSalesForCommission = this.getMinimumSales();

		// This is the net sales that the salesman has this month.
		double netSales = 0.00;
		double commission = 0.00;

		// Iterate over all transactions.
		for(SalesTransaction s : this.transactions) {
			// If we have already met the threshold for sales, simply add the commission in.
			if(netSales >= minimumSalesForCommission) {

				commission += s.getTransactionAmount()
						//TRB * commissionTable[s.getTransactionType()];
						* s.getTransactionType().getCommissionRate(employeeExperience);
						//TRB Refactored to use enum call from SaleType.
			} else if((netSales + s.getTransactionAmount()) >= minimumSalesForCommission) {
				// We need to determine how much of this sale qualifies for commission.
				double commissionableAmount = (netSales + s
						.getTransactionAmount()) - minimumSalesForCommission;
				netSales += s.getTransactionAmount();
				commission += commissionableAmount
						//TRB * commissionTable[s.getTransactionType()];
						* s.getTransactionType().getCommissionRate(employeeExperience);
						//TRB Refactored to use enum call from SaleType.
			} else {
				// No commission. Simply go on.
				netSales += s.getTransactionAmount();
			}
		}

		return commission;
	}

	@Override
	public double calculateBonusCommission() {
		/* TRB 
		final double BONUS_COMMISSION_FOR_PROBATIONARY_EMPLOYEE_RATE = 0.005;
		final double BONUS_COMMISSION_FOR_EXPERIENCED_EMPLOYEE_RATE = 0.015;
		TRB Data moved to EmployeeExperience enumeration. */

		// Setup based on the employee type type value of commissions that
		// should be paid out.
		//TRB Added initializers from enum.
		double bonusCommissionRate = employeeExperience.getBonusCommissionRate();
		double minimumSalesForBonusCommission = employeeExperience.getMinBonusSales();

		/* TRB
		if(this.employeeExperience == iCommissionCalculator.PROBATIONARY) {
			bonusCommissionRate = BONUS_COMMISSION_FOR_PROBATIONARY_EMPLOYEE_RATE;
			minimumSalesForBonusCommission = MINIMUM_PROBATIONARY_SALES_FOR_BONUS_COMMISSION;
		} else if(this.employeeExperience == iCommissionCalculator.EXPERIENCED) {
			bonusCommissionRate = BONUS_COMMISSION_FOR_EXPERIENCED_EMPLOYEE_RATE;
			minimumSalesForBonusCommission = MINIMUM_EXPERIENCED_SALES_FOR_BONUS_COMMISSION;
		} else {
			bonusCommissionRate = 0;
			minimumSalesForBonusCommission = 0;
		}
		TRB The data is simply retrieved from the enum now. */

		// This is the net sales that the salesman has this month.
		double netSales = 0.00;
		double bonusCommission = 0.00;

		// Iterate over all transactions.
		for(SalesTransaction s : this.transactions) {
			// If we have already met the threshold for sales, simply add the commission in.
			if(netSales >= minimumSalesForBonusCommission) {
				netSales += s.getTransactionAmount();
				bonusCommission += s.getTransactionAmount()
						* bonusCommissionRate;
			} else if((netSales + s.getTransactionAmount()) >= minimumSalesForBonusCommission) {
				// We need to determine how much of this sale qualifies for commission.
				double commissionableAmount = (netSales + s
						.getTransactionAmount())
						- minimumSalesForBonusCommission;

				bonusCommission += commissionableAmount * bonusCommissionRate;
			} else {
				// No commission. Simply go on.
				netSales += s.getTransactionAmount();
			}
		}

		return bonusCommission;
	}

	@Override
	public double getMinimumSales() {
		/* TRB
		double retVal = 0;
		if(this.employeeExperience == iCommissionCalculator.PROBATIONARY) {
			retVal = CommissionCalculator.MINIMUM_PROBATIONARY_SALES_FOR_COMMISSION;
		} else if(this.employeeExperience == iCommissionCalculator.EXPERIENCED) {
			retVal = CommissionCalculator.MINIMUM_EXPERIENCED_SALES_FOR_COMMISSION;
		} else {
			retVal = 0.00;
		}
		return retVal;
		TRB This data is now associated with a single call from the EmployeeExperience enum. */
		return employeeExperience.getMinCommissionSales();
	}

	@Override
	public String getName() {
		return this.employeeName;
	}
}
