import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class SpecialVendingMachineGUI extends JFrame {

    private JButton createMachineButton;
    private JButton testMachineButton;
    private JButton maintenanceButton;
    private JButton exitButton;

    private SpecialVendingMachine specialVendingMachine;

    public SpecialVendingMachineGUI() {
        setTitle("Special Vending Machine");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        createMachineButton = new JButton("Create Special Vending Machine");
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
                createSpecialVendingMachine();
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

        specialVendingMachine = new SpecialVendingMachine();
    }

    private void createSpecialVendingMachine() {
        specialVendingMachine.createSpecialVendingMachine(new Scanner(System.in)); // You can use a proper scanner here.
        JOptionPane.showMessageDialog(null, "Special Vending Machine created successfully!");
    }

    private void testVendingMachine() {
        specialVendingMachine.testVendingMachine(new Scanner(System.in)); // You can use a proper scanner here.
    }

    private void performMaintenance() {
        specialVendingMachine.restockItems(new Scanner(System.in)); // You can use a proper scanner here.
    }
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SpecialVendingMachineGUI vendingMachineGUI = new SpecialVendingMachineGUI();
                vendingMachineGUI.setVisible(true);
            }
        });
    }*/
}
