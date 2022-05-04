
package se.kth.iv1350.kevpos.model;
/**
 * A DTO concerning information about the exchange of cash.
 */
public class PaymentInfoDTO {
    
    private final double totalPrice;
    private final double paymentReceived;
    private final double change;

    /**
     * Creates an instance of a <code>PaymentInfoDTO</code>. 
     * @param totalPrice of the entire <code>Sale</code>.
     * @param paymentReceived from customer.
     * @param change to be given to customer.
     */
    public PaymentInfoDTO(double totalPrice, double paymentReceived, double change) {
        this.totalPrice = totalPrice;
        this.paymentReceived = paymentReceived;
        this.change = change;
    }
        /**
         * Get the <code>totalPrice</code>.
         * @return cost of the entire <code>Sale</code>.
         */
    public double getTotalPrice() {
        return totalPrice;
    }
        /**
         * Get the <code>paymentReceived</code>.
         * @return the amount the customer payed.
         */
    public double getPaymentReceived() {
        return paymentReceived;
    }
        /**
         * Get the <code>change</code>.
         * @return the amount returned to customer.
         */
    public double getChange() {
        return change;
    }
    
    
    
}
