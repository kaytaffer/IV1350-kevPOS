
# kevPOS
Code and tests for a Point-Of-Sale program in a course for Object Oriented Design at KTH

# Design/
UML design documents related to the project

# src/main/

## controller

`ConnectionFailedException.java` - Thrown when there is some connection error.

`Controller.java` - Handles all calls from the `View` to the `Model`. This is the application's only controller.

`InvalidInputException.java` - Thrown when the input from the user is not valid.

## integration

`AccountingHandler.java` - 

`DatabaseUnreachableException.java` - 

`DiscountHandler.java` - 

`InventoryHandler.java` - 

`ItemDTO.java` - 

`ItemNotFoundException.java` - 

`SalesLog.java` - 

`TotalRevenueFileOutput.java` - 

## model

`CheckoutCart.java` - 

`Item.java` - 

`PaymentInfoDTO.java` - 

`RecepitDTO.java` - 

`Register.java` - 

`RegisterObserver.java` - 

`Sale.java` - 

`SaleStateDTO.java` - 

## startup

`Main.java` - Starts the entire application and sets up initial classes for later use.

## util/

`ExceptionLogger.java` - 

`FileOutputter.java` - 

## view

`TotalRevenueView.java` - 

`View.java` - 

# src/test

Unit tests for the classes in the source code.