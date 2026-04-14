package its340_andreas_anamv;

import java.sql.*;
import javax.swing.JOptionPane;

public class patientDemographicsDBUtils {

    /**
     * Fetches all demographics for a single patient by ID.
     */
    public static ResultSet getPatientByID(Connection conn, int pid) {
        try {
            String sql = "{CALL GetPatientbyID(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, pid);
            return cs.executeQuery();
        } catch (SQLException e) {
            System.out.println("Read Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Executes the Insert procedure for a brand new patient.
     * data[] should contain parameters in the exact order of your SP.
     */
    public static int insertPatient(Connection conn, String[] data) {
        try {
            String sql = "{CALL InsertPatientSP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = conn.prepareCall(sql);
            
            // Set parameters 1 through 21 from the array
            for (int i = 0; i < data.length; i++) {
                cs.setString(i + 1, data[i]);
            }
            
            return cs.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Insert Failed: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Executes the Update procedure for an existing patient.
     * Note: This SP requires the PatientID as the first parameter.
     */
    public static int updatePatient(Connection conn, int pid, String[] data) {
        try {
            String sql = "{CALL UpdatePatientSP(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
            CallableStatement cs = conn.prepareCall(sql);
            
            cs.setInt(1, pid); // First param is the ID
            // Set the remaining 21 parameters
            for (int i = 0; i < data.length; i++) {
                cs.setString(i + 2, data[i]);
            }
            
            return cs.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Update Failed: " + e.getMessage());
            return -1;
        }
    }

    /**
     * Soft Delete: Marks the record as deleted via your modified SP.
     */
    public static int deletePatient(Connection conn, int pid) {
        try {
            String sql = "{CALL DeletePatientSP(?)}";
            CallableStatement cs = conn.prepareCall(sql);
            cs.setInt(1, pid);
            return cs.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Delete Failed: " + e.getMessage());
            return -1;
        }
    }
}