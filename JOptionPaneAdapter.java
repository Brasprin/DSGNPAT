import javax.swing.JOptionPane;

public class JOptionPaneAdapter implements UserInterface {

    @Override
    public String getInput(String prompt) {
        return JOptionPane.showInputDialog(null, prompt);
    }

    @Override
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
