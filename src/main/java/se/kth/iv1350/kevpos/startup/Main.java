package se.kth.iv1350.kevpos.startup;

import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.controller.Controller;
import se.kth.iv1350.kevpos.view.View;
import se.kth.iv1350.kevpos.integration.SalesLog;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.AccountingHandler;
import se.kth.iv1350.kevpos.integration.TotalRevenueFileOutput;
import se.kth.iv1350.kevpos.model.Register;
import se.kth.iv1350.kevpos.view.TotalRevenueView;
/**  
 * Starts the application and contains the <code>main</code> method.
 */

public class Main {
        
       /** 
        * Starts the entire application and sets up initial classes for later use.
        * @param args The application does not take any command line arguments
        */
	public static void main(String[] args) {
            AccountingHandler accountingHandler = new AccountingHandler();
            InventoryHandler inventoryHandler = new InventoryHandler();
            SalesLog salesLog = new SalesLog(accountingHandler, inventoryHandler);
            DiscountHandler discountHandler = new DiscountHandler();
            Register register = Register.getRegister();
            TotalRevenueView totalRevenueView = new TotalRevenueView();
            register.addRegisterObserver(totalRevenueView);
            TotalRevenueFileOutput totalRevenueFileOutput = new TotalRevenueFileOutput();
            register.addRegisterObserver(totalRevenueFileOutput);
            Controller controller = new Controller(discountHandler, inventoryHandler, register, salesLog);
            View view = new View(controller);
            
            view.viewExecute();
	}

}
