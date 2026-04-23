package utils;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ActivityInterviewUtils {
    public static Map<String, String> runActivityInterview() {

        Map<String, String> results = new HashMap<>();

        // Question 1
        String activity = JOptionPane.showInputDialog(
                null,
                "What activity did you perform?",
                "Activity Interview",
                JOptionPane.QUESTION_MESSAGE
        );

        if (activity == null || activity.trim().isEmpty()) {
            activity = "N/A";
        }

        // Question 2
        String difficulty = JOptionPane.showInputDialog(
                null,
                "Difficulty? (Easy / Moderate / Hard)",
                "Activity Interview",
                JOptionPane.QUESTION_MESSAGE
        );

        if (difficulty == null || difficulty.trim().isEmpty()) {
            difficulty = "N/A";
        }

        // Question 3
        String date = JOptionPane.showInputDialog(
                null,
                "Enter date (YYYY-MM-DD)",
                "Activity Interview",
                JOptionPane.QUESTION_MESSAGE
        );

        if (date == null || date.trim().isEmpty()) {
            date = "2026-01-01";
        }

        // Store results
        results.put("ActivityName", activity);
        results.put("Difficulty", difficulty);
        results.put("Date", date);

        return results;
    }
}
