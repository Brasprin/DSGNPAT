import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class MaintenanceMenuGUI extends JFrame {

    private JButton restockButton;
    private JButton setPriceButton;
    private JButton collectMoneyButton;
    private JButton replenishChangeButton;
    private JButton printSummaryButton;
    private JButton exitButton;

    private RegularVendingMachine vendingMachine;

    public MaintenanceMenuGUI(RegularVendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
        setTitle("Maintenance Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        restockButton = new JButton("Restock Items");
        setPriceButton = new JButton("Set Item Price");
        collectMoneyButton = new JButton("Collect Money");
        replenishChangeButton = new JButton("Replenish Change");
        printSummaryButton = new JButton("Print Transaction Summary");
        exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridLayout(7, 1));
        buttonPanel.add(restockButton);
        buttonPanel.add(setPriceButton);
        buttonPanel.add(collectMoneyButton);
        buttonPanel.add(replenishChangeButton);
        buttonPanel.add(printSummaryButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        restockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                restockItems();
            }
        });

        setPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemPrice();
            }
        });

        collectMoneyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectMoney();
            }
        });

        replenishChangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replenishChange();
            }
        });

        printSummaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTransactionSummary();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void restockItems() {
        vendingMachine.restockItems(new Scanner(System.in)); // You can use a proper scanner here.
    }

    private void setItemPrice() {
        vendingMachine.setItemPrice(new Scanner(System.in)); // You can use a proper scanner here.
    }

    private void collectMoney() {
        vendingMachine.collectMoney();
    }

    private void replenishChange() {
        vendingMachine.replenishChange(new Scanner(System.in)); // You can use a proper scanner here.
    }

    private void printTransactionSummary() {
        vendingMachine.printTransactionSummary();
    }

   /* public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegularVendingMachine vendingMachine = new RegularVendingMachine();
                MaintenanceMenuGUI maintenanceMenuGUI = new MaintenanceMenuGUI(vendingMachine);
                maintenanceMenuGUI.setVisible(true);
            }
        });
    }*/
}
