package se.kth.iv1350.kevpos.startup;

import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.controller.Controller;
import se.kth.iv1350.kevpos.view.View;
import se.kth.iv1350.kevpos.integration.SalesLog;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.AccountingHandler;
/**  
 * Starts the application and contains the <code>main</code> method.
 */

public class Main {
        
       /** 
        *  Starts the entire application and sets up initial classes for later use.
        * @param args The application does not take any command line arguments
        */
	public static void main(String[] args) {
            AccountingHandler accountingHandler = new AccountingHandler();
            InventoryHandler inventoryHandler = new InventoryHandler();
            SalesLog salesLog = new SalesLog(accountingHandler, inventoryHandler);
            DiscountHandler discountHandler = new DiscountHandler();
            Controller controller = new Controller(discountHandler, inventoryHandler, salesLog);
            View view = new View(controller);
            
            view.viewExecute();
	}

}
