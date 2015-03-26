package commissionCalc;


/** This class holds information about
 * the amount of a transaction and the type of the transaction. */
public class SalesTransaction {
	/** This attribute is the type of the transaction;
	 * one of the variants in the SaleType enumeration. */
	private SaleType transactionType;

	/** This is the amount of the transaction, in dollars and cents. */
	private double transactionAmount;

	/** This constructor will instantiate a new instance of a transaction type.
	 * @param salesType
	 *            This is the type of the transaction. It must be one of the
	 *            ones defined in the SaleType enumeration.
	 * @param transactionAmount
	 *            This is the dollar amount of the transaction.
	 *            It must be positive.
	 * @throws Exception
	 *             an Exception will be thrown if the amount is negative or the
	 *             transaction type is invalid. */
	public SalesTransaction(SaleType salesType, double transactionAmount)
			throws Exception {
		super();

		/* TRB
		if((salesType != iCommissionCalculator.BASIC_ITEM)
				&& (salesType != iCommissionCalculator.CONSULTING_ITEM)
				&& (salesType != iCommissionCalculator.MAINTENANCE_ITEM)
				&& (salesType != iCommissionCalculator.REPLACEMNET_ITEM)) { throw new Exception(
				"Invalid transaction type."); }
		TRB Entire block is rendered unnecessary by use of enum. */

		if((transactionAmount < 0))	throw new Exception("Invalid transaction amount.");
		
		this.transactionType = salesType;
		this.transactionAmount = transactionAmount;
	}

	/** @return the transactionType, as defined in the SaleType enum. */
	public SaleType getTransactionType() {
		return transactionType;
	}

	/** This method will obtain the dollar amount for the transaction.
	 * @return the transactionAmount This is the dollar amount of the
	 *         transaction.	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
}
