package se.kth.iv1350.kevpos.integration;

/**
 * thrown when an <code>ItemDTO</code>'s <code>identifier</code> does not match
 * an identifier in the inventory database.
 */
public class ItemNotFoundException extends Exception{

    /**
     * an instance of the <code>ItemNotFoundException</code>.
     * @param itemRequestDTO the <code>ItemDTO</code> which doesn't have a match.
     */    
    public ItemNotFoundException(ItemDTO itemRequestDTO){
        super("ERROR: Item with ID " + itemRequestDTO.getIdentifier() + " could not be found.");
    }
}
