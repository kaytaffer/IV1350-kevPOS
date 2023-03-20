package se.kth.iv1350.kevpos.controller;

/**
 * Thrown when the input from the user is not valid.
 */
public class InvalidInputException extends Exception {
    
    /**
     * an instance of the InvalidInputException.
     * @param originalException the causing deeper layer exception.
     */    
    public InvalidInputException(Exception originalException){
        super("ERROR: You've entered something wrong. Please try again.", originalException);
    }
}
