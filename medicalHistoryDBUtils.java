package its340_andreas_anamv;

import java.sql.*;
import javax.swing.*;

/**
 * Utility class for General Medical History database operations.
 * All operations are performed via Stored Procedures as per project requirements.
 */
public class medicalHistoryDBUtils {
    

    /**
     * Retrieves ONE record for a specific patient.
     * Maps to: CALL GetGeneralMedicalHistorySP(?)
     */
    public static ResultSet getGeneralMedicalHistory(Connection conn, int p_PID) {
        ResultSet rs = null;
        try {
            String qryStoredProc = "{CALL GetGeneralMedicalHistorySP(?)}";
            CallableStatement cs = conn.prepareCall(qryStoredProc);

            cs.setInt(1, p_PID);
            rs = cs.executeQuery();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database Read Error: " + e.getMessage());
            e.printStackTrace();
        }
        return rs;
    }

    /**
     * Handles both Create (New) and Update (Edit) modes.
     * Maps to: CALL UpsertGeneralMedicalHistorySP(?,?,?,?,?,?,?,?)
     */
public static int upsertGeneralMedicalHistory(Connection conn, int pid, String[] data) {
    // 1. We now need 12 question marks to match the updated SP
    String sql = "{CALL UpsertGeneralMedicalHistorySP(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

    try (CallableStatement cstmt = conn.prepareCall(sql)) {
        // 2. Set the parameters based on the SQL order
        cstmt.setInt(1, pid);           // p_PID
        cstmt.setString(2, data[0]);    // p_Tobacco
        cstmt.setString(3, data[1]);    // p_TobaccoQty
        cstmt.setString(4, data[2]);    // p_TobaccoDur (The typo "Tobaccoduraton")
        cstmt.setString(5, data[3]);    // p_Alcohol
        cstmt.setString(6, data[4]);    // p_AlcoholQty
        cstmt.setString(7, data[5]);    // p_AlcoholDur
        cstmt.setString(8, data[6]);    // p_Drug (Yes/No)
        cstmt.setString(9, data[7]);    // p_DrugType
        cstmt.setString(10, data[8]);   // p_DrugDur
        cstmt.setString(11, data[9]);   // p_BloodType
        cstmt.setString(12, data[10]);  // p_Rh

        // 3. Execute
        return cstmt.executeUpdate();
    } catch (SQLException e) {
        javax.swing.JOptionPane.showMessageDialog(null, "Procedure Error: " + e.getMessage());
        return -1;
    }
}

    /**
     * Deletes the history record for a specific patient.
     * Maps to: CALL DeleteGeneralMedicalHistorySP(?)
     */
    public static int deleteGeneralMedicalHistory(Connection conn, int p_PID) {
        int status = 0;
        try {
            String deleteProc = "{CALL DeleteGeneralMedicalHistorySP(?)}";
            CallableStatement cs = conn.prepareCall(deleteProc);
            
            cs.setInt(1, p_PID);
            status = cs.executeUpdate();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Delete Error: " + e.getMessage());
        }
        return status;
    }
    
    
    
}