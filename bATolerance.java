package its340_andreas_anamv;
import DBUtils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import utils.FormUtils;
import javax.swing.JTextField;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import java.sql.*;
import utils.ActivityInterviewUtils;
import utils.navigationUtils;

public class bATolerance extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(bATolerance.class.getName());

    int selectedID = -1;
    JTextField[] fields;
    public bATolerance() {
        initComponents();
        FormUtils.disableFields(txtActivityName, txtDifficulty, txtDate);
        
        tblActivity.addMouseListener(new java.awt.event.MouseAdapter() {
    public void mouseClicked(java.awt.event.MouseEvent evt) {
        tblActivityMouseClicked(evt);
    }
    });
        
        fields = new JTextField[] {
        txtActivityName,
        txtDifficulty,
        txtDate
        };
        
        loadTable();
    }
  private void tblActivityMouseClicked(java.awt.event.MouseEvent evt) {

    int row = tblActivity.getSelectedRow();

    selectedID = Integer.parseInt(tblActivity.getValueAt(row, 0).toString());

    txtDate.setText(tblActivity.getValueAt(row, 1).toString());
    txtActivityName.setText(tblActivity.getValueAt(row, 2).toString());
    txtDifficulty.setText(tblActivity.getValueAt(row, 3).toString());
    FormUtils.disableFields(txtActivityName, txtDifficulty, txtDate);
    }
    private void loadTable() {

    try {
        Connection con = DBConnection.getConnection();

       String sql = "SELECT ActivityID, ActivityDate, ActivityName, Difficulty FROM activitytable WHERE PatientID=?";
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, SelectedPatient.patientID);
       ResultSet rs = ps.executeQuery();

        DefaultTableModel model = new DefaultTableModel();

        model.setColumnIdentifiers(new String[]{
            "ID", "Date", "Activity", "Difficulty"
        });

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("ActivityID"),
                rs.getDate("ActivityDate"),
                rs.getString("ActivityName"),
                rs.getString("Difficulty")
            });
        }

        tblActivity.setModel(model);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtActivityName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDifficulty = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActivity = new javax.swing.JTable();
        btnDelete = new javax.swing.JButton();
        btnInterview = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Activity Name");

        txtActivityName.setName(""); // NOI18N

        jLabel2.setText("Difficulty");

        txtDifficulty.setName(""); // NOI18N

        jLabel3.setText("Date (YYYY-MM-DD)");

        txtDate.setName(""); // NOI18N

        btnNew.setText("New");
        btnNew.addActionListener(this::btnNewActionPerformed);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnSave.setText("Save");
        btnSave.addActionListener(this::btnSaveActionPerformed);

        tblActivity.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblActivity);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnInterview.setText("Interview");
        btnInterview.addActionListener(this::btnInterviewActionPerformed);

        btnBack.setText("Back");
        btnBack.addActionListener(this::btnBackActionPerformed);

        btnNext.setText("Next");
        btnNext.addActionListener(this::btnNextActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(55, 55, 55)
                        .addComponent(txtActivityName, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnNew)
                                .addGap(3, 3, 3)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(btnEdit)
                                .addGap(43, 43, 43)
                                .addComponent(btnSave)
                                .addGap(55, 55, 55)
                                .addComponent(btnDelete)
                                .addGap(50, 50, 50)
                                .addComponent(btnInterview))))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack)
                        .addContainerGap(86, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNext)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtActivityName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBack)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtDifficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnNext)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnSave)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnInterview))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        selectedID = -1;
        
        txtActivityName.setText("");
        txtDifficulty.setText("");
        txtDate.setText("");

    FormUtils.enableFields(txtActivityName, txtDifficulty, txtDate);
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
       FormUtils.enableFields(txtActivityName, txtDifficulty, txtDate);
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

         try {
       
        if (txtActivityName.getText().trim().isEmpty() ||
            txtDifficulty.getText().trim().isEmpty() ||
            txtDate.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, "All fields are required!");
            return;
        }

        
        try {
            java.sql.Date.valueOf(txtDate.getText().trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format (YYYY-MM-DD)");
            return;
        }

        Connection con = DBConnection.getConnection();

        if (selectedID == -1) {

            
            CallableStatement stmt = con.prepareCall("{CALL InsertActivitySP(?, ?, ?, ?)}");

            stmt.setInt(1, SelectedPatient.patientID);
            stmt.setDate(2, java.sql.Date.valueOf(txtDate.getText().trim()));
            stmt.setString(3, txtActivityName.getText().trim());
            stmt.setString(4, txtDifficulty.getText().trim());

        stmt.execute();
            LoggerUtil.log("Inserted activity: " + txtActivityName.getText());

        } else {
            
            CallableStatement stmt = con.prepareCall("{CALL UpdateActivitySP(?, ?, ?, ?)}");

            stmt.setInt(1, selectedID);
            stmt.setDate(2, java.sql.Date.valueOf(txtDate.getText().trim()));
            stmt.setString(3, txtActivityName.getText().trim());
            stmt.setString(4, txtDifficulty.getText().trim());

            stmt.execute();
            LoggerUtil.log("Updated activity ID: " + selectedID);
           
        }

        JOptionPane.showMessageDialog(this, "Saved successfully!");

        loadTable();
    txtActivityName.setText("");
    txtDifficulty.setText("");
    txtDate.setText("");

    FormUtils.disableFields(txtActivityName, txtDifficulty, txtDate);
        selectedID = -1;

    } catch (Exception e) {
        e.printStackTrace();
    }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
        if (selectedID == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first");
            return;
        }

        Connection con = DBConnection.getConnection();

        CallableStatement stmt = con.prepareCall("{CALL DeleteActivitySP(?)}");
        stmt.setInt(1, selectedID);
        stmt.execute();
        LoggerUtil.log("Deleted activity ID: " + selectedID);

        JOptionPane.showMessageDialog(this, "Deleted successfully!");

        loadTable();
        txtActivityName.setText("");
        txtDifficulty.setText("");
        txtDate.setText("");

    FormUtils.disableFields(txtActivityName, txtDifficulty, txtDate);
        selectedID = -1;

    } catch (Exception e) {
        e.printStackTrace();
        LoggerUtil.log("ERROR: " + e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInterviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInterviewActionPerformed
        java.util.Map<String, String> results = ActivityInterviewUtils.runActivityInterview();

        txtActivityName.setText(results.get("ActivityName"));
        txtDifficulty.setText(results.get("Difficulty"));
        txtDate.setText(results.get("Date"));
        FormUtils.enableFields(txtActivityName, txtDifficulty, txtDate);
    }//GEN-LAST:event_btnInterviewActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        navigationUtils.switchForm(this, new patientSelection());
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        navigationUtils.switchForm(this, new SOBForm());
    }//GEN-LAST:event_btnNextActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new bATolerance().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnInterview;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblActivity;
    private javax.swing.JTextField txtActivityName;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtDifficulty;
    // End of variables declaration//GEN-END:variables
}
