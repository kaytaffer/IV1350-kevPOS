package se.kth.iv1350.kevpos.controller;

/**
 * Thrown when there is some connection error.
 */
public class ConnectionFailedException extends RuntimeException{
    /**
     * an instance of the <code>ConnectionException</code>.
     * @param originalException the causing deeper layer exception.
     */
    public ConnectionFailedException(RuntimeException originalException){
        super("ERROR: Please reconnect and try again.", originalException);        
    }
    
}
