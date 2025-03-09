import java.util.*;

public class VendingMachineAdapter implements VendingMachineInterface {
    private RegularVendingMachine regularVendingMachine;

    public VendingMachineAdapter(RegularVendingMachine regularVendingMachine) {
        this.regularVendingMachine = regularVendingMachine;
    }

    @Override
    public void restockItems(Scanner scanner) {
        regularVendingMachine.restockItems(scanner);
    }

    @Override
    public void setItemPrice(Scanner scanner) {
        regularVendingMachine.setItemPrice(scanner);
    }

    @Override
    public void collectMoney() {
        regularVendingMachine.collectMoney();
    }

    @Override
    public void replenishChange(Scanner scanner) {
        regularVendingMachine.replenishChange(scanner);
    }

    @Override
    public void printTransactionSummary() {
        regularVendingMachine.printTransactionSummary();
    }
}
