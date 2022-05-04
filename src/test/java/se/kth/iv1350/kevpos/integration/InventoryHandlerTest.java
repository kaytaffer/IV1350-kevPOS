
package se.kth.iv1350.kevpos.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.kevpos.model.Sale;

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
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO scannedItem = inventoryHandler.constructItemDTO(itemRequest);
        
        assertNotNull(scannedItem, "failed to return data in return object");
    }

    /**
     * Test of ConstructItemDTO method. Tests if a null ItemDTO is produced when given an out-of-range input identifier.
     */
    @Test
    public void testDoesConstructItemFailsWhenIDOutsideRange(){
        ItemDTO itemRequest = new ItemDTO(Integer.MAX_VALUE, 1, null, null, 0, 0);
        ItemDTO scannedItem = inventoryHandler.constructItemDTO(itemRequest);
        
        assertNull(scannedItem, "ItemDTO contains data even though it shouldn't");
    }

    /**
     * Test of ConstructItemDTO method. Tests if a null ItemDTO is produced when given a negative input identifier.
     */
    @Test
    public void testDoesConstructItemFailsWhenIDNegative(){
        ItemDTO itemRequest = new ItemDTO(Integer.MIN_VALUE, 1, null, null, 0, 0);
        ItemDTO scannedItem = inventoryHandler.constructItemDTO(itemRequest);
        
        assertNull(scannedItem, "ItemDTO contains data even though it shouldn't");
    }
    
    /**
     * Test of ConstructItemDTO method. Tests if the returned ItemDTO gives a valid rateOfVAT.
     */
    @Test
    public void testCreateItemDTORateOfVAT(){
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO receivedItemDTO = inventoryHandler.constructItemDTO(itemRequest);
        double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
        assertTrue(receivedRateOfVAT == 0.25 || receivedRateOfVAT == 0.12 || receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
    }
}
