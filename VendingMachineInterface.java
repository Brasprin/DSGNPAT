import java.util.*;

public interface VendingMachineInterface {
    void restockItems(Scanner scanner);
    void setItemPrice(Scanner scanner);
    void collectMoney();
    void replenishChange(Scanner scanner);
    void printTransactionSummary();
}
