
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

`SalesLog.java` - handles the logging of concluded `Sale`s and distributes relevant info to external systems.

`TotalRevenueFileOutput.java` - handles the logging to a file, of the total revenue since the start of the application.

## model

`CheckoutCart.java` - Contains and manages `ItemDTO`s for the current `Sale`.

`Item.java` - Represents an item to be bought at the point-of-sale, to be added to a `CheckoutCart`.

`PaymentInfoDTO.java` - A DTO concerning information about the exchange of cash.

`RecepitDTO.java` - All final info regarding, and proof of, the concluded `Sale`.

`Register.java` - Handles all logic concerning the exchange of cash at the conclusion of a `Sale`.

`RegisterObserver.java` - Observer interface that informs classes that implements it that the `Register` contains a new balance.

`Sale.java` - Contains information about the current state of the `Sale` in progress.

`SaleStateDTO.java` - A data transfer object representing changes to the system, meant to be transferred to the `View`.

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