package se.kth.iv1350.kevpos.view;

import java.util.ArrayList;
import se.kth.iv1350.kevpos.controller.ConnectionFailedException;
import se.kth.iv1350.kevpos.controller.Controller;
import se.kth.iv1350.kevpos.controller.InvalidInputException;
import se.kth.iv1350.kevpos.integration.ItemDTO;
import se.kth.iv1350.kevpos.model.Item;
import se.kth.iv1350.kevpos.model.ReceiptDTO;
import se.kth.iv1350.kevpos.model.SaleStateDTO;

/**
 * A mostly unimplemented placeholder for the user interface. Contains a few hardcoded data entries to test the program functionality. 
 */
public class View {

	private final Controller controller;
        
        /**
         * Creates an instance of the <code>View</code>.
         * @param controller the only access for the <code>View</code> to the business logic goes through this controller.
         */
	public View(Controller controller) {
		this.controller = controller;
	}
        
        private final ArrayList<ItemDTO> itemInputs = new ArrayList<>();
        /**
         * Fills a hardcoded list of items used to demonstrate the application outputs as desired.
         */
        private void fillList(){
            itemInputs.add(new ItemDTO(3, 1, null, null, 0, 0));
            itemInputs.add(new ItemDTO(5, 1, null, null, 0, 0));
            itemInputs.add(new ItemDTO(3, 1, null, null, 0, 0));
            itemInputs.add(new ItemDTO(6, 1, null, null, 0, 0)); //undefined itemIdentifier.
            itemInputs.add(new ItemDTO(404, 1, null, null, 0, 0)); //simulates loss of access to database.
        }
        private void runHardcodedCalls(){            
            SaleStateDTO toBePrintedOnScreen;
            ReceiptDTO receipt;
            
            //first sale
            controller.startNewSale();
            
            for(ItemDTO itemDTO : itemInputs){
                try{
                    toBePrintedOnScreen = controller.nextItem(itemDTO);
                    toScreenScannedItem(toBePrintedOnScreen);
                }
                catch(InvalidInputException exception){
                    System.out.println("ERROR: You've entered something wrong. Please try again.");
                }
                catch(ConnectionFailedException exception){
                    System.out.println("ERROR: Please reconnect and try again.");
                }
            }
            
            double paymentReceived = 200.0;
            
            receipt = controller.concludeSale(paymentReceived);
            toScreenSaleConcluded(receipt);
            
                        //Second sale
            
            controller.startNewSale();
                        try{
            toBePrintedOnScreen = controller.nextItem(itemInputs.get(0));
            toScreenScannedItem(toBePrintedOnScreen);
            }
            catch(InvalidInputException invalidInputException){
                System.out.println("ERROR: You've entered something wrong. Please try again.");
            }
            
            paymentReceived = 60.0;
            receipt = controller.concludeSale(paymentReceived);
            toScreenSaleConcluded(receipt);
        }
        
        /**
         * A hardcoded series of calls to the <code>Controller</code>, representing user input. 
         */
        public void viewExecute(){
            System.out.println("-------------------------------");
            fillList();
            runHardcodedCalls();
            
        }
        
        /**
         * Prints ongoing <code>Sale</code> info in the terminal
         * @param toBePrintedOnScreen current state changes after an entered <code>Item</code> <code>identifier</code>.
         */
        private void toScreenScannedItem(SaleStateDTO toBePrintedOnScreen){
            System.out.println("Latest scanned item: " + toBePrintedOnScreen.getScannedItemDTO().getName() 
                    + "\n Description: It is " + toBePrintedOnScreen.getScannedItemDTO().getDescription()
                    + "\n Running total: " + toBePrintedOnScreen.getRunningTotal()
                    + "\n including VAT: " + toBePrintedOnScreen.getTotalVAT());
            System.out.println("-------------------------------");
        }
        
        /**
         * Prints the <code>Receipt</code> in the terminal
         * @param receipt final data sent from the program after a concluded <code>Sale</code>.
         */
        private void toScreenSaleConcluded (ReceiptDTO receipt){
            System.out.println("\n -------------------------------");
            System.out.println("---Receipt---");
            
            System.out.println(receipt.getTimeForSale());

            ArrayList<Item> listOfItems = receipt.getListOfBoughtItems();
            for (Item item : listOfItems)
            {
                System.out.println(item.getQuantity() + "  " + item.getName() + "  " + item.getDescription() + "  " + item.getPrice()*item.getQuantity());
            }
            System.out.println("\n"
                + "Total price: " + receipt.getTotalPrice()
                + "\n Discount: " + receipt.getDiscount()
                + "\n Received Payment: " + receipt.getReceivedPayment()
                + "\n Change: " + receipt.getChange()
                + "\n -------------------------------");
        }

}
