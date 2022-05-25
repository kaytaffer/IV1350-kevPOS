
package se.kth.iv1350.kevpos.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.kevpos.controller.Controller;
import se.kth.iv1350.kevpos.integration.AccountingHandler;
import se.kth.iv1350.kevpos.integration.DiscountHandler;
import se.kth.iv1350.kevpos.integration.InventoryHandler;
import se.kth.iv1350.kevpos.integration.ItemDTO;
import se.kth.iv1350.kevpos.integration.SalesLog;
import se.kth.iv1350.kevpos.model.Item;
import se.kth.iv1350.kevpos.model.ReceiptDTO;
import se.kth.iv1350.kevpos.model.Register;
import se.kth.iv1350.kevpos.model.SaleStateDTO;


public class ViewTest {

    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private AccountingHandler accountingHandler;
    private InventoryHandler inventoryHandler;
    private DiscountHandler discountHandler;
    private SalesLog salesLog;
    private Controller controller;
    private ItemDTO itemRequest;    
    private View view;
    private SaleStateDTO saleState;
    private ReceiptDTO receipt;
    
    
    @BeforeEach
    public void setUp() {
        
        inventoryHandler = new InventoryHandler();
        discountHandler = new DiscountHandler();
        accountingHandler = new AccountingHandler();
        salesLog = new SalesLog(accountingHandler, inventoryHandler);
        Register register = Register.getRegister();
        controller = new Controller(discountHandler, inventoryHandler, register, salesLog);
        view = new View(controller);
        itemRequest = new ItemDTO(3, 1, null, null, 0, 0);
        controller.startNewSale();

        
        System.setOut(new PrintStream(outContent));        
    }
    
    @AfterEach
    public void tearDown() {
        inventoryHandler = null;
        discountHandler = null;
        accountingHandler = null;
        salesLog = null;
        controller = null;
        view = null;
        itemRequest = null;
        saleState = null;
        saleState = null;
        receipt = null;
        
        System.setOut(originalOut);
    }

    /**
     * Test of viewExecute method, which is View's only public method. Tests 
     * specifically that the error message for an invalid item id is written.
     */
    @Test
    public void testViewExecuteErrorInvalidInput() {
        String expected = "ERROR: You've entered something wrong. Please try again.";
        
        view.viewExecute();
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expected);
        assertTrue(outputContainsMessage, "The InvalidInputExceptionMessage is not printed to System.out");
    }
    
        /**
     * Test of viewExecute method, which is View's only public method. Tests 
     * specifically that the error message for an invalid item id is written.
     */
    @Test
    public void testViewExecuteErrorDatabaseConnectionFailure() {
        String expected = "ERROR: Please reconnect and try again.";
        
        view.viewExecute();
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expected);
        assertTrue(outputContainsMessage, "The InvalidInputExceptionMessage is not printed to System.out");
    }
    
    @Test
    public void testViewIfScannedItemDTOIsPrintable(){
        try{
            saleState = controller.nextItem(itemRequest);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        
        String expectedName = saleState.getScannedItemDTO().getName();
        String expectedDescription = saleState.getScannedItemDTO().getDescription();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedName);
        assertTrue(outputContainsMessage, "Item name is not printed to System.out");
        outputContainsMessage = output.contains(expectedDescription);
        assertTrue(outputContainsMessage, "Item description is not printed to System.out");
    }
    
    @Test
    public void testViewIfSaleStateVATisPrinted(){
        try{
            saleState = controller.nextItem(itemRequest);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        
        String expectedVAT = "" + saleState.getTotalVAT();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedVAT);
        assertTrue(outputContainsMessage, "TotalVAT is not printed to System.out");
    }
    
    @Test
    public void testViewIfSaleStateRunningTotalIsPrinted(){
        try{
            saleState = controller.nextItem(itemRequest);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedRunningTotal = "" + saleState.getRunningTotal();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedRunningTotal);
        assertTrue(outputContainsMessage, "TotalVAT is not printed to System.out");
    }
    
    @Test
    public void testViewIfReceiptTimeForSaleIsPrintedCorrectly(){
        int secondBeforeTest = 62; 
        int expectedSecond = 61;
        int minuteAfterTest = 63;
        
        while (secondBeforeTest != expectedSecond){
            secondBeforeTest = LocalDateTime.now().getSecond();
            try{
                double paymentReceived = 60;
                controller.nextItem(itemRequest);
                receipt = controller.concludeSale(paymentReceived);
            }
            catch(Exception e){
                fail("Some exception was thrown when it wasn't expected");
            }

            view.viewExecute();
            
            minuteAfterTest = receipt.getTimeForSale().getMinute();
            expectedSecond = receipt.getTimeForSale().getSecond();
        }
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains("" + minuteAfterTest + ":" + expectedSecond);
        assertTrue(outputContainsMessage, "Time at concluded sale is not printed to System.out");
    }
    
    @Test
    public void testViewIfReceiptTotalPriceIsPrintedCorrectly(){
        try{
            double paymentReceived = 60;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(paymentReceived);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedTotalPrice = "" + receipt.getTotalPrice();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedTotalPrice);
        assertTrue(outputContainsMessage, "Total price at concluded sale is not printed to System.out");
    }
    
    @Test
    public void testViewIfReceiptItemIsPrintedCorrectly(){
        try{
            double paymentReceived = 60;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(paymentReceived);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        
        ArrayList<Item> listOfItems = receipt.getListOfBoughtItems();
        Item expectedItem = listOfItems.get(0);
        String expectedItemRepresentation = "" + expectedItem.getQuantity() + "  " + expectedItem.getName() + "  " + expectedItem.getDescription() + "  " + expectedItem.getPrice()*expectedItem.getQuantity();
           
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedItemRepresentation);
        assertTrue(outputContainsMessage, "The bought Item isn't written to the receipt at concluded sale is not printed to System.out");
    }
    
        @Test
    public void testViewIfReceiptDiscountIsPrintedCorrectly(){
        try{
            double paymentReceived = 60;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(paymentReceived);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedDiscount = "" + receipt.getDiscount();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedDiscount);
        assertTrue(outputContainsMessage, "Discount at concluded sale is not printed to System.out");
    }
            @Test
    public void testViewIfReceiptPaymentIsPrintedCorrectly(){
        try{
            double paymentReceived = 60;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(paymentReceived);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedPayment = "" + receipt.getReceivedPayment();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedPayment);
        assertTrue(outputContainsMessage, "The payment at concluded sale is not printed to System.out");
    }
            @Test
    public void testViewIfReceiptChangeIsPrintedCorrectly(){
        try{
            double paymentReceived = 60;
            controller.nextItem(itemRequest);
            receipt = controller.concludeSale(paymentReceived);
        }
        catch(Exception e){
            fail("Some exception was thrown when it wasn't expected");
        }
        String expectedChange = "" + receipt.getChange();
        
        view.viewExecute();
        
        String output = outContent.toString();
        boolean outputContainsMessage = output.contains(expectedChange);
        assertTrue(outputContainsMessage, "The change after payment at concluded sale is not printed to System.out");
    }
    
}
