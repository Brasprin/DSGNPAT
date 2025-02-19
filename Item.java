 /**
 * The class Item
 */ 
public class Item {
    private String name;
    private double price;
    private int calories;
    private boolean individualItem; // Indicates whether the item can be sold individually

    /**
     * Creates Item object with name, price, and calories
     *
     * @param name           a label for the item
     * @param price          dictates the cost
     * @param calories       dictates the nutritional energy
     * @param individualItem dictates if item can be sold individually
     */
    public Item(String name, double price, int calories, boolean individualItem) { 

        this.name = name;
        this.price = price;
        this.calories = calories;
        this.individualItem = individualItem;
    }

    /**
     * Returns the name of an item.
     * <p>
     * This method would return the name of an item.
     *
     * @return name of the item
     */
    public String getName() { 

        return name;
    }

    /**
     * Returns the price of an item.
     * <p>
     * This method would return the price of an item.
     *
     * @return price of the item
     */
    public double getPrice() { 

        return price;
    }

    /**
     * Returns the calories of an item.
     * <p>
     * This method would return the calories of an item.
     *
     * @return calories of the item
     */
    public int getCalories() { 

        return calories;
    }

    /**
     * Set the price of an item.
     * <p>
     * This method would set the price of the item.
     */
    public void setPrice(double price) { 

        this.price = price;
    }


/** 
 *
 * Is individual item
 *
 * @return boolean
 */
    public boolean isIndividualItem() { 

        return individualItem;
    }
}
