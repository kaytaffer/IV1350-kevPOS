
package se.kth.iv1350.kevpos.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;


public class RegisterTest {
    
    Register register;
    SaleStateDTO saleState;
    
    @BeforeEach
    public void setUp() {
        register = new Register();     
        saleState = new SaleStateDTO(null, 0, 20.0, null);
    }
    
    @AfterEach
    public void tearDown() {
        register = null;
        saleState = null;
    }

    /**
     * Test of calculateChange method, of class Register. Tests if the amount of change is correctly calculated
     */
    @Test
    public void testCalculateChange() {
        System.out.println("calculateChange");
        double paymentReceived = 76.22;
        double expResult = 56.22;
        double actual = register.calculateChange(saleState, paymentReceived).getChange();
        assertEquals(expResult, actual, "The calculated change is incorrect");
    }
    
        /**
     * Test of calculateChange method, of class Register. Tests if the amount of change is correctly calculated when given the maximum allowed amount.
     */
    @Test
    public void testCalculateChangeIfPaymentIsHuge() {
        System.out.println("calculateChange");
        double paymentReceived = Double.MAX_VALUE;
        double expResult = Double.MAX_VALUE - 20.0;
        double actual = register.calculateChange(saleState, paymentReceived).getChange();
        assertEquals(expResult, actual, "The calculated change is incorrect");
    }
    
}
