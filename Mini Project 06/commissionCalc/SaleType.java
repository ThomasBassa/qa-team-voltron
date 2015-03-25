package commissionCalc;

/** An enumeration representing types of sales.
 * @author Thomas Bassa */
public enum SaleType {
	BASIC_ITEM(0.02, 0.04),
	MAINTENANCE_ITEM(0.03, 0.06),
	REPLACEMENT_ITEM(0.01, 0.015),
	CONSULTING_ITEM(0.03, 0.08);
	
	/** The commission rate earned by probationary employees on this sale type */
	private final double probationaryCommission;
	
	/** The commission rate earned by experienced employees on this sale type */
	private final double experiencedComission;
	
	/** Constructs a SaleType by initializing all fields. */
	private SaleType(double probationaryCommission, double experiencedComission) {
		this.probationaryCommission = probationaryCommission;
		this.experiencedComission = experiencedComission;
	}
	
	/** Get the commission rate for this sale type based on an employee experience level.
	 * @param expLevel the employee experience level
	 * @return the commission rate for the employee type; fractional (already divided by 100)
	 * This is zero for types other than PROBATIONARY or EXPERIENCED.
	 */
	public double getCommissionRate(EmployeeExperience expLevel) {
		switch(expLevel) {
			case PROBATIONARY:
				return probationaryCommission;
			case EXPERIENCED:
				return experiencedComission;
			default:
				return 0;
		}
	}
}
