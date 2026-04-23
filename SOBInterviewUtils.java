package utils;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class SOBInterviewUtils {
    
     public static Map<String, String> runSOBInterview() {

        Map<String, String> results = new HashMap<>();

        int sob = JOptionPane.showConfirmDialog(null, "Do you have shortness of breath?");
        results.put("SOB", sob == JOptionPane.YES_OPTION ? "Yes" : "No");

        String severity = JOptionPane.showInputDialog("Severity (Mild / Moderate / Severe)");
        if (severity == null || severity.trim().isEmpty()) severity = "N/A";

        String notes = JOptionPane.showInputDialog("Any notes?");
        if (notes == null || notes.trim().isEmpty()) notes = "N/A";

        results.put("Severity", severity);
        results.put("Notes", notes);

        return results;
    }
}
