package se.kth.iv1350.kevpos.integration;

import se.kth.iv1350.kevpos.model.Sale;
/**
 * unimplemented class, TODO handle discount requests.
 */
public class DiscountHandler {
    /**
     * Creates an instance of the <code>DiscountHandler</code>.
     */
    public DiscountHandler() {

    }
    
    /**
     * Get the discount to apply to the current <code>SaleZ</code>
     * @param customerID the ID for the customer who possibly has discounts.
     * @param sale The sale to which apply the discount.
     * @return the discounted amount.
     */
    public float getDiscount(int customerID, Sale sale) {
            return 0;
    }
}
