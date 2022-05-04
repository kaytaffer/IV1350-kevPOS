package se.kth.iv1350.kevpos.controller;

import se.kth.iv1350.kevpos.model.Register;
import se.kth.iv1350.kevpos.model.Sale;
import se.kth.iv1350.kevpos.integration.SalesLog;
import se.kth.iv1350.kevpos.model.ReceiptDTO;
import se.kth.iv1350.kevpos.model.CheckoutCart;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;
import se.kth.iv1350.kevpos.model.PaymentInfoDTO;
import se.kth.iv1350.kevpos.model.SaleStateDTO;
/**
 * Handles all calls from the <code>View</code> to <code>the Model</code>. This is the application's only controller. 
 */
public class Controller {
        
        private DiscountHandler discountHandler;
        private InventoryHandler inventoryHandler;
	private SalesLog salesLog;
	private CheckoutCart checkoutCart;
        private Sale sale;
        private Register register;
        
	private ReceiptDTO receiptDTO;

        /**
         * Creates an instance of the <code>Controller</code>.
         * @param discountHandler the handler connecting to discount database.
         * @param inventoryHandler the handler connecting to the inventory system
         * @param salesLog the application's log for completed sales.
         */
	public Controller(DiscountHandler discountHandler, InventoryHandler inventoryHandler, SalesLog salesLog) {
		this.discountHandler = discountHandler;
                this.inventoryHandler = inventoryHandler;
                this.salesLog = salesLog;
                this.register = new Register();
	}
/**
 * Sets upp a current <code>Sale</code>.
 */
	public void startNewSale() {
            checkoutCart = new CheckoutCart(inventoryHandler);
            sale = new Sale(checkoutCart, discountHandler);
	}
/**
 * Based on the input <code>ItemDTO</code>, adds a corresponding <code>Item</code> to the cart and updates the running total.
 * @param itemRequest a proto-item, all values null except for <code>identifier</code>. 
 * @return Contains relevant info of changed states in the program.
 */
	public SaleStateDTO nextItem(ItemDTO itemRequest) {
            ItemDTO scannedItem = checkoutCart.addItem(itemRequest);
            SaleStateDTO saleStateDTO = sale.updateRunningTotal(scannedItem);
            return saleStateDTO;
	}
/**
 * Unimplemented discount
 * @param customerID
 * @return 
 */
	public Sale signalDiscount(int customerID) {
		return null;
	}
/**
 * Ends the <code>Sale</code> and makes sure all logs are done.
 * @param receivedPayment from the customer.
 * @return receipt with all information about the <code>Sale</code>.
 */
	public ReceiptDTO concludeSale(double receivedPayment) {
            ItemDTO noItem = new ItemDTO(0, 0, null, null, 0, 0);
            SaleStateDTO saleState = sale.updateRunningTotal(noItem);
            PaymentInfoDTO paymentInfo = register.calculateChange(saleState, receivedPayment);
            
            salesLog.logSale(saleState, paymentInfo);
            ReceiptDTO receipt = new ReceiptDTO(saleState, paymentInfo);
            return receipt;
	}
}
