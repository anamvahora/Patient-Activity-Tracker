package its340_andreas_anamv;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

public class validationUtils {

    // 1. Works for both JTextField and JTextArea
    public static boolean isNotEmpty(JTextComponent field, String fieldName) {
        if (field.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, fieldName + " is required (*).");
            field.requestFocus();
            return false;
        }
        return true;
    }

    // 2. Improved error message phrasing
    public static boolean isInteger(JTextComponent field, String fieldName) {
        try {
            Integer.parseInt(field.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " must be a valid number.");
            field.requestFocus();
            return false;
        }
    }

    // 3. Regex for SSN (Supports 9 digits with or without dashes)
    public static boolean isValidSSN(JTextComponent field) {
        String ssn = field.getText().trim();
        // Regex: 3 digits, optional dash, 2 digits, optional dash, 4 digits
        String regex = "^(\\d{3}-?\\d{2}-?\\d{4})$";
        if (!ssn.matches(regex)) {
            JOptionPane.showMessageDialog(null, "Invalid SSN format. Use 000-00-0000.");
            field.requestFocus();
            return false;
        }
        return true;
    }

    // 4. Validates against a specific pattern
    public static boolean matchesPattern(JTextComponent field, String fieldName, String regex, String formatExample) {
        if (!field.getText().trim().toUpperCase().matches(regex)) {
            JOptionPane.showMessageDialog(null, fieldName + " must follow format: " + formatExample);
            field.requestFocus();
            return false;
        }
        return true;
    }
}