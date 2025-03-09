import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JFrame;

public class RegularVendingMachine extends JFrame implements VendingMachineInterface  {
    protected Map<Integer, Item> items;
    protected Map<Integer, Integer> stock;
    private Map<Double, Integer> money;
    private boolean machineCreated;
    protected double totalSales;
    private double initialSales;
    private double initialMoney;
    protected double currentMoney;
    protected boolean isMachineCreated;
    protected double userBalance;
    
    // Use our UI adapter instead of direct JOptionPane calls.
    private UserInterface ui;
    
    public RegularVendingMachine() {
        items = new HashMap<>();
        stock = new HashMap<>();
        money = new HashMap<>();
        totalSales = 0;
        initialSales = 0;
        initialMoney = 0;
        currentMoney = 0;
        isMachineCreated = true;
        ui = new JOptionPaneAdapter();
    }
    
    public void createRegularVendingMachine() {
        int slots = InputUtil.getIntInput(ui, "Enter the number of slots (must be at least 8): ", 8);
        int capacity = InputUtil.getIntInput(ui, "Enter the capacity per slot (must be at least 10): ", 10);
    
        for (int i = 1; i <= slots; i++) {
            ui.showMessage("\n--- Slot " + i + " ---");
            String itemName = ui.getInput("Enter the item name: ");
            double itemPrice = Double.parseDouble(ui.getInput("Enter the item price: $"));
            int itemCalories = Integer.parseInt(ui.getInput("Enter the item calories: "));
    
            addItem(i, itemName, itemPrice, itemCalories, true);
            stock.put(i, capacity);
        }
    
        isMachineCreated = true;
        ui.showMessage("\nRegular Vending Machine created successfully!");
    }
    
    public void testVendingMachine(Scanner scanner) {
        if (!isMachineCreated) {
            ui.showMessage("Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }
    
        double prevTotalSales = totalSales;
        displayItems();
        displayCalories();
    
        double[] validDenoms = {0.1, 0.5, 1, 5, 10, 20, 50};
        double denomination = InputUtil.getDoubleInput(ui, "Enter a denomination (0.1, 0.5, 1, 5, 10, 20, 50): ", validDenoms);
    
        userBalance += denomination;
    
        displayItems();
        displayCalories();
    
        int slotNumber = Integer.parseInt(ui.getInput("Enter the slot number of the item you want to purchase: "));
    
        if (!items.containsKey(slotNumber)) {
            ui.showMessage("Invalid slot number. Please try again.");
            return;
        }
    
        Item item = items.get(slotNumber);
    
        if (stock.get(slotNumber) == 0) {
            ui.showMessage("This item is out of stock. Please choose another item.");
            return;
        }
    
        ui.showMessage("You have selected: " + item.getName());
        if (userBalance < item.getPrice()) {
            ui.showMessage("Insufficient balance. Please insert more money or choose another item.");
            return;
        }
    
        double change = userBalance - item.getPrice();
        userBalance = 0;
        stock.put(slotNumber, stock.get(slotNumber) - 1);
        totalSales += item.getPrice();
    
        ui.showMessage("Dispensing " + item.getName() + "...");
        ui.showMessage("Change: $" + String.format("%.2f", change));
    
        // Update current money
        currentMoney += userBalance;
        double collectedMoney = totalSales - prevTotalSales;
        currentMoney += collectedMoney;
        totalSales += prevTotalSales;
    
        ui.showMessage("\nRegular Vending Machine transaction completed!");
    }
    
    protected void displayItems() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Available Items ---\n");
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = entry.getValue();
            sb.append(slotNumber).append(". ").append(item.getName())
              .append(" - Stock: ").append(stock.get(slotNumber));
            if (isIndividualItem(slotNumber)) {
                sb.append(" - Price: $").append(item.getPrice());
            } else {
                sb.append(" (Can only be purchased as part of a customized product)");
            }
            sb.append("\n");
        }
        ui.showMessage(sb.toString());
    }
    
