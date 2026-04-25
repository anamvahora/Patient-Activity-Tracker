package utils;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;

public class SOBInterviewUtils {
    
     public static Map<String, String> runSOBInterview() {

        Map<String, String> results = new HashMap<>();

    // Q1
    int q1 = JOptionPane.showConfirmDialog(null,
            "Did you have any shortness of breath today?",
            "SOB Interview",
            JOptionPane.YES_NO_OPTION);

    if (q1 == JOptionPane.NO_OPTION) {
        results.put("Severity", "None");
        results.put("Notes", "No shortness of breath");
        results.put("Date", LocalDate.now().toString());
        return results;
    }

    // Q2
    String scale = JOptionPane.showInputDialog(
            "On a scale of 1-5, enter severity:");

    int severity = Integer.parseInt(scale);

    if (severity <= 3) {
        results.put("Severity", "Mild");
        results.put("Notes", "Mild shortness of breath");
    } else {
        results.put("Severity", "Severe");

        // Q3
        int q3 = JOptionPane.showConfirmDialog(null,
                "Would you rate it between 4 and 5?",
                "SOB Interview",
                JOptionPane.YES_NO_OPTION);

        if (q3 == JOptionPane.YES_OPTION) {

            // Q4
            int q4 = JOptionPane.showConfirmDialog(null,
                    "Is it noticeably worse than yesterday?",
                    "SOB Interview",
                    JOptionPane.YES_NO_OPTION);

            if (q4 == JOptionPane.YES_OPTION) {
                results.put("Notes", "Severe and worsening");
            } else {
                results.put("Notes", "Severe but stable");
            }
        }
    }

    results.put("Date", LocalDate.now().toString());

    return results;
    }
}
