
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
import se.kth.iv1350.kevpos.model.Register;
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
    Register register;

    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        discountHandler = new DiscountHandler();
        accountingHandler = new AccountingHandler();
        salesLog = new SalesLog(accountingHandler, inventoryHandler);
        register = Register.getRegister();
        controller = new Controller(discountHandler, inventoryHandler, register, salesLog);
        itemRequest = new ItemDTO(5, 1, null, null, 0, 0);
        controller.startNewSale();
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        checkoutCart = null;
        discountHandler = null;
        accountingHandler = null;
        register = null;
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
        try{
        SaleStateDTO saleState = controller.nextItem(itemRequest);
        String actual = saleState.getListOfItems().get(0).getName();
        
        assertEquals(expectedValue, actual, "The returned item is not identical");
                }
        catch(InvalidInputException e)
        {e.printStackTrace();}
    }

    /**
     * Test of nextItem method, of class Controller. Tests if the running total is correctly calculated.
     */
    @Test
    public void testIsRunningTotalRight() {
        double expectedValue = 2 * (22.4); 
        
        try{
        
        SaleStateDTO saleState = controller.nextItem(itemRequest);
        saleState = controller.nextItem(itemRequest);
        double actual = saleState.getRunningTotal();
        
        assertEquals(expectedValue, actual, "The returned total price is not the same as the expected");
        
                }
        catch(InvalidInputException e)
        {e.printStackTrace();}
    }
    
        /**
     * Test of nextItem method, of class Controller. Tests if the total VAT is correctly returned.
     */
    @Test
    public void testIsVATRight() {
        double expectedValue = 2.4; 
        
        try{
        SaleStateDTO saleState = controller.nextItem(itemRequest);
        double actual = saleState.getTotalVAT();

        
        assertEquals(expectedValue, actual, "The returned VAT is not the same as the expected");
                }
        catch(InvalidInputException e)
        {e.printStackTrace();}
    }
    
    /**
     * Test of nextItem method. Tests if an exceptions is thrown correctly if given a parameter out of range.
     */
    @Test
    public void testDoesFaultyInputGenerateAnInvalidInputException(){
    ItemDTO itemRequest = new ItemDTO(Integer.MAX_VALUE, 1, null, null, 0, 0);
    
    try{
        controller.nextItem(itemRequest);    
        fail("The method did not throw an expected exception.");
    }
        catch(InvalidInputException invalidInputException){
            invalidInputException.printStackTrace();
        }
        catch(Exception exception){
            fail("A wrong type of exception was thrown.");
        }
    }
    
    /**
     * Test of nextItem method. Tests if an exceptions is thrown correctly if given a negative parameter out of range.
     */
        @Test
    public void testDoesFaultyNegativeInputGenerateItemNotFoundException(){
    ItemDTO itemRequest = new ItemDTO(Integer.MIN_VALUE, 1, null, null, 0, 0);
    
    try{
        controller.nextItem(itemRequest);    
        fail("The method did not throw an expected exception.");
    }
        catch(InvalidInputException invalidInputException){
            invalidInputException.printStackTrace();
        }
        catch(Exception exception){
            fail("A wrong type of exception was thrown.");
        }
    }  
    
    
    /**
     * Test if the right exception is thrown when the method receives a "forbidden item", 
     * which represents a state where the database cannot be accessed.
     */
    @Test
    public void testForbiddenItemIDGenerateCouldNotReachDatabaseException(){
        ItemDTO forbiddenItemIdentifier = new ItemDTO(404, 1, null, null, 0, 0);
        try {
            controller.nextItem(forbiddenItemIdentifier);
            fail("Method did not return the expected exception.");
        } 
        catch (ConnectionFailedException connectionException) {
            connectionException.printStackTrace();
        }
        catch (Exception exception){
            fail("Method throw the wrong exception.");
        }
    }      
}
