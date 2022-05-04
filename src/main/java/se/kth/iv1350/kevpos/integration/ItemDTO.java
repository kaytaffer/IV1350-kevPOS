package se.kth.iv1350.kevpos.integration;

/**
 * Data transfer object related to <code>Item</code>, used to pass immutable data between classes.
 */
public class ItemDTO {
	private final int identifier;
        private final double quantity;
	private final String name;
	private final String description;
	private final double price;
        private final double rateOfVAT;
        /**
         * 
         * @param identifier The item's numerical identifier.
         * @param quantity Amount of items of same type.
         * @param name The item's colloquial identifier.
         * @param description Any other relevant info about the item.
         * @param price The cost of a given item
         * @param rateOfVAT The item's tax rate, given between 0 and 1. 
         */
	public ItemDTO(int identifier, double quantity, String name, String description, double price, double rateOfVAT) {
		this.identifier = identifier;
                this.quantity = quantity;
                this.name = name;
                this.description = description;
                this.price = price;
                this.rateOfVAT = rateOfVAT;
	}

                /**
         * Get the <code>identifier</code> number of the <code>ItemDTO</code>
         * @return identifier number of item
         */
    public int getIdentifier() {
        return identifier;
    }
    
            /**
         * Get the <code>quantity</code> of the <code>ItemDTO</code>
         * @return quantity of item
         */
    public double getQuantity() {
        return quantity;
    }
    
            /**
         * Get the <code>name</code> of the <code>ItemDTO</code>
         * @return name of item
         */
    public String getName() {
        return name;
    }
    
    
            /**
         * Get a <code>description</code> of the <code>ItemDTO</code>
         * @return description of item
         */
    public String getDescription() {
        return description;
    }
    
        /**
         * Get the <code>price</code> of the <code>ItemDTO</code>
         * @return price of item
         */
    public double getPrice() {
        return price;
    }





        /**
         * Get the VAT rate of the <code>ItemDTO</code>
         * @return VAT rate of item
         */
    public double getRateOfVAT() {
        return rateOfVAT;
    }



}

