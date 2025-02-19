import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class RegularVendingMachineGUI extends JFrame {

    private JButton createMachineButton;
    private JButton testMachineButton;
    private JButton maintenanceButton;
    private JButton exitButton;

    private RegularVendingMachine regularVendingMachine;

    public RegularVendingMachineGUI() {
        setTitle("Regular Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        createMachineButton = new JButton("Create Regular Vending Machine");
        testMachineButton = new JButton("Test Vending Machine");
        maintenanceButton = new JButton("Maintenance");
        exitButton = new JButton("Exit");

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1));
        buttonPanel.add(createMachineButton);
        buttonPanel.add(testMachineButton);
        buttonPanel.add(maintenanceButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.CENTER);

        createMachineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRegularVendingMachine();
            }
        });

        testMachineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                testVendingMachine();
            }
        });

        maintenanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performMaintenance();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        regularVendingMachine = new RegularVendingMachine();
    }

    private void createRegularVendingMachine() {
        regularVendingMachine.createRegularVendingMachine(); // You can use a proper scanner here.
        JOptionPane.showMessageDialog(null, "Regular Vending Machine created successfully!");
    }

    private void testVendingMachine() {
        regularVendingMachine.testVendingMachine(new Scanner(System.in)); // You can use a proper scanner here.
    }

    private void performMaintenance() {
        regularVendingMachine.restockItems(new Scanner(System.in)); // You can use a proper scanner here.
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegularVendingMachineGUI vendingMachineGUI = new RegularVendingMachineGUI();
                vendingMachineGUI.setVisible(true);
            }
        });
    }*/
}
