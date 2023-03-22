
package se.kth.iv1350.kevpos.model;

import java.util.ArrayList;
import se.kth.iv1350.kevpos.integration.ItemDTO;

/**
 * A data transfer object representing changes to the system, meant to be 
 * transferred to the <code>View</code>.
 */
public class SaleStateDTO {
    
    private final ItemDTO scannedItemDTO;
    private final double totalVAT;
    private final double runningTotal;
    private final ArrayList<Item> listOfItems;

    /**
     * Creates an instance of a <code>SaleStateDTO</code>. 
     * @param scannedItemDTO the latest scanned item.
     * @param totalVAT the current total VAT of the <code>Sale</code> in progress.
     * @param runningTotal the current total price of the <code>Sale</code> in progress.
     * @param listOfItems the current <code>Items</code>s in the <code>CheckoutCart</code>
     */
    public SaleStateDTO(ItemDTO scannedItemDTO, double totalVAT, 
            double runningTotal, ArrayList<Item> listOfItems){
        this.scannedItemDTO = scannedItemDTO;
        this.totalVAT = totalVAT;
        this.runningTotal = runningTotal;
        this.listOfItems = listOfItems;
    }
    
    /**
     * Get the <code>scannedItemDO</code>.
     * @return the latest scanned item.
     */
    public ItemDTO getScannedItemDTO() {
        return scannedItemDTO;
    }
    
    /**
     * Get the <code>currentTotalVAT</code> of the <code>Sale</code>.
     * @return the running total price
     */
        public double getTotalVAT() {
        return totalVAT;
    }
    
    /**
     * Get the current <code>runningTotal</code> price of the  <code>Sale</code>.
     * @return the running total price
     */
    public double getRunningTotal() {
        return runningTotal;
    }

    /**
     * Get the <code>listOfItems</code> in the current cart.
     * @return the current shopping list.
     */
    public final ArrayList<Item> getListOfItems() {
        return listOfItems;
    } 
}
