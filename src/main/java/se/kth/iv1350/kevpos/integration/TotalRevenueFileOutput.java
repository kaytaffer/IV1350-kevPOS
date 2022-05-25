
package se.kth.iv1350.kevpos.integration;

import java.io.FileWriter;
import java.io.IOException;
import se.kth.iv1350.kevpos.model.RegisterObserverTemplate;

/**
 * Handles the logging to a file, of the total revenue since the start of the
 * application
 */
public class TotalRevenueFileOutput extends RegisterObserverTemplate{
    
    /**
     * Creates an instance of the <code>TotalRevenueFileOutput</code>
     */
    public TotalRevenueFileOutput(){

    }
    
    /**
     * Shows the total income, the current balance in the <code>Register<code> 
     * to a file.
     * @param balance The current total income.
     * @throws java.io.IOException if something went wrong when trying to write
     * to the file.
     */
    @Override
    protected void doShowTotalIncome(double balance) throws IOException{ 
        FileWriter writer = new FileWriter("totalRevenue.txt", true);
        writer.append("Today's total revenue is: " + balance + " money units.\n");
        writer.close();                
    } 

    /**
     * Handles any thrown exceptions by printing the stack trace.
     * @param exception The thrown error to be handled
     */
    @Override
    protected void handleErrors (Exception exception)
    {
        exception.printStackTrace();
    }
}

