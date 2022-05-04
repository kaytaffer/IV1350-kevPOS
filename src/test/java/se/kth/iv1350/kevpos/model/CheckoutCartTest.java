
package se.kth.iv1350.kevpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;

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
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO scannedItem = checkoutCart.addItem(itemRequest);
        
        assertNotNull(scannedItem, "failed to return data in return object");
    }
    
    
    /**
     * Test of AddItem method. Tests if a null ItemDTO is produced when given an out-of-range input identifier.
     */
    @Test
    public void testDoesAddItemFailWhenIDOutsideRange(){
        ItemDTO itemRequest = new ItemDTO(Integer.MAX_VALUE, 1, null, null, 0, 0);
        ItemDTO scannedItem = checkoutCart.addItem(itemRequest);
        
        assertNull(scannedItem, "ItemDTO contains data even though it shouldn't");
    }

    /**
     * Test of AddItem method. Tests if a null ItemDTO is produced when given a negative input identifier.
     */
    @Test
    public void testDoesAddItemFailWhenIDNegative(){
        ItemDTO itemRequest = new ItemDTO(Integer.MIN_VALUE, 1, null, null, 0, 0);
        ItemDTO scannedItem = checkoutCart.addItem(itemRequest);
        
        assertNull(scannedItem, "ItemDTO contains data even though it shouldn't");
    }
    
    /**
     * Test of AddItem method. Tests if the returned ItemDTO gives a valid rateOfVAT.
     */
    @Test
    public void testAddItemDTORateOfVAT(){
        ItemDTO itemRequest = new ItemDTO(1, 1, null, null, 0, 0);
        ItemDTO receivedItemDTO = checkoutCart.addItem(itemRequest);
        double receivedRateOfVAT = receivedItemDTO.getRateOfVAT();
        assertTrue(receivedRateOfVAT == 0.25 || receivedRateOfVAT == 0.12 || receivedRateOfVAT == 0.06  ,"Method return invalid VAT-Rate");
    }
    
}
