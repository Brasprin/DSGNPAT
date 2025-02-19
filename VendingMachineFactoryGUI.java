import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class VendingMachineFactoryGUI extends JFrame implements ActionListener {

    private JButton createRegularMachineButton;
    private JButton createSpecialMachineButton;
    private JButton testMachineButton;
    private JButton maintenanceButton;
    private JButton exitButton;

    private RegularVendingMachine regularVendingMachine;
    private SpecialVendingMachine specialVendingMachine;

    public VendingMachineFactoryGUI() {
        setTitle("Vending Machine Factory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLayout(new GridLayout(5, 1));

        createRegularMachineButton = new JButton("Create Regular Vending Machine");
        createSpecialMachineButton = new JButton("Create Special Vending Machine");
        testMachineButton = new JButton("Test Vending Machine");
        maintenanceButton = new JButton("Maintenance");
        exitButton = new JButton("Exit");

        createRegularMachineButton.addActionListener(this);
        createSpecialMachineButton.addActionListener(this);
        testMachineButton.addActionListener(this);
        maintenanceButton.addActionListener(this);
        exitButton.addActionListener(this);

        add(createRegularMachineButton);
        add(createSpecialMachineButton);
        add(testMachineButton);
        add(maintenanceButton);
        add(exitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == createRegularMachineButton) {
            createRegularVendingMachine();
        } else if (e.getSource() == createSpecialMachineButton) {
            createSpecialVendingMachine();
        } else if (e.getSource() == testMachineButton) {
            testVendingMachine();
        } else if (e.getSource() == maintenanceButton) {
            startMaintenanceMenu();
        } else if (e.getSource() == exitButton) {
            System.out.println("Exiting Vending Machine Factory. Goodbye!");
            dispose();
        }
    }

    private void createRegularVendingMachine() {
        String presetChoice = JOptionPane.showInputDialog(null, "Do you want to create a preset Regular Vending Machine? (Y/N)");
        if (presetChoice != null && presetChoice.equalsIgnoreCase("Y")) {
            regularVendingMachine = new RegularVendingMachine();
            regularVendingMachine.addPresetItems();
            regularVendingMachine.setMachineCreated(true);
            JOptionPane.showMessageDialog(null, "Preset Regular Vending Machine created successfully!");
        } else {
            regularVendingMachine = new RegularVendingMachine();
            regularVendingMachine.createRegularVendingMachine();
        }
    }

    private void createSpecialVendingMachine() {
        String presetChoice = JOptionPane.showInputDialog(null, "Do you want to create a preset Special Vending Machine? (Y/N)");
        if (presetChoice != null && presetChoice.equalsIgnoreCase("Y")) {
            specialVendingMachine = new SpecialVendingMachine();
            specialVendingMachine.addPresetItems();
            specialVendingMachine.setMachineCreated(true);
            JOptionPane.showMessageDialog(null, "Preset Special Vending Machine created successfully!");
        } else {
            specialVendingMachine = new SpecialVendingMachine();
            specialVendingMachine.createSpecialVendingMachine(new Scanner(System.in));
        }
    }

    private void testVendingMachine() {
        if ((regularVendingMachine == null || !regularVendingMachine.isMachineCreated())
                && (specialVendingMachine == null || !specialVendingMachine.isMachineCreated())) {
            JOptionPane.showMessageDialog(null, "Vending Machine is not created. Please create a Vending Machine first.");
        } else {
            Object[] machineChoices = {"Regular Vending Machine", "Special Vending Machine"};
            int machineChoice = JOptionPane.showOptionDialog(null, "Which vending machine would you like to test?", "Vending Machine Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, machineChoices, machineChoices[0]);
            if (machineChoice == 0 && regularVendingMachine != null) {
                regularVendingMachine.testVendingMachine(new Scanner(System.in));
            } else if (machineChoice == 1 && specialVendingMachine != null) {
                specialVendingMachine.testVendingMachine(new Scanner(System.in));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid choice or vending machine not created.");
            }
        }
    }

    private void startMaintenanceMenu() {
        if ((regularVendingMachine == null || !regularVendingMachine.isMachineCreated())
                && (specialVendingMachine == null || !specialVendingMachine.isMachineCreated())) {
            JOptionPane.showMessageDialog(null, "Vending Machine is not created. Please create a Vending Machine first.");
        } else {
            Object[] machineChoices = {"Regular Vending Machine", "Special Vending Machine"};
            int machineChoice = JOptionPane.showOptionDialog(null, "Which vending machine would you like to maintain?", "Vending Machine Selection",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, machineChoices, machineChoices[0]);
            if (machineChoice == 0 && regularVendingMachine != null) {
                MaintenanceMenu maintenanceMenu = new MaintenanceMenu(regularVendingMachine, new Scanner(System.in));
                maintenanceMenu.startMaintenanceMenu();
            } else if (machineChoice == 1 && specialVendingMachine != null) {
                MaintenanceMenu maintenanceMenu = new MaintenanceMenu(specialVendingMachine, new Scanner(System.in));
                maintenanceMenu.startMaintenanceMenu();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid choice or vending machine not created.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VendingMachineFactoryGUI vendingMachineFactoryGUI = new VendingMachineFactoryGUI();
                vendingMachineFactoryGUI.setVisible(true);
            }
        });
    }
}
