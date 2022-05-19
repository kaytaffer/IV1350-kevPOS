
package se.kth.iv1350.kevpos.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ObserverTester implements RegisterObserver{
    @Override
    public void notifyObserversBalanceHasChanged(double balance) {
        System.out.println("works");
    }
}

public class RegisterTest {
    
    Register register;
    SaleStateDTO saleState;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        register = Register.getRegister();     
        saleState = new SaleStateDTO(null, 0, 20.0, null);
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        register = null;
        saleState = null;
        System.setOut(originalOut);
    }

    /**
     * Test of calculateChange method, of class Register. Tests if the amount of change is correctly calculated
     */
    @Test
    public void testCalculateChange() {
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
        double paymentReceived = Double.MAX_VALUE;
        double expResult = Double.MAX_VALUE - 20.0;
        double actual = register.calculateChange(saleState, paymentReceived).getChange();
        assertEquals(expResult, actual, "The calculated change is incorrect");
    }
    
    /**
     * Test of addRegisterObserver method. Tests if a <code>RegisterObserver</code> actually is added to the list.
     */
    @Test
    public void testIsAnObserverAdded (){
        
        ObserverTester observerTester = new ObserverTester();
        register.addRegisterObserver(observerTester);        
        
        testCalculateChange();
        
        String output = outContent.toString();
        boolean outputContainsBalance = output.contains("works");
        assertTrue(outputContainsBalance, "The observer wasn't added to registerObserver");
    }
}
