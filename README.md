
# kevPOS
Code and tests for a Point-Of-Sale program in the course IV1350: Object Oriented Design at KTH.

# Design/
UML design documents related to the project

# src/main/

## controller

`ConnectionFailedException.java` - Thrown when there is some connection error.

`Controller.java` - Handles all calls from the `View` to the `Model`. This is the application's only controller.

`InvalidInputException.java` - Thrown when the input from the user is not valid.

## integration

`AccountingHandler.java` - a placeholder class representing an interface to an external system.

`DatabaseUnreachableException.java` - Thrown when a database could not be reached.

`DiscountHandler.java` - unimplemented class to handle discount requests.

`InventoryHandler.java` - a placeholder class representing an interface to an external system.

`ItemDTO.java` - data transfer object related to `Item`, used to pass immutable data between classes.

`ItemNotFoundException.java` - thrown when an `ItemDTO`'s `identifier` does not match an identifier in the inventory database.

`SalesLog.java` - Handles the logging of concluded `Sale`s and distributes relevant info to external systems.

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