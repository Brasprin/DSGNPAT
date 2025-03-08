import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class SpecialVendingMachine extends RegularVendingMachine {

    private Map<Integer, VendingSlot> components;
    private boolean machineCreated;

    public SpecialVendingMachine() {

        super();
        components = new HashMap<>();
        machineCreated = true;
    }

    public void addComponent(Item item, int quantity) {

        VendingSlot slot = new VendingSlot(item, quantity);
        components.put(components.size() + 1, slot);
    }

    private void displayComponents() {

        JOptionPane.showMessageDialog(null,"--- Available Components ---");
        for (Map.Entry<Integer, VendingSlot> entry : components.entrySet()) {
            int slotNumber = entry.getKey();
            VendingSlot slot = entry.getValue();
            Item item = slot.getItem();
            int componentStock = slot.getQuantity();

            JOptionPane.showMessageDialog(null, slotNumber + ". " + item.getName() + " - Stock: " + componentStock);
        }
    }

    @Override

    public void dispenseItem(int slotNumber) {

        // Check if the item is not meant to be sold individually
        if (!isIndividualItem(slotNumber)) {
            JOptionPane.showMessageDialog(null, "This item can only be purchased as part of a customized product.");
            return;
        }

        // Check if the item is in stock
        if (stock.get(slotNumber) == 0) {
            JOptionPane.showMessageDialog(null, "Item out of stock!");
            return;
        }

        // Get the item from the slot
        Item item = items.get(slotNumber);

        // Check if user balance is sufficient
        if (userBalance < item.getPrice()) {
            JOptionPane.showMessageDialog(null, "Insufficient balance. Please insert more coins.");
            return;
        }

        // Deduct item price from user balance and update stock
        userBalance -= item.getPrice();
        stock.put(slotNumber, stock.get(slotNumber) - 1);

        // Calculate and display the change
        double change = userBalance;
        userBalance = 0; // Reset user balance after the purchase

        // Step 1: Store the current totalSales
        double prevTotalSales = totalSales;

        // Step 2: Update currentMoney to be the item's price for the current
        // transaction
        currentMoney += item.getPrice();

        // Step 3: Add the change to currentMoney for the current transaction
        currentMoney += change;

        // Step 4: Update totalSales to be the current totalSales plus the item's price
        // for the current transaction
        totalSales += item.getPrice();

        JOptionPane.showMessageDialog(null, "Item dispensed: " + item.getName());
        JOptionPane.showMessageDialog(null, "Price: $" + item.getPrice());
        JOptionPane.showMessageDialog(null, "Change: $" + String.format("%.2f", change));
    }

    // Add any additional methods and attributes specific to the
    // SpecialVendingMachine

    public void createSpecialVendingMachine(Scanner scanner) {

        int slots;
        int capacity;

        do {
            String presetChoice = JOptionPane.showInputDialog(null, "Enter the number of slots (must be at least 8): ");
            slots = Integer.parseInt(presetChoice);
        } while (slots < 8);

        do {
            String presetChoice = JOptionPane.showInputDialog(null, "Enter the capacity per slot (must be at least 10): ");
            capacity = Integer.parseInt(presetChoice);
        } while (capacity < 10);

        for (int i = 1; i <= slots; i++) {
            String itemName;
            double itemPrice;
            int itemCalories;

            JOptionPane.showMessageDialog(null,"\n--- Slot " + i + " ---");
            itemName = JOptionPane.showInputDialog(null, "Enter the item name: ");

            String itemPriceInput = JOptionPane.showInputDialog(null, "Enter the item price: $");
            itemPrice = Double.parseDouble(itemPriceInput);

            String itemCaloriesInput = JOptionPane.showInputDialog(null, "Enter the item calories: ");
            itemCalories = Integer.parseInt(itemCaloriesInput);

            // Create the item with individualItem set to true
            Item item = new Item(itemName, itemPrice, itemCalories, true);
            items.put(i, item);
            stock.put(i, capacity);
        }

        isMachineCreated = true;
        JOptionPane.showMessageDialog(null,"\nSpecial Vending Machine created successfully!");
    }

    // Method to add preset items to the Special Vending Machine
    public void addPresetItems() {

        addItem(1, "Vegie Sandwich", 1.99, 100, true);
        addItem(2, "Roast Beef Sandwich", 2.49, 150, true);
        addItem(3, "Bagel", 0.99, 80, true);

        // Replace the following lines with your own preset items for the Special
        // Vending Machine
        components.put(4, new VendingSlot(new Item("Vegies", 5.99, 400, true), 10));
        components.put(5, new VendingSlot(new Item("Beef Strips", 3.99, 50, true), 10));
        components.put(6, new VendingSlot(new Item("Bread", 1.99, 100, true), 10));
        // ... Add more preset items as needed

        // Set the initial stock values for the preset items
        stock.put(1, 10);
        stock.put(2, 10);
        stock.put(3, 10);

        stock.put(4, 10);
        stock.put(5, 10);
        stock.put(6, 10);
        // ... Add more initial stock values as needed

    }

    // Add a setMachineCreated method to set the machineCreated flag
    public void setMachineCreated(boolean machineCreated) {

        this.machineCreated = machineCreated;
    }

    // Add a getter method for the isMachineCreated flag
    public boolean isMachineCreated() {

        return this.machineCreated;
    }

    @Override
    public void testVendingMachine(Scanner scanner) {

        if (!isMachineCreated) {
            JOptionPane.showMessageDialog(null,"Vending Machine is not created. Please create a Special Vending Machine first.");
            return;
        }
        double prevTotalSales = totalSales; // Step 1: Store the current totalSales
        JOptionPane.showMessageDialog(null,"--- Available Items ---");
        displayItems();
        displayCalories();
        displayComponents();
        JOptionPane.showMessageDialog(null,"Calories per item:");

        // Step 1: Ask the user to input a denomination and update the user balance
        double denomination;
        do {
            String denoms = JOptionPane.showInputDialog(null,"Enter a denomination (0.1, 0.5, 1, 5, 10, 20, 50): ");
            denomination = Double.parseDouble(denoms);
        } while (denomination != 0.1 && denomination != 0.5 && denomination != 1 &&
                denomination != 5 && denomination != 10 && denomination != 20 && denomination != 50);

        userBalance += denomination; // Update user's balance

        // Step 2: Display the items and prompt the user to select an item
        JOptionPane.showMessageDialog(null,"--- Available Items ---");
        displayItems();
        displayCalories();
        displayComponents();
        JOptionPane.showMessageDialog(null,"Calories per item:");

        String slotNom = JOptionPane.showInputDialog(null, "Enter the slot number of the item you want to purchase: ");
        int slotNumber = Integer.parseInt(slotNom);

        // Check if the selected item is a custom sandwich

        if (!isIndividualItem(slotNumber)) {

            JOptionPane.showMessageDialog(null,"This item can only be purchased as part of a customized product.");
            return;

        } else {
            // Step 3: Proceed with the item selection and dispensing for individual items
            dispenseItem(slotNumber);
        }

        JOptionPane.showMessageDialog(null,"\nSpecial Vending Machine transaction completed!");
    }

    @Override
    public void addItem(int slotNumber, String itemName, double itemPrice, int itemCalories, boolean individualItem) {

        Item sandwich = new Item(itemName, itemPrice, itemCalories, individualItem);
        items.put(slotNumber, sandwich);
    }
}
