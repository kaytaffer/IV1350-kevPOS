package se.kth.iv1350.kevpos.view;

import java.lang.*;
import java.util.ArrayList;
import se.kth.iv1350.kevpos.controller.Controller;
import se.kth.iv1350.kevpos.integration.ItemDTO;
import se.kth.iv1350.kevpos.model.Item;
import se.kth.iv1350.kevpos.model.ReceiptDTO;
import se.kth.iv1350.kevpos.model.SaleStateDTO;
/**
 * A mostly unimplemented placeholder for the user interface. Contains a few hardcoded data entries to test the program functionality. 
 */
public class View {

	private Controller controller;
/**
 * Creates an instance of the <code>View</code>.
 * @param controller the only access for the <code>View</code> to the business logic goes through this controller.
 */
	public View(Controller controller) {
		this.controller = controller;
	}
        
        /**
         * A hardcoded series of calls to the <code>Controller</code>, representing user input. 
         */
        public void viewExecute(){
            ItemDTO firstItemBought = new ItemDTO(3, 1, null, null, 0, 0);
            ItemDTO secondItemBought = new ItemDTO(5, 1, null, null, 0, 0);
            ItemDTO thirdItemBought = new ItemDTO(3, 1, null, null, 0, 0);
            SaleStateDTO toBePrintedOnScreen;
            
            controller.startNewSale();
            toBePrintedOnScreen = controller.nextItem(firstItemBought);
            toScreenOngoing(toBePrintedOnScreen);
            
            toBePrintedOnScreen = controller.nextItem(secondItemBought);
            toScreenOngoing(toBePrintedOnScreen);
            
            toBePrintedOnScreen = controller.nextItem(thirdItemBought);
            toScreenOngoing(toBePrintedOnScreen);
                        
            double paymentReceived = 200.0;
            ReceiptDTO receipt = controller.concludeSale(paymentReceived);
            toScreenFinal(receipt);
            
           //ReceiptDTO receipt = controller.concludeSale(paymentReceived);
        }
        
        /**
         * Prints ongoing <code>Sale</code> info in the terminal
         * @param toBePrintedOnScreen current state changes after an entered <code>Item</code> <code>identifier</code>.
         */
        private void toScreenOngoing(SaleStateDTO toBePrintedOnScreen){
            System.out.println("Latest scanned item: " + toBePrintedOnScreen.getScannedItemDTO().getName());
            System.out.println("It is " + toBePrintedOnScreen.getScannedItemDTO().getDescription());
            System.out.println("Running total: " + toBePrintedOnScreen.getRunningTotal());
            System.out.println("including VAT: " + toBePrintedOnScreen.getTotalVAT());
            System.out.println();
        }
        
        /**
         * Prints the <code>Receipt</code> in the terminal
         * @param receipt final data sent from the program after a concluded <code>Sale</code>.
         */
        private void toScreenFinal(ReceiptDTO receipt){
            System.out.println();
            System.out.println("---------");
            System.out.println(receipt.getTimeForSale());

            ArrayList<Item> listOfItems = receipt.getListOfBoughtItems();
            
            for (Item item : listOfItems)
            {
                System.out.println(item.getQuantity() + "  " + item.getName() + "  " + item.getDescription() + "  " + item.getPrice()*item.getQuantity());
            }
            System.out.println();
            System.out.println("Total price: " + receipt.getTotalPrice());
            System.out.println("Discount: " + receipt.getDiscount());
            System.out.println("Received Payment: " + receipt.getReceivedPayment());
            System.out.println("Change: " + receipt.getChange());
        }

}
