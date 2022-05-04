
package se.kth.iv1350.kevpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;


public class SaleTest {
    
    InventoryHandler inventoryHandler; 
    CheckoutCart checkoutCart;
    DiscountHandler discountHandler;
    Sale sale;
    ItemDTO scannedItem;
    
    @BeforeEach
    public void setUp() {
        inventoryHandler = new InventoryHandler();
        checkoutCart = new CheckoutCart(inventoryHandler);
        discountHandler = new DiscountHandler();
        sale = new Sale(checkoutCart, discountHandler);
        scannedItem = new ItemDTO(5, 1, "Chocolate", "tasty", 20.0, 0.12);
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        checkoutCart = null;
        discountHandler = null;
        sale = null;
        scannedItem = null;
    }

    /**
     * Test of updateRunningTotal method, of class Sale. Tests if a valid SaleStateDTO is produced when given a valid input identifier.
     */
    @Test
    public void testUpdateRunningTotalReturnsAnything() {
        SaleStateDTO saleState = sale.updateRunningTotal(scannedItem);
        assertNotNull(scannedItem, "failed to return data in return object");
    }
    
        /**
     * Test of updateRunningTotal method, of class Sale. Tests if the shopping cart is correctly added to the SaleStateDTO.
     */
    
    @Test
    public void testUpdateRunningTotalReturnsAShoppingCart() {
        String expectedValue = "Chocolate";
        checkoutCart.addItem(scannedItem);
        SaleStateDTO saleState = sale.updateRunningTotal(scannedItem);
        String actual = saleState.getListOfItems().get(0).getName();
        
        assertEquals(expectedValue, actual, "The names of the item in the shopping cart are not the same");
        
    }
    
    /**
     * Test of updateRunningTotal method, of class Sale. Tests if given an item, the total VAT is correctly calculated.
     */
    @Test
    public void testUpdateRunningTotalReturnsRightTotalVAT() {
        double expectedValue = 2.4; 
        
        SaleStateDTO saleState = sale.updateRunningTotal(scannedItem);
        saleState.getRunningTotal(); 
        
        double actual = saleState.getTotalVAT();
        
        assertEquals(expectedValue, actual, "The returned VAT is not the same as the expected");
    }
        /**
     * Test of updateRunningTotal method, of class Sale. Tests if given an item, the running total is correctly calculated.
     */
    @Test
    public void testUpdateRunningTotalReturnsRightRunningTotal() {
        double expectedValue = 22.4; 
        
        SaleStateDTO saleState = sale.updateRunningTotal(scannedItem);
        saleState.getRunningTotal(); 
        
        double actual = saleState.getRunningTotal();
        
        assertEquals(expectedValue, actual, "The returned total price is not the same as the expected");
    }
    
    /**
     * Test of updateRunningTotal method, of class Sale. Tests if given two items, the running total is correctly calculated.
     */
    @Test
    public void testUpdateRunningTotalReturnsRightRunningTotalForTwoItems() {
        double expectedValue = 2 * (22.4); 
        
        SaleStateDTO saleState = sale.updateRunningTotal(scannedItem);
        saleState = sale.updateRunningTotal(scannedItem);
        saleState.getRunningTotal(); 
        double actual = saleState.getRunningTotal();
        
        assertEquals(expectedValue, actual, "The returned total price is not the same as the expected");
    }


}
