package se.kth.iv1350.kevpos.model;
/**
 * Handles all logic concerning the exchange of cash at the conclusion of a <code>Sale</code>.
 */
public class Register {

	private double balance;
	private double change;

        /**
         * Creates an instance of a <code>Register</code>.
         */
	public Register() {
            this.balance = 0;
	}

/**
         * Calculate eventual <code>change</code> after payment is received.
         * @param saleState the final state of the <code>Sale</code>, ready to be concluded.
         * @param paymentReceived amount received from customer must be >= the total price.
         * @return information about the exchanged cash.
         */
    public PaymentInfoDTO calculateChange(SaleStateDTO saleState, double paymentReceived) {
            double totalPrice = saleState.getRunningTotal();
            change = (paymentReceived - totalPrice);
            updateBalance(totalPrice);
            PaymentInfoDTO paymentInfo = new PaymentInfoDTO(totalPrice, paymentReceived, change);
            
            return paymentInfo;
    }
        
        /**
         * Updates the <code>balance</code> in the <code>Register</code> after concluded <code>Sale</code>.
         * 
         * @param totalPrice amount to update balance with
         */
    private void updateBalance(double totalPrice) {
            balance = balance + totalPrice;
    }

}
