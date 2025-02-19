public class VendingSlot {
    private Item item;
    private int quantity;

    /**
     * Creates a VendingSlot object with an item and its quantity.
     */

    public VendingSlot(Item item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Gets the item from the VendingItem.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Gets the quantity of an item in the vending slot
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the price of an item
     * <p>
     * This method takes in the price parameter and assigns it to
     * the item of the user's choosing.
     * 
     * @param //price item price
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
