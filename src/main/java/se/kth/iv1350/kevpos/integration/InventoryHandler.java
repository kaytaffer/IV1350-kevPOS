package se.kth.iv1350.kevpos.integration;

import java.util.ArrayList;
import se.kth.iv1350.kevpos.model.Item;
import se.kth.iv1350.kevpos.model.SaleStateDTO;

/**
 * A placeholder class representing an interface to an external system. The
 * database is here hardcoded in the <code>fakeItemList</code>.
 */
public class InventoryHandler {

    private final int lengthOfFakeItemList = 6;
    private final ItemDTO[] fakeItemList = new ItemDTO[lengthOfFakeItemList]; 
    /**
     * Creates an instance of the <code>InventoryHandler</code>.
     */
    public InventoryHandler() {
        fakeItemList[0] = new ItemDTO(0, 0, "nothing", "no description", 0, 0);
        fakeItemList[1] = new ItemDTO(1, 0, "Coffee", "perky", 25.0, 0.12);
        fakeItemList[2] = new ItemDTO(2, 0, "Fisherman's friend", "laxative", 12.5, 0.12);
        fakeItemList[3] = new ItemDTO(3, 0, "Book", "cool", 50.0, 0.06);
        fakeItemList[4] = new ItemDTO(4, 0, "Teddy", "greenish yellowish brown", 6.0, 0.25);
        fakeItemList[5] = new ItemDTO(5, 0, "Chocolate", "tasty", 20.0, 0.12);
    }

    /**
     * Matches an <code>ItemDTO</code> <code>identifier</code> with all its available data.
     * @param itemRequest a proto-item, all values null except for <code>identifier</code>.
     * and <code>quantity</code>.
     * @return a completed <code>ItemDTO</code> matched to the given <code>identifier</code>. If an 
     * invalid ID is attached to the <code>itemRequest</code>, a null valued <code>ItemDTO</code> 
     * is returned.
     * @throws DatabaseUnreachableException when a database cannot be reached.
     * @throws ItemNotFoundException Thrown when an <code>ItemDTO</code>:s <code>identifier</code> does not match
     * an identifier in the inventory database.
     */
    public ItemDTO constructItemDTO(ItemDTO itemRequest) throws DatabaseUnreachableException, ItemNotFoundException{
        int itemIdentifier = itemRequest.getIdentifier();
        
        connectToInventoryDatabase(itemRequest);
        try{
        ItemDTO scannedItem = new ItemDTO(itemIdentifier, itemRequest.getQuantity(), fakeItemList[itemIdentifier].getName(), 
                fakeItemList[itemIdentifier].getDescription(), fakeItemList[itemIdentifier].getPrice(), 
                fakeItemList[itemIdentifier].getRateOfVAT());
        return scannedItem;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            throw new ItemNotFoundException(itemRequest);
        }
    }
    
    //Placeholder connection attempt to inventory database
    private void connectToInventoryDatabase(ItemDTO itemRequest) throws DatabaseUnreachableException {
        int nonexistingIdentifier = 404;
        if(itemRequest.getIdentifier() == nonexistingIdentifier)
            throw new DatabaseUnreachableException();
    }
   
    /**
     * Placeholder for the call to log all inventory changes in the external inventory system.
     * @param saleState the finalized info about the <code>Sale</code>.
     */
    public void sendInventoryInfo(SaleStateDTO saleState) {
        ArrayList<Item> listOfItemsBought = saleState.getListOfItems();
        Item m;
        for (Item i : listOfItemsBought) {
            m = i;
            i = m;
        }
    }
}
