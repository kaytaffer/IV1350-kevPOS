package se.kth.iv1350.kevpos.model;

import java.util.ArrayList;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;

/**
 * Contains information about the current state of the <code>Sale</code> in progress.
 */
public class Sale {

        private CheckoutCart checkoutCart;
	private DiscountHandler discountHandler;
	private double totalPrice;
        private double totalVAT;
	private double discount;
	private double receivedPayment;

        /**
         * Creates an instance of a <code>Sale</code>.
         * @param checkoutCart contains the <code>Item</code>s in the current <code>Sale</code>.
         * @param discountHandler the handler connecting to discount database.
         */
	public Sale(CheckoutCart checkoutCart, DiscountHandler discountHandler) {
            this.checkoutCart = checkoutCart;
            this.discountHandler = discountHandler;
            this.totalPrice = 0.0;
            this.totalVAT = 0.0;
            this.discount = 0.0;
            this.receivedPayment = 0.0;
	}
        /**
         * Updates the running total and total VAT for the <code>Sale</code> in progress.
         * @param scannedItem The latest scanned item, the price of which (including VAT) is to be added to the running total.
         * @return Contains relevant info of changed states in the program.
         */
	public SaleStateDTO updateRunningTotal(ItemDTO scannedItem) {
            totalVAT = totalVAT + calculateVAT(scannedItem);
            this.totalPrice = this.totalPrice + (scannedItem.getPrice() * scannedItem.getQuantity()) + calculateVAT(scannedItem);
            SaleStateDTO saleState = updateSaleState(scannedItem, totalVAT, this.totalPrice);
            return saleState; 
	}
        /**
         * Calculates the VAT of a given <code>ItemDTO</code>.
         * @param scannedItem the <code>ItemDTO</code> in question.
         * @return the VAT.
         */
        private double calculateVAT(ItemDTO scannedItem){
            double calculatedVAT = scannedItem.getRateOfVAT() * scannedItem.getPrice() * scannedItem.getQuantity();
            return calculatedVAT;
        }
        /**
         * Collects data to save to a DTO for eventual transfer to View.
         * @param scannedItem The latest scanned item
         * @param totalVAT the current amount of VATin the <code>Sale</code>.
         * @param totalPrice the current running total in the <code>Sale</code>.
         * @return the latest changes to the application, to be returned to View.
         */
        private SaleStateDTO updateSaleState(ItemDTO scannedItem, double totalVAT, double totalPrice)
        {
            final ArrayList<Item> shoppingCart = checkoutCart.getListOfItems();
            SaleStateDTO saleState = new SaleStateDTO(scannedItem, totalVAT, totalPrice, shoppingCart);                        
            return saleState;
        }
        
        /**
         * Unimplemented: discount
         * @param customerID
         * @return 
         */
	public Sale applyDiscount(int customerID) {
		return null;
	}

	private Sale updateRunningTotal(double discount) {
		return null;
	}

	private java.time.LocalTime logDateAndTimeForSale() {
		return null;
	}


}
