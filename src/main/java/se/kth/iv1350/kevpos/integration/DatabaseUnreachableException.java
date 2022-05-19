

package se.kth.iv1350.kevpos.integration;

/**
 * Thrown when a database could not be reached.
 */
public class DatabaseUnreachableException extends RuntimeException{
    
    /**
     * an instance of the <code>DatabaseUnreachableException</code>.
     */
    public DatabaseUnreachableException(){
        super("The database can't be reached.");      
    }
    
}
