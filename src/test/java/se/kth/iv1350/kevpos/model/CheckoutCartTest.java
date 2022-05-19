
package se.kth.iv1350.kevpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.kevpos.integration.DatabaseUnreachableException;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;
import se.kth.iv1350.kevpos.integration.ItemNotFoundException;

public class CheckoutCartTest {
    
    InventoryHandler inventoryHandler; 
    CheckoutCart checkoutCart;
    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        checkoutCart = new CheckoutCart(inventoryHandler);
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        checkoutCart = null;
    }

    

    /**
     * Test of AddItem method, of class CheckoutCart. Tests if a valid ItemDTO is produced when given a valid input identifier.
     */
    @Test 
    public void testAddItemReturnCorrectly() {
        try{
            ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO scannedItem = checkoutCart.addItem(itemRequest);
        
        assertNotNull(scannedItem, "failed to return data in return object");
        }
        catch(ItemNotFoundException itemNotFoundException)
        {itemNotFoundException.printStackTrace();}
    }
    
    
    @Test
    public void testAddItemDTORateOfVAT(){
        try{
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO receivedItemDTO = checkoutCart.addItem(itemRequest);
        double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
        assertTrue(receivedRateOfVAT == 0.25 || receivedRateOfVAT == 0.12 || receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
        }
        catch(ItemNotFoundException itemNotFoundException)
        {itemNotFoundException.printStackTrace();}
    }
    
        
    /**
     * Test of addItem method. Tests if an exceptions is thrown correctly if given a parameter out of range.
     */
    @Test
    public void testDoesFaultyInputGenerateItemNotFoundException(){
    ItemDTO itemRequest = new ItemDTO(Integer.MAX_VALUE, 1, null, null, 0, 0);
    
    try{
        checkoutCart.addItem(itemRequest);    
        fail("The function did not return an expected exception.");
    }
        catch(ItemNotFoundException itemNotFoundException){
        boolean errorMessage = itemNotFoundException.getMessage().contains("" + itemRequest.getIdentifier());
        assertTrue(errorMessage, "The function returned the right exception type, but not related to input");
        }
    }
    
    /**
     * Test of addItem method. Tests if an exceptions is thrown correctly if given a negative parameter out of range.
     */
        @Test
    public void testDoesFaultyNegativeInputGenerateItemNotFoundException(){
    ItemDTO itemRequest = new ItemDTO(Integer.MIN_VALUE, 1, null, null, 0, 0);
    
    try{
        checkoutCart.addItem(itemRequest);    
        fail("The function did not return an expected exception.");
    }
        catch(ItemNotFoundException itemNotFoundException){
        boolean errorMessage = itemNotFoundException.getMessage().contains("" + itemRequest.getIdentifier());
        assertTrue(errorMessage, "The function returned the right exception type, but not related to input");
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
            checkoutCart.addItem(forbiddenItemIdentifier);
            fail("Method did not return the expected exception.");
        } 
        catch (DatabaseUnreachableException databaseUnreachableException) {
            databaseUnreachableException.printStackTrace();
        }
        catch (Exception exception){
            fail("Method throws the wrong exception.");
        }

    }
}
