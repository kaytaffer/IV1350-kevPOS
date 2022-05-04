package se.kth.iv1350.kevpos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * All final info regarding, and proof of, the concluded <code>Sale</code>.
 */
public class ReceiptDTO {

	private final LocalDateTime timeForSale;
        private final ArrayList<Item> listOfBoughtItems;
	private final double totalPrice;
        private final double totalVAT;
	private final double discount;
	private final double receivedPayment;
	private final double change;

        /**
         * * Creates an instance of a <code>ReceiptDTO</code>. 
         * @param saleState the final state of the <code>Sale</code>, ready to be concluded.
         * @param paymentInfo information about the exchanged cash.
         */
	public ReceiptDTO(SaleStateDTO saleState, PaymentInfoDTO paymentInfo) {
            this.timeForSale = LocalDateTime.now(); 
            this.listOfBoughtItems = saleState.getListOfItems();
            this.totalPrice = saleState.getRunningTotal();
            this.totalVAT = saleState.getTotalVAT();
            this.discount = 0;
            this.receivedPayment = paymentInfo.getPaymentReceived();
            this.change = paymentInfo.getChange();
	}

        /**
         * get the date and time for the concluded <code>Sale</code>.
         * @return the time and date
         */
    public LocalDateTime getTimeForSale() {
        return timeForSale;
    }
        /**
         * Get a list of all <code>Item</code>s bought in the concluded <code>Sale</code>.
         * @return list of all bought <code>Item</code>s.
         */
    public ArrayList<Item> getListOfBoughtItems() {
        return listOfBoughtItems;
    }
        /**
         * Get the total cost of concluded <code>Sale</code>.
         * @return the total cost.
         */
    public double getTotalPrice() {
        return totalPrice;
    }
        /**
         * the total amount of VAT for the concluded <code>Sale</code>.
         * @return total VAT payed
         */
    public double getTotalVAT() {
        return totalVAT;
    }
        /**
         * the total amount of <code>Discount</code> in the concluded <code>Sale</code>.
         * @return total <code>Discount</code> discounted.
         */
    public double getDiscount() {
        return discount;
    }
        /**
         * the amount of received payment from the customer.
         * @return total payment received.
         */
    public double getReceivedPayment() {
        return receivedPayment;
    }
    
            /**
         * the amount of <code>change</code> in the concluded <code>Sale</code>.
         * @return amount of change.
         */
    public double getChange() {
        return change;
    }
        
}
