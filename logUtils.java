package its340_andreas_anamv;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class logUtils {
    public static void writeInterviewLog(int patientID, String patientName, String interviewType, String content) {
        // FileName: Log_interview_patientID_date.txt
        String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String fileName = "Log_" + interviewType + "_" + patientID + "_" + dateStr + ".txt";

        //  Text file using Java File I/O
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println("=== INTERVIEW LOG ===");
            //  Record patient name and ID
            writer.println("Patient ID: " + patientID);
            writer.println("Patient Name: " + patientName);
            writer.println("Date/Time: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            writer.println("---------------------");
            //  This 'content' will contain the Questions and Answers
            writer.println(content);
            writer.println("===========Q&A==========\n");
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Log Error: " + e.getMessage());
        }
    }
}