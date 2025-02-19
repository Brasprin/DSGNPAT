import java.util.Scanner;


/**
 * The class Vending machine factory driver
 */
public class VendingMachineFactoryDriver {

    /**
     *
     * Main
     *
     * @param args  the args.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choice;
        RegularVendingMachine regularVendingMachine = null; // Declare a variable for RegularVendingMachine
        SpecialVendingMachine specialVendingMachine = null; // Declare a variable for SpecialVendingMachine

        do {
            System.out.println("------ Vending Machine Factory ------");
            System.out.println("1. Create a Regular Vending Machine");
            System.out.println("2. Create a Special Vending Machine");
            System.out.println("3. Test a Vending Machine");
            System.out.println("4. Maintenance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Do you want to create a preset Regular Vending Machine? (Y/N)");
                    String presetChoice = scanner.next().toUpperCase();
                    if (presetChoice.equals("Y")) {
                        regularVendingMachine = new RegularVendingMachine();
                        regularVendingMachine.addPresetItems();
                        regularVendingMachine.setMachineCreated(true);
                        System.out.println("\nPreset Regular Vending Machine created successfully!");
                    } else {
                        regularVendingMachine = new RegularVendingMachine();
                        regularVendingMachine.createRegularVendingMachine();
                    }
                    break;
                case 2:
                    System.out.println("Do you want to create a preset Special Vending Machine? (Y/N)");
                    String presetChoice2 = scanner.next().toUpperCase();
                    if (presetChoice2.equals("Y")) {
                        specialVendingMachine = new SpecialVendingMachine();
                        specialVendingMachine.addPresetItems();
                        specialVendingMachine.setMachineCreated(true);
                        System.out.println("\nPreset Special Vending Machine created successfully!");
                    } else {
                        specialVendingMachine = new SpecialVendingMachine();
                        specialVendingMachine.createSpecialVendingMachine(scanner);
                    }
                    break;
                case 3:
                    if ((regularVendingMachine == null || !regularVendingMachine.isMachineCreated())
                            && (specialVendingMachine == null || !specialVendingMachine.isMachineCreated())) {
                        System.out.println("Vending Machine is not created. Please create a Vending Machine first.");
                    } else {
                        // Prompt user to select which vending machine to test
                        System.out.println("Which vending machine would you like to test?");
                        System.out.println("1. Regular Vending Machine");
                        System.out.println("2. Special Vending Machine");
                        int machineChoice = scanner.nextInt();
                        if (machineChoice == 1 && regularVendingMachine != null) {
                            regularVendingMachine.testVendingMachine(scanner);
                        } else if (machineChoice == 2 && specialVendingMachine != null) {
                            specialVendingMachine.testVendingMachine(scanner);
                        } else {
                            System.out.println("Invalid choice or vending machine not created.");
                        }
                    }
                    break;
                case 4:
                    if ((regularVendingMachine == null || !regularVendingMachine.isMachineCreated())
                            && (specialVendingMachine == null || !specialVendingMachine.isMachineCreated())) {
                        System.out.println("Vending Machine is not created. Please create a Vending Machine first.");
                    } else {
                        // Prompt user to select which vending machine to maintain
                        System.out.println("Which vending machine would you like to maintain?");
                        System.out.println("1. Regular Vending Machine");
                        System.out.println("2. Special Vending Machine");
                        int machineChoice = scanner.nextInt();
                        if (machineChoice == 1 && regularVendingMachine != null) {
                            MaintenanceMenu maintenanceMenu = new MaintenanceMenu(regularVendingMachine, scanner);
                            maintenanceMenu.startMaintenanceMenu();
                        } else if (machineChoice == 2 && specialVendingMachine != null) {
                            MaintenanceMenu maintenanceMenu = new MaintenanceMenu(specialVendingMachine, scanner);
                            maintenanceMenu.startMaintenanceMenu();
                        } else {
                            System.out.println("Invalid choice or vending machine not created.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Exiting Vending Machine Factory. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
