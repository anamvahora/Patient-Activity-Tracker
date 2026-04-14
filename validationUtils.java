package its340_andreas_anamv;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class validationUtils {

    // 1. Validates that a field is not empty (Required Field Rule)
    public static boolean isNotEmpty(JTextField field, String fieldName) {
        if (field.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, fieldName + " is required (*).");
            field.requestFocus();
            return false;
        }
        return true;
    }

    // 2. Validates that the input is a valid number (Data Linking Rule)
    public static boolean isInteger(JTextField field, String fieldName) {
        try {
            Integer.parseInt(field.getText().trim());
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, fieldName + " must be a numeric.");
            field.requestFocus();
            return false;
        }
    }

    // 3. Validates against a specific pattern (Format Rule)
    // Useful for Blood Type (A, B, AB, O) or Rh (+, -)
    public static boolean matchesPattern(JTextField field, String fieldName, String regex, String formatExample) {
        if (!field.getText().trim().toUpperCase().matches(regex)) {
            JOptionPane.showMessageDialog(null, fieldName + " must follow format: " + formatExample);
            field.requestFocus();
            return false;
        }
        return true;
    }
}