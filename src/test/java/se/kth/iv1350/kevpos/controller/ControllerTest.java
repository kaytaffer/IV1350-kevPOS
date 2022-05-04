
package se.kth.iv1350.kevpos.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.kevpos.integration.AccountingHandler;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;
import se.kth.iv1350.kevpos.integration.SalesLog;
import se.kth.iv1350.kevpos.model.CheckoutCart;
import se.kth.iv1350.kevpos.model.Item;
import se.kth.iv1350.kevpos.model.ReceiptDTO;
import se.kth.iv1350.kevpos.model.Sale;
import se.kth.iv1350.kevpos.model.SaleStateDTO;

public class ControllerTest {
    
    InventoryHandler inventoryHandler; 
    CheckoutCart checkoutCart;
    DiscountHandler discountHandler;
    Sale sale;
    ItemDTO itemRequest;
    Controller controller;
    AccountingHandler accountingHandler;
    SalesLog salesLog;

    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        discountHandler = new DiscountHandler();
        accountingHandler = new AccountingHandler();
        salesLog = new SalesLog(accountingHandler, inventoryHandler);
        controller = new Controller(discountHandler, inventoryHandler, salesLog);
        itemRequest = new ItemDTO(5, 1, null, null, 0, 0);
        controller.startNewSale();
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        checkoutCart = null;
        discountHandler = null;
        accountingHandler = null;
        salesLog = null;
        controller = null;
        itemRequest = null;
        
    }
    
    
    /**
     * Test of nextItem method, of class Controller. Tests if the corresponding item is returned in the data.
     */
    @Test
    public void testIsRightItemReturned() {
        ItemDTO dto = new ItemDTO(5, 1, "Chocolate", "tasty", 20.0, 0.12);
        String expectedValue = new Item(dto).getName();
        
        SaleStateDTO saleState = controller.nextItem(itemRequest);
        String actual = saleState.getListOfItems().get(0).getName();
        
        assertEquals(expectedValue, actual, "The returned item is not identical");
    }

    /**
     * Test of nextItem method, of class Controller. Tests if the running total is correctly calculated.
     */
    @Test
    public void testIsRunningTotalRight() {
        double expectedValue = 2 * (22.4); 
        
        SaleStateDTO saleState = controller.nextItem(itemRequest);
        saleState = controller.nextItem(itemRequest);
        double actual = saleState.getRunningTotal();
        
        assertEquals(expectedValue, actual, "The returned total price is not the same as the expected");
    }
    
        /**
     * Test of nextItem method, of class Controller. Tests if the total VAT is correctly returned.
     */
    @Test
    public void testIsVATRight() {
        double expectedValue = 2.4; 
        
        SaleStateDTO saleState = controller.nextItem(itemRequest);
        double actual = saleState.getTotalVAT();
        
        assertEquals(expectedValue, actual, "The returned VAT is not the same as the expected");
    }
           
}
