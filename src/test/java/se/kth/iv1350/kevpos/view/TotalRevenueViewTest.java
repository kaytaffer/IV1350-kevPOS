
package se.kth.iv1350.kevpos.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TotalRevenueViewTest {
    
    private double balance;
    private TotalRevenueView totalRevenueView;
    
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();


    
    @BeforeEach
    public void setUp() {
        balance =  20.30;
        totalRevenueView = new TotalRevenueView();
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        balance = 0;
        totalRevenueView = null;
        System.setOut(originalOut);
    }

    /**
     * Test of notifyObserversBalanceHasChanged method, is the right balane printed to the console.
     */
    @Test
    public void testNotifyObserversBalanceHasChanged() {
        String bal = "" + balance; 
        
        totalRevenueView.notifyObserversBalanceHasChanged(balance);
        String output = outContent.toString();
        boolean outputContainsBalance = output.contains(bal);
        assertTrue(outputContainsBalance, "The wrong balance is printed to system.out");
        
    }
    
}
