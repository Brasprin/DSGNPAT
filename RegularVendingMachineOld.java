import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * The class Regular vending machine
 */
public class RegularVendingMachine extends JFrame {
    protected Map<Integer, Item> items; // Change the map type to Item

    protected Map<Integer, Integer> stock;
    private Map<Double, Integer> money;

    private boolean machineCreated; // Add the machineCreated field

    protected double totalSales;
    private double initialSales;
    private double initialMoney;
    protected double currentMoney;
    protected boolean isMachineCreated;

    protected double userBalance; // New instance variable to keep track of the user's balance

    /**
     * creates RegularVendingMachine object
     * <p>
     * This constructor assigns variables and gives them their values.
     */
    public RegularVendingMachine() {

        items = new HashMap<>();
        stock = new HashMap<>();
        money = new HashMap<>();
        totalSales = 0;
        initialSales = 0;
        initialMoney = 0;
        currentMoney = 0;
        isMachineCreated = true; // Set the flag to true after all setup is done
    }

    /**
     * Creates a Regular Vending Machine based on the number of slots and capacity.
     * <p>
     * Creates a Regular Vending Machine based on the number of slots
     * and capacity the user inputs. Then asks the user what items would the user like
     * to store, set the price of the item, and input how many calories each item has.
     *
     * @param scanner user input
     */
    public void createRegularVendingMachine() {

        int slots;
        int capacity;

        do {
            //System.out.print("Enter the number of slots (must be at least 8): ");
            //slots = scanner.nextInt();
            String presetChoice = JOptionPane.showInputDialog(this, "Enter the number of slots (must be at least 8): ");
            slots = Integer.parseInt(presetChoice);
        } while (slots < 8);

        do {
            //System.out.print("Enter the capacity per slot (must be at least 10): ");
            //capacity = scanner.nextInt();
            String presetChoice = JOptionPane.showInputDialog(this, "Enter the capacity per slot (must be at least 10): ");
            capacity = Integer.parseInt(presetChoice);
        } while (capacity < 10);

        for (int i = 1; i <= slots; i++) {
            String itemName;
            double itemPrice;
            int itemCalories;

            JOptionPane.showMessageDialog(this, "\n--- Slot " + i + " ---");
            //System.out.print("Enter the item name: ");
            //scanner.nextLine(); // Consume the newline character left by previous nextInt()
            //itemName = scanner.nextLine(); // Use nextLine() to read the whole line with spaces
            itemName = JOptionPane.showInputDialog(this, "Enter the item name: ");


            //System.out.print("Enter the item price: $");
            //itemPrice = scanner.nextDouble();
            String itemPriceInput = JOptionPane.showInputDialog(this, "Enter the item price: $");
            itemPrice = Double.parseDouble(itemPriceInput);
            //System.out.print("Enter the item calories: ");
            //itemCalories = scanner.nextInt();
            String itemCaloriesInput = JOptionPane.showInputDialog(this, "Enter the item calories: ");
            itemCalories = Integer.parseInt(itemCaloriesInput);

            addItem(i, itemName, itemPrice, itemCalories, true);
            stock.put(i, capacity);
        }

        isMachineCreated = true;
        JOptionPane.showMessageDialog(this, "\nRegular Vending Machine created successfully!");
    }

    /**
     * Testing function of the vending machine
     * <p>
     * This method tests the functions of the vending machine. It checks if there is
     * a vending machine made and will act accordingly based on user input.
     *
     * @param scanner user input
     */
    public void testVendingMachine(Scanner scanner) {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null,"Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }

        double prevTotalSales = totalSales; // Step 1: Store the current totalSales
        displayItems();
        displayCalories();

        // Step 1: Ask the user to input a denomination and update the user balance
        double denomination;
        do {
            String cash = JOptionPane.showInputDialog(null, "Enter a denomination (0.1, 0.5, 1, 5, 10, 20, 50): ");
            denomination = Double.parseDouble(cash);
        } while (denomination != 0.1 && denomination != 0.5 && denomination != 1 &&
                denomination != 5 && denomination != 10 && denomination != 20 && denomination != 50);

        userBalance += denomination; // Update user's balance


// Step 2: Show available items and their prices
        displayItems();
        displayCalories();

        String slotNum = JOptionPane.showInputDialog(null, "Enter the slot number of the item you want to purchase: ");
        int slotNumber = Integer.parseInt(slotNum);

        if (!items.containsKey(slotNumber)) {
            JOptionPane.showMessageDialog(null,"Invalid slot number. Please try again.");
            return;
        }

        Item item = (Item) items.get(slotNumber);

        if (stock.get(slotNumber) == 0) {
            JOptionPane.showMessageDialog(null,"This item is out of stock. Please choose another item.");
            return;
        }

        JOptionPane.showMessageDialog(null,"You have selected: " + item.getName());
        // Step 4: Check if the user has enough balance to purchase the selected item
        if (userBalance < item.getPrice()) {
            JOptionPane.showMessageDialog(null,"Insufficient balance. Please insert more money or choose another item.");
            return;
        }

        // Step 5: Proceed with the purchase, update user balance, and provide change (if any)
        double change = userBalance - item.getPrice();
        userBalance = 0; // Reset user balance after the purchase
        stock.put(slotNumber, stock.get(slotNumber) - 1);
        totalSales += item.getPrice();

        JOptionPane.showMessageDialog(null,"Dispensing " + item.getName() + "...");
        JOptionPane.showMessageDialog(null,"Change: $" + String.format("%.2f",change));

        // Step 2: Update currentMoney to be the current balance (userBalance) after the purchase
        currentMoney += userBalance;
        // Step 3: Calculate the collectedMoney and add it to currentMoney
        double collectedMoney = totalSales - prevTotalSales;
        currentMoney += collectedMoney;
        // Step 4: Update initialSales to be the current totalSales
        totalSales += prevTotalSales;

       JOptionPane.showMessageDialog(null, "\nRegular Vending Machine transaction completed!");
    }

