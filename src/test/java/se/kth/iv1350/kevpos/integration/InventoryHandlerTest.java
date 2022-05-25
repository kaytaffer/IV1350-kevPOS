
package se.kth.iv1350.kevpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventoryHandlerTest {
    
    private InventoryHandler inventoryHandler;
    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
    }

    /**
     * Test of ConstructItemDTO method. Tests if a valid ItemDTO is produced when given a valid input identifier.
     */
    @Test
    public void testDoesConstructItemDTOReturnAnything() {
        try{
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO scannedItem = inventoryHandler.constructItemDTO(itemRequest);
        
        assertNotNull(scannedItem, "failed to return data in return object");
        }
        catch(ItemNotFoundException itemNotFoundException)
        {}
    }

    /**
     * Test of ConstructItemDTO method. Tests if the returned ItemDTO gives a valid rateOfVAT.
     */
    @Test
    public void testCreateItemDTORateOfVAT(){
        try{
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO receivedItemDTO = inventoryHandler.constructItemDTO(itemRequest);
        double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
        assertTrue(receivedRateOfVAT == 0.25 || receivedRateOfVAT == 0.12 || receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
            }
        catch(ItemNotFoundException itemNotFoundException)
        {}
    }
    
    /**
     * Test of ConstructItemDTO method. Tests if an exceptions is thrown correctly if given a parameter out of range.
     */
    @Test
    public void testDoesFaultyInputGenerateItemNotFoundException(){
    ItemDTO itemRequest = new ItemDTO(Integer.MAX_VALUE, 1, null, null, 0, 0);
    
    try{
        inventoryHandler.constructItemDTO(itemRequest);    
        fail("The function did not return an expected exception.");
    }
        catch(ItemNotFoundException itemNotFoundException){
        boolean errorMessage = itemNotFoundException.getMessage().contains("" + itemRequest.getIdentifier());
        assertTrue(errorMessage, "The function returned the right exception type, but not related to input");
        }
    }
    
    /**
     * Test of ConstructItemDTO method. Tests if an exceptions is thrown correctly if given a negative parameter out of range.
     */
        @Test
    public void testDoesFaultyNegativeInputGenerateItemNotFoundException(){
    ItemDTO itemRequest = new ItemDTO(Integer.MIN_VALUE, 1, null, null, 0, 0);
    
    try{
        inventoryHandler.constructItemDTO(itemRequest);    
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
            inventoryHandler.constructItemDTO(forbiddenItemIdentifier);
            fail("Method did not return the expected exception.");
        } 
        catch (DatabaseUnreachableException databaseUnreachableException) {
            
        }
        catch (Exception exception){
            fail("Method throws the wrong exception.");
        }
    }
}
