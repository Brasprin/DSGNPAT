import javax.swing.*;
import java.awt.*;

public class VendingSlotDetailsGUI extends JFrame {

    private JLabel itemNameLabel;
    private JLabel quantityLabel;

    public VendingSlotDetailsGUI(VendingSlot vendingSlot) {
        setTitle("Vending Slot Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 100);
        setLayout(new GridLayout(2, 2));

        itemNameLabel = new JLabel("Item Name: ");
        quantityLabel = new JLabel("Quantity: ");

        JLabel itemLabel = new JLabel(vendingSlot.getItem().getName());
        JLabel quantityValueLabel = new JLabel(Integer.toString(vendingSlot.getQuantity()));

        add(itemNameLabel);
        add(itemLabel);
        add(quantityLabel);
        add(quantityValueLabel);

        // Center-align the labels
        itemNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Center-align the item details
        itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
        quantityValueLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
/*
    public static void main(String[] args) {
        // Create a VendingSlot instance for testing
        Item item = new Item("Chips", 1.5, 200, false);
        VendingSlot vendingSlot = new VendingSlot(item, 10);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                VendingSlotDetailsGUI vendingSlotDetailsGUI = new VendingSlotDetailsGUI(vendingSlot);
                vendingSlotDetailsGUI.setVisible(true);
            }
        });
    }*/
}
