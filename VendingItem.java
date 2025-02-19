public class VendingItem extends Item {

    /**
     * Creates a VendingItem object that has a name, price, calories, and individualItem.
     */
    public VendingItem(String name, double price, int calories, boolean individualItem) {
        super(name, price, calories, individualItem);
    }

    /**
     * Overrides the getName() method from the Item class.
     * Gets the name of the item in the vending machine.
     */
    @Override
    public String getName() {
        return super.getName(); // You can provide a different implementation if needed
    }

    /**
     * Overrides the getPrice() method from the Item class.
     * Gets the price of the item in the vending machine.
     */
    @Override
    public double getPrice() {
        return super.getPrice(); // You can provide a different implementation if needed
    }

    /**
     * Overrides the isIndividualItem() method from the Item class.
     * Returns false for vending items since they cannot be sold individually.
     */
    @Override
    public boolean isIndividualItem() {
        return false;
    }
}