    public void restockItems(Scanner scanner) {
        if (!isMachineCreated) {
            ui.showMessage("Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }
    
        ui.showMessage("\n--- Restocking Items ---");
    
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = entry.getValue();
            displayItems();
            int quantity = Integer.parseInt(ui.getInput("Enter the quantity to restock for slot " 
                    + slotNumber + " (" + item.getName() + "): "));
    
            stock.put(slotNumber, stock.get(slotNumber) + quantity);
        }
    
        double collectedMoney = totalSales - initialSales;
        currentMoney += collectedMoney;
        initialSales = totalSales;
    
        ui.showMessage("\nItems restocked successfully!");
    }
    
    public void setItemPrice(Scanner scanner) {
        if (!isMachineCreated) {
            ui.showMessage("Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }
    
        ui.showMessage("\n--- Setting Item Price ---");
    
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = entry.getValue();
            displayItems();
            double newPrice = Double.parseDouble(ui.getInput("Enter the new price for slot " 
                    + slotNumber + " (" + item.getName() + "): $"));
            item.setPrice(newPrice);
        }
    
        ui.showMessage("\nItem prices updated successfully!");
    }
    
    public void collectMoney() {
        if (!isMachineCreated) {
            ui.showMessage("Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }
    
        ui.showMessage("\n--- Collecting Money ---");
    
        if (totalSales == initialSales) {
            ui.showMessage("No new sales since the last restocking. Nothing to collect.");
            return;
        }
    
        double collectedMoney = totalSales - initialSales;
        currentMoney += collectedMoney;
        totalSales = initialSales;
    
        ui.showMessage("Collected money: $" + collectedMoney);
        ui.showMessage("Total money in machine: $" + currentMoney);
    }
    
    public void replenishChange(Scanner scanner) {
        if (!isMachineCreated) {
            ui.showMessage("Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }
    
        ui.showMessage("\n--- Replenishing Change ---");
    
        double denomination = Double.parseDouble(ui.getInput("Enter the denomination of the change to replenish: $"));
        int quantity = Integer.parseInt(ui.getInput("Enter the quantity of " + denomination 
                + " dollar bills to replenish: "));
    
        money.put(denomination, money.getOrDefault(denomination, 0) + quantity);
    
        ui.showMessage("Change replenished successfully!");
    }
    
    public void displayCalories() {
        StringBuilder sb = new StringBuilder();
        sb.append("Calories per item:\n");
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = entry.getValue();
            sb.append("Slot ").append(slotNumber).append(": ")
              .append(item.getName()).append(" - ").append(item.getCalories()).append(" calories\n");
        }
        ui.showMessage(sb.toString());
    }
    
    public void printTransactionSummary() {
        if (!isMachineCreated) {
            ui.showMessage("Vending Machine is not created. Please create a Regular Vending Machine first.");
            return;
        }
    
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- Transaction Summary ---\n");
        sb.append("Total Sales: $").append(totalSales).append("\n");
        sb.append("Starting Inventory:\n");
    
        for (Map.Entry<Integer, Item> entry : items.entrySet()){
            int slotNumber = entry.getKey();
            Item item = entry.getValue();
            int initialStock = stock.get(slotNumber);
            sb.append(slotNumber).append(". ").append(item.getName()).append(" - Stock: ").append(initialStock).append("\n");
        }
    
        sb.append("\nEnding Inventory:\n");
    
        for (Map.Entry<Integer, Item> entry : items.entrySet()) {
            int slotNumber = entry.getKey();
            Item item = entry.getValue();
            int currentStock = stock.get(slotNumber);
            sb.append(slotNumber).append(". ").append(item.getName()).append(" - Stock: ").append(currentStock).append("\n");
        }
    
        ui.showMessage(sb.toString());
    }
    
    public void dispenseItem(int slotNumber) {
        Item item = items.get(slotNumber);
        if (!item.isIndividualItem()) {
            ui.showMessage("This item can only be purchased as part of a customized product.");
        }
    }
    
    public void addItem(int slotNumber, String itemName, double itemPrice, int itemCalories, boolean individualItem) {
        Item item = new Item(itemName, itemPrice, itemCalories, individualItem);
        items.put(slotNumber, item);
    }
    
    public boolean isIndividualItem(int slotNumber) {
        Item item = items.get(slotNumber);
        return item != null && item.isIndividualItem();
    }
    
    public void addPresetItems() {
        addItem(1, "Item 1", 1.99, 100, true);
        addItem(2, "Item 2", 2.49, 150, true);
        addItem(3, "Item 3", 0.99, 80, true);
    
        stock.put(1, 10);
        stock.put(2, 10);
        stock.put(3, 10);
    }
    
    public void setMachineCreated(boolean machineCreated) {
        this.isMachineCreated = machineCreated;
    }
    
    public boolean isMachineCreated() {
        return this.isMachineCreated;
    }
    
    public void addComponent(String name, double price, int calories, boolean individualItem) {
        Item item = new Item(name, price, calories, individualItem);
        items.put(items.size() + 1, item);
    }
    
    public void displayDenominations() {
        ui.showMessage("--- Available Denominations ---\n0.5, 1, 5, 10, 20, 50");
    }
}
