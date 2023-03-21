package se.kth.iv1350.kevpos.integration;

import java.util.ArrayList;
import se.kth.iv1350.kevpos.model.PaymentInfoDTO;
import se.kth.iv1350.kevpos.model.SaleStateDTO;

/**
 * Handles the logging of concluded <code>Sale</code>s and distributes relevant info to external systems.
 */
public class SalesLog {

    private AccountingHandler accountingHandler;
    private ArrayList<PaymentInfoDTO> paymentLog;
    private InventoryHandler inventoryHandler;
    private ArrayList<SaleStateDTO> salesLog;

    /**
     * Creates an instance of the <code>SalesLog</code>.
     * @param accountingHandler the handler of the external accounting system.
     * @param inventoryHandler the handler of the external inventory system.
     */
    public SalesLog(AccountingHandler accountingHandler, InventoryHandler inventoryHandler) {
        this.accountingHandler = accountingHandler;
        this.paymentLog = new ArrayList<>();
        this.inventoryHandler = inventoryHandler;
        this.salesLog = new ArrayList<>();
    }

    /**
     * Logs the concluded <code>Sale</code> and sends the info to the external databases.
     * @param saleState the final state of the <code>Sale</code>, ready to be concluded.
     * @param paymentInfo information about the exchanged cash.
     */
    public void logSale(SaleStateDTO saleState, PaymentInfoDTO paymentInfo) {
        accountingHandler.sendAccountingInfo(paymentInfo);
        paymentLog.add(paymentInfo);
        inventoryHandler.sendInventoryInfo(saleState);
        salesLog.add(saleState);
    }
}
