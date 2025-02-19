public class InputUtil {

    /**
     * Prompts the user until they enter an integer value that is at least minValue.
     */
    public static int getIntInput(UserInterface ui, String prompt, int minValue) {
        int value;
        while (true) {
            try {
                String input = ui.getInput(prompt);
                value = Integer.parseInt(input);
                if (value < minValue) {
                    ui.showMessage("Value must be at least " + minValue + ". Please try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                ui.showMessage("Invalid number. Please try again.");
            }
        }
        return value;
    }

    /**
     * Prompts the user until they enter a double that is one of the allowed values.
     */
    public static double getDoubleInput(UserInterface ui, String prompt, double[] validValues) {
        double value = 0;
        boolean valid = false;
        while (!valid) {
            try {
                String input = ui.getInput(prompt);
                value = Double.parseDouble(input);
                for (double v : validValues) {
                    if (Double.compare(value, v) == 0) {
                        valid = true;
                        break;
                    }
                }
                if (!valid) {
                    ui.showMessage("Invalid denomination. Please try again.");
                }
            } catch (NumberFormatException e) {
                ui.showMessage("Invalid input. Please try again.");
            }
        }
        return value;
    }
}
