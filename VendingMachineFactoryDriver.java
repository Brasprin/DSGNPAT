import java.util.*;

public class VendingMachineFactoryDriver
{
    RegularVendingMachine regularVendingMachine = null; // Declare a variable for RegularVendingMachine
    SpecialVendingMachine specialVendingMachine = null; // Declare a variable for SpecialVendingMachine

    public static void main(String[] args) {
        VendingMachineFactoryDriver driver = new VendingMachineFactoryDriver();
        driver.runFactory();
    }

    public void runFactory() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            choice = menuOptions(scanner);

            switch (choice) {
                case 1:
                    createVendingMachineType(scanner, true);
                    break;
                case 2:
                    createVendingMachineType(scanner, false);
                    break;
                case 3:
                    testVendingMachine(scanner);
                    break;
                case 4:
                    maintenance(scanner);
                    break;
                case 5:
                    System.out.println("Exiting Vending Machine Factory. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        } while (choice != 5);

        scanner.close();
    }

    private int menuOptions(Scanner scanner) {
        System.out.println("------ VENDING MACHINE FACTORY ------");
        System.out.println("1. Create a Regular Vending Machine");
        System.out.println("2. Create a Special Vending Machine");
        System.out.println("3. Test a Vending Machine");
        System.out.println("4. Maintenance");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    private void createVendingMachineType(Scanner scanner, boolean isRegular ) {
        System.out.println("\nDo you want to create a preset Vending Machine? (Y/N)");
        String presetChoice = scanner.next().toUpperCase();

        if(isRegular) {
            regularVendingMachine = new RegularVendingMachine();
            if (presetChoice.equals("Y")) {
                regularVendingMachine.addPresetItems();
                regularVendingMachine.setMachineCreated(true);
                System.out.println("\nPreset Regular Vending Machine created successfully!\n");
            } else {
                regularVendingMachine.createRegularVendingMachine();
            }
        } else {
            specialVendingMachine = new SpecialVendingMachine();
            if (presetChoice.equals("Y")) {
                specialVendingMachine.addPresetItems();
                specialVendingMachine.setMachineCreated(true);
                System.out.println("\nPreset Special Vending Machine created successfully!\n");
            } else {
                specialVendingMachine.createSpecialVendingMachine(scanner);
            }
        }
    }

    private void testVendingMachine(Scanner scanner) {
        if ((regularVendingMachine == null || !regularVendingMachine.isMachineCreated())
            && (specialVendingMachine == null || !specialVendingMachine.isMachineCreated())) {
            System.out.println("Vending Machine is not created. Please create a Vending Machine first.\n");
        } else {
            // Prompt user to select which vending machine to test
            System.out.println("\nWhich vending machine would you like to test?");
            System.out.println("1. Regular Vending Machine");
            System.out.println("2. Special Vending Machine");
            int machineChoice = scanner.nextInt();

            if (machineChoice == 1 && regularVendingMachine != null) {
                regularVendingMachine.testVendingMachine(scanner);
            } else if (machineChoice == 2 && specialVendingMachine != null) {
                specialVendingMachine.testVendingMachine(scanner);
            } else {
                System.out.println("Invalid choice or vending machine not created.\n");
            }
        }
    }

    private void maintenance(Scanner scanner) {
        if ((regularVendingMachine == null || !regularVendingMachine.isMachineCreated())
        && (specialVendingMachine == null || !specialVendingMachine.isMachineCreated())) {
            System.out.println("Vending Machine is not created. Please create a Vending Machine first.\n");
        } else {
            // Prompt user to select which vending machine to maintain
            System.out.println("\nWhich vending machine would you like to maintain?");
            System.out.println("1. Regular Vending Machine");
            System.out.println("2. Special Vending Machine");
            int machineChoice = scanner.nextInt();

            if (machineChoice == 1 && regularVendingMachine != null) {
                VendingMachineInterface adapter = new VendingMachineAdapter(regularVendingMachine);
                MaintenanceMenu maintenanceMenu = new MaintenanceMenu(adapter, scanner);
                maintenanceMenu.startMaintenanceMenu();
            } else if (machineChoice == 2 && specialVendingMachine != null) {
                VendingMachineInterface adapter = new VendingMachineAdapter(specialVendingMachine);
                MaintenanceMenu maintenanceMenu = new MaintenanceMenu(adapter, scanner);
                maintenanceMenu.startMaintenanceMenu();
            } else {
                System.out.println("Invalid choice or vending machine not created.\n");
            }
        }
    }
}