    /**
     * Displays the items.
     * <p>
     * This method creates a screen where it shows the items
     * on display for the customer to purchase from.
     */
    protected void displayItems() {

        JOptionPane.showMessageDialog(null,"--- Available Items ---");
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = entry.getValue();

            // Check if the item is not meant to be sold individually
            if (!isIndividualItem(slotNumber)) {
                JOptionPane.showMessageDialog(null, slotNumber + ". " + item.getName() + " - Stock: " + stock.get(slotNumber) + " (Can only be purchased as part of a customized product)");
            } else {
                JOptionPane.showMessageDialog(null, slotNumber + ". " + item.getName() + " - Stock: " + stock.get(slotNumber) + " - Price: $" + item.getPrice());
            }
        }
    }



    /*
     * Restocks the items based on the user input.
     * <p>
     * This method takes in the slot number and restocks the item slot based on the user's input.
     *
     * @param scanner user input
     */

    /**
     *
     * Restock items
     *
     * @param scanner  the scanner.
     */
    public void restockItems(Scanner scanner) {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null,"Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }

        JOptionPane.showMessageDialog(null,"\n--- Restocking Items ---");

        double prevTotalSales = totalSales; // Step 1: Store the current totalSales
        double prevCurrentMoney = currentMoney; // Step 1: Store the current currentMoney

        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = (Item) entry.getValue();
            displayItems();
            String stockQty = JOptionPane.showInputDialog(null, "Enter the quantity to restock for slot " + slotNumber + " (" + item.getName() + "): ");
            int quantity = Integer.parseInt(stockQty);

            stock.put(slotNumber, stock.get(slotNumber) + quantity);
        }

        // Step 3: Calculate the collectedMoney and add it to currentMoney
        double collectedMoney = totalSales - initialSales;
        currentMoney += collectedMoney;
        // Step 4: Update initialSales to be the current totalSales
        initialSales = totalSales;


        JOptionPane.showMessageDialog(null,"\nItems restocked successfully!");
    }

    /**
     * Sets the price of an item.
     * <p>
     * This method sets the price of an item based on the user input.
     */
    public void setItemPrice(Scanner scanner) {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null, "Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }

        JOptionPane.showMessageDialog(null,"\n--- Setting Item Price ---");

        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = (Item) entry.getValue();
            displayItems();
            String price = JOptionPane.showInputDialog(null,"Enter the new price for slot " + slotNumber + " (" + item.getName() + "): $");
            double newPrice = Double.parseDouble(price);

            item.setPrice(newPrice);
        }

        JOptionPane.showMessageDialog(null,"\nItem prices updated successfully!");
    }

    /**
     * Collects the money from the vending machine.
     * <p>
     * This method collects the money made from the sales of the vending machine.
     */
    public void collectMoney() {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null,"Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }

        JOptionPane.showMessageDialog(null,"\n--- Collecting Money ---");

        if (totalSales == initialSales) {
            JOptionPane.showMessageDialog(null,"No new sales since the last restocking. Nothing to collect.");
            return;
        }

        double collectedMoney = totalSales - initialSales;
        currentMoney += collectedMoney;
        totalSales = initialSales;

        JOptionPane.showMessageDialog(null,"Collected money: $" + collectedMoney);
        JOptionPane.showMessageDialog(null,"Total money in machine: $" + currentMoney);
    }

    /*
     * Replenishes the change of the vending machine.
     * <p>
     * This method allows the user the replenish the money
     * stored in the vending machine in denomination quantities.
     * @param scanner user input of change
     */

    /**
     *
     * Replenish change
     *
     * @param scanner  the scanner.
     */
    public void replenishChange(Scanner scanner) {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null, "Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }

        JOptionPane.showMessageDialog(null, "\n--- Replenishing Change ---");

        String change = JOptionPane.showInputDialog(null,"Enter the denomination of the change to replenish: $");
        double denomination = Double.parseDouble(change);

        String changeQty = JOptionPane.showInputDialog(null,"Enter the quantity of " + denomination + " dollar bills to replenish: ");
        int quantity = Integer.parseInt(changeQty);

        money.put(denomination, money.getOrDefault(denomination, 0) + quantity);

        JOptionPane.showMessageDialog(null,"Change replenished successfully!");
    }

    /**
     * Displays the calories of an item
     * <p>
     * This method displays the calories of the items in the vending machine.
     */
    public void displayCalories() {

        JOptionPane.showMessageDialog(null,"Calories per item:");
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = (Item) entry.getValue();
            JOptionPane.showMessageDialog(null,"Slot " + slotNumber + ": " + item.getName() + " - " + item.getCalories() + " calories");
        }
    }

    /*
     * Prints a summary of transactions done by the vending machine.
     * <p>
     * This method prints a detailed transaction summary to help see how many sales the vending machine made.
     */

    /**
     *
     * Print transaction summary
     *
     */
    public void printTransactionSummary() {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null,"Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }

        JOptionPane.showMessageDialog(null,"\n--- Transaction Summary ---");
        JOptionPane.showMessageDialog(null,"Total Sales: $" + totalSales);
        JOptionPane.showMessageDialog(null,"Starting Inventory:");

        for (Map.Entry<Integer, Item> entry : items.entrySet()){
            int slotNumber = entry.getKey();
            Item item = (Item) (Item) entry.getValue();
            int initialStock = stock.get(slotNumber);

            JOptionPane.showMessageDialog(null,slotNumber + ". " + item.getName() + " - Stock: " + initialStock);
        }

        JOptionPane.showMessageDialog(null,"\nEnding Inventory:");

        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = (Item) entry.getValue();
            int currentStock = stock.get(slotNumber);

            JOptionPane.showMessageDialog(null, slotNumber + ". " + item.getName() + " - Stock: " + currentStock);
        }
    }



    /**
     *
     * Dispense item
     *
     * @param slotNumber  the slot number.
     */
    public void dispenseItem(int slotNumber) {

        // Check if the item is not meant to be sold individually
        Item item = (Item) items.get(slotNumber); // .getItem was replaced with a qualifier
        if (!item.isIndividualItem()) {
            JOptionPane.showMessageDialog(null,"This item can only be purchased as part of a customized product.");
            return;
        }

    }



    /**
     *
     * Add item
     *
     * @param slotNumber  the slot number.
     * @param itemName  the item name.
     * @param itemPrice  the item price.
     * @param itemCalories  the item calories.
     * @param individualItem  the individual item.
     */
    public void addItem(int slotNumber, String itemName, double itemPrice, int itemCalories, boolean individualItem) {

        Item item = new Item(itemName, itemPrice, itemCalories, individualItem);
        items.put(slotNumber, item);
    }


    /**
     *
     * Is individual item
     *
     * @param slotNumber  the slot number.
     * @return boolean
     */
    public boolean isIndividualItem(int slotNumber) {

        Item item = items.get(slotNumber);
        return item != null && item.isIndividualItem();
    }



    // Method to add preset items to the Vending Machine

    /**
     *
     * Add preset items
     *
     */
    public void addPresetItems() {

        addItem(1, "Item 1", 1.99, 100, true);
        addItem(2, "Item 2", 2.49, 150, true);
        addItem(3, "Item 3", 0.99, 80, true);
        // ... Add more preset items as needed

        // Set the initial stock values for the preset items
        stock.put(1, 10);
        stock.put(2, 10);
        stock.put(3, 10);
        // ... Add more initial stock values as needed
    }



    // Add a setMachineCreated method to set the machineCreated flag

    /**
     *
     * Sets the machine created
     *
     * @param machineCreated  the machine created.
     */
    public void setMachineCreated(boolean machineCreated) {
        this.machineCreated = machineCreated;
    }

    // Add a getter method for the isMachineCreated flag

    /**
     *
     * Is machine created
     *
     * @return boolean
     */
    public boolean isMachineCreated() {
        return this.machineCreated;
    }



    /**
     *
     * Add component
     *
     * @param name  the name.
     * @param price  the price.
     * @param calories  the calories.
     * @param individualItem  the individual item.
     */
    public void addComponent(String name, double price, int calories, boolean individualItem) {

        Item item = new Item(name, price, calories, individualItem); // Create a new Item with the provided details
        items.put(items.size() + 1, item); // Put the item into the items map
    }



    /**
     *
     * Display denominations
     *
     */
    public void displayDenominations() {
        JOptionPane.showMessageDialog(null,"--- Available Denominations ---");
        JOptionPane.showMessageDialog(null,"0.5, 1, 5, 10, 20, 50");
    }


}