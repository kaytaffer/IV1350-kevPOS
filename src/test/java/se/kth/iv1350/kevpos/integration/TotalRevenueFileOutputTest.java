
package se.kth.iv1350.kevpos.integration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TotalRevenueFileOutputTest {
    
    private double balance;
    private TotalRevenueFileOutput totalRevenueFileOutput;
    private FileReader reader; 
    
    @BeforeEach
    public void setUp() {
        balance =  40.30;
        totalRevenueFileOutput = new TotalRevenueFileOutput();
    }
    
    @AfterEach
    public void tearDown() {
        balance = 0;
        totalRevenueFileOutput = null;
    }

    /**
     * Test of notifyObserversBalanceHasChanged method, is the right balance printed to the file.
     */
    @Test
    public void testNotifyObserversBalanceHasChanged() {
        StringBuilder sb = new StringBuilder();
        String bal = "" + balance; 
        totalRevenueFileOutput.notifyObserversBalanceHasChanged(balance);
        
        try {
            reader = new FileReader("totalRevenue.txt");
            
            int data = reader.read();
            while (data != -1){
                data = reader.read();
                sb.append((char) data);
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();   
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        String output = sb.toString();
                
        boolean outputContainsBalance = output.contains(bal);
        assertTrue(outputContainsBalance, "The wrong balance is printed to output file");
        
    }
    
}
