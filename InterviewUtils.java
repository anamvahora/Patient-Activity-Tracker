package its340_andreas_anamv;

import javax.swing.JOptionPane;
import java.util.HashMap;
import java.util.Map;

public class InterviewUtils {

    public static Map<String, String> runMedicalHistoryInterview(int patientID) {
        Map<String, String> results = new HashMap<>();
        
        // 1. Instantiate Nodes
        InterviewNode tobaccoNode = new InterviewNode("Do you use tobacco?", "Tobacco");
        InterviewNode tobaccoQty = new InterviewNode("How often do you use tobacco?", "TobaccoQuantity");
        InterviewNode tobaccoDur = new InterviewNode("How long have you used it?", "Tobaccoduraton");

        InterviewNode alcoholNode = new InterviewNode("Do you consume alcohol?", "Alcohol");
        InterviewNode alcoholQty = new InterviewNode("How often do you drink?", "AlcoholQuantity");
        InterviewNode alcoholDur = new InterviewNode("How long have you consumed alcohol?", "Alcoholduration");

        InterviewNode drugNode = new InterviewNode("Do you use drugs?", "Drug");
        InterviewNode drugType = new InterviewNode("What type of drugs?", "DrugType");
        InterviewNode drugDur = new InterviewNode("How long have you used drugs?", "Drugduration");

        InterviewNode bloodType = new InterviewNode("What is your blood type? (e.g., A, B, AB, O)", "BloodType");
        InterviewNode rhNode = new InterviewNode("What is your Rh factor? (+ or -)", "Rh");

        // 2. Build Tree Connections
        tobaccoNode.yesBranch = tobaccoQty;
        tobaccoNode.noBranch = alcoholNode;
        tobaccoQty.yesBranch = tobaccoDur;
        tobaccoDur.yesBranch = alcoholNode;

        alcoholNode.yesBranch = alcoholQty;
        alcoholNode.noBranch = drugNode;
        alcoholQty.yesBranch = alcoholDur;
        alcoholDur.yesBranch = drugNode;

        drugNode.yesBranch = drugType;
        drugNode.noBranch = bloodType;
        drugType.yesBranch = drugDur;
        drugDur.yesBranch = bloodType;

        bloodType.yesBranch = rhNode;
        // rhNode.yesBranch remains null to end the interview

        // 3. Traverse the Tree
        InterviewNode current = tobaccoNode;
        while (current != null) {
            String answer;
            String currentField = current.dbField; // Keep track of the current field

            if (current.question.startsWith("Do you")) {
                int response = JOptionPane.showConfirmDialog(null, current.question, 
                               "Medical Interview", JOptionPane.YES_NO_OPTION);

                answer = (response == JOptionPane.YES_OPTION) ? "Yes" : "No";
                results.put(currentField, answer); // SAVE FIRST

                // THEN MOVE
                current = (response == JOptionPane.YES_OPTION) ? current.yesBranch : current.noBranch;
            } else {
                //  text input
                answer = JOptionPane.showInputDialog(null, current.question, "Medical Interview", JOptionPane.QUESTION_MESSAGE);

                if (answer == null || answer.trim().isEmpty()) answer = "N/A";
                results.put(currentField, answer); // SAVE FIRST

                // THEN MOVE
                current = current.yesBranch;
            }
        }

        return results;
    }
}