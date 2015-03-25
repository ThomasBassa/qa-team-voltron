package commissionCalc;

/** An enumeration representing levels of employee experience,
 * in addition to storing constant data related to the experience
 * (notably the minimum sales for earning commission and bonus commission).
 * @author Thomas Bassa */
public enum EmployeeExperience {
	PROBATIONARY(2000.00, 50000.00, 0.005),
	EXPERIENCED(5000.00, 100000.00, 0.015);
	
	/** The minimum sales an employee needs to earn commission. */
	private final double minCommissionSales;
	
	/** The minimum sales an employee needs to earn bonus commission */
	private final double minBonusSales;
	
	/** The bonus commission rate an employee earns after reaching minBonusSales */
	private final double bonusCommissionRate;
	
	/** Initializes the fields associated with each enum item. */
	private EmployeeExperience(double minCommissionSales, double minBonusSales, double bonusCommissionRate) {
		this.minCommissionSales = minCommissionSales;
		this.minBonusSales = minBonusSales;
		this.bonusCommissionRate = bonusCommissionRate;
	}
	
	/** @return the minimum sales needed to earn commission for this experience level */
	public double getMinCommissionSales() {
		return minCommissionSales;
	}
	
	/** @return the minimum sales needed to earn bonus commission for this experience level */
	public double getMinBonusSales() {
		return minBonusSales;
	}

	
	/** @return the bonus commission rate earned after reaching the sales needed for a bonus */
	public double getBonusCommissionRate() {
		return bonusCommissionRate;
	}
	
	
}
