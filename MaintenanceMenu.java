import java.util.Scanner;

import javax.swing.JOptionPane;

public class MaintenanceMenu {
    private RegularVendingMachine vendingMachine;
    private Scanner scanner;

    /**
     * creates a maintenance menu object based on the user input and vending machine
     * 
     * @param vendingMachine    function call for RegularVendingMachine
     * @param scanner   user input
     */
    public MaintenanceMenu(RegularVendingMachine vendingMachine, Scanner scanner) {
        this.vendingMachine = vendingMachine;
        this.scanner = scanner;
    }

    /**
     * Prints out a menu detailing the features in the maintenance of a vending machine.
     * <p>
     * This method creates a menu allowing the user to choose what maintenance feature the user 
     * wants to do.
     * 
     */
    public void startMaintenanceMenu() {
        int maintenanceChoice;

        do {
            JOptionPane.showMessageDialog(null, 
            "\n------ Maintenance Menu ------\n1. Restock Items\n2. Set Item Price\n3. Collect Money\n4. Replenish Change\n5. Print Transaction Summary\n6. Exit Maintenance Menu");
            //System.out.println("1. Restock Items");
            //System.out.println("2. Set Item Price");
            //System.out.println("3. Collect Money");
            //System.out.println("4. Replenish Change");
            //System.out.println("5. Print Transaction Summary");
            //System.out.println("6. Exit Maintenance Menu");
            String choices = JOptionPane.showInputDialog(null,"Enter your choice: ");
            maintenanceChoice = Integer.parseInt(choices);

            switch (maintenanceChoice) {
                case 1:
                    vendingMachine.restockItems(scanner);
                    break;
                case 2:
                    vendingMachine.setItemPrice(scanner);
                    break;
                case 3:
                    vendingMachine.collectMoney();
                    break;
                case 4:
                    vendingMachine.replenishChange(scanner);
                    break;
                case 5:
                    vendingMachine.printTransactionSummary();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null,"Exiting Maintenance Menu.");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Invalid choice. Please try again.");
            }
        } while (maintenanceChoice != 6);
    }
}
