
package its340_andreas_anamv;
import javax.swing.JOptionPane;
import java.sql.*;


public class patientDemographics extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(patientDemographics.class.getName());

    /**
     * Creates new form patientDemographics
     */
    // Define the global array for all demographics fields
    private javax.swing.text.JTextComponent[] demographicsFields;
    
    public patientDemographics() {
        initComponents();
        
        // Initialize the array with all 20 text components
        demographicsFields = new javax.swing.text.JTextComponent[]{
            tb_FirstName, tb_LastName, tb_PrevLastName, tb_SSN,
            tb_Address, tb_City, tb_State, tb_Zip, tb_Country,
            tb_MobilePhone, tb_Email, tb_Gender, tb_Citizenship,
            tb_Ethnic, tb_MaritalStatus, tb_PrimaryHCP, tb_NOKName,
            tb_NOKRelationship, tb_EmergPhone, ta_Comments
        };

        int currentPID = SelectedPatient.patientID; 
        if (currentPID != -1) {
            loadPatientData(currentPID); 
        } else {
            // If no patient was selected, start a fresh "New Patient" form
            btnNewActionPerformed(null);
        }
        
        setFormMode(false);
        
        // so it doesnt default to rando tiem
        jSpinnerDOB.setValue(new java.util.Date());
        
        // Layout fixes for comments
        jScrollPane1.setPreferredSize(new java.awt.Dimension(360, 100));
        ta_Comments.setRows(5); 
        jPanel1.revalidate();
    }
    
    private void setFormMode(boolean isEditable) {
        java.awt.Color bgColor = isEditable ? java.awt.Color.WHITE : new java.awt.Color(240, 240, 240);

        // 1. Bulk update all text fields and text areas
        for (javax.swing.text.JTextComponent c : demographicsFields) {
            c.setEditable(isEditable);
            c.setBackground(bgColor);
        }

        // 2. Handle the JSpinner (which isn't a JTextComponent)
        jSpinnerDOB.setEnabled(isEditable);
        if (jSpinnerDOB.getEditor() instanceof javax.swing.JSpinner.DefaultEditor) {
            javax.swing.JTextField textField = ((javax.swing.JSpinner.DefaultEditor) jSpinnerDOB.getEditor()).getTextField();
            textField.setBackground(bgColor);
        }

        lblModeStatus.setText(isEditable ? "Mode: Editing" : "Mode: View (Locked)");
    }
    
    //bridge that takes the raw data from your MySQL ResultSet and "pastes" it into your 21 JTextFields.
    private void loadPatientData(int pid) {
    try (Connection conn = dbUtils.ConnectToMySqlDB("root", "")) {
        ResultSet rs = patientDemographicsDBUtils.getPatientByID(conn, pid);
        
        if (rs != null && rs.next()) {
            // 1. Set the ID field (Prefix it so your Delete button logic works)
            lbl_PatientID.setText("Patient ID: " + rs.getInt("PatientID"));
            
            // 2. Fill the text fields
            tb_LastName.setText(rs.getString("PtLastName"));
            tb_PrevLastName.setText(rs.getString("PtPreviousLastName"));
            tb_FirstName.setText(rs.getString("PtFirstName"));
            tb_Address.setText(rs.getString("HomeAddress1"));
            tb_City.setText(rs.getString("HomeCity"));
            tb_State.setText(rs.getString("HomeState/Province/Region"));
            tb_Zip.setText(rs.getString("HomeZip"));
            tb_Country.setText(rs.getString("Country"));
            tb_Citizenship.setText(rs.getString("Citizenship"));
            tb_MobilePhone.setText(rs.getString("PtMobilePhone"));
            tb_EmergPhone.setText(rs.getString("EmergencyPhoneNumber"));
            tb_Email.setText(rs.getString("EmailAddress"));
            tb_SSN.setText(rs.getString("PtSS#"));
            
            // FIX: Load the Date into the Spinner properly
            java.sql.Date dbDate = rs.getDate("DOB");
            if (dbDate != null) {
                jSpinnerDOB.setValue(new java.util.Date(dbDate.getTime()));
            }

            tb_Gender.setText(rs.getString("Gender"));
            tb_Ethnic.setText(rs.getString("EthnicAssociation"));
            tb_MaritalStatus.setText(rs.getString("MaritalStatus"));
            tb_PrimaryHCP.setText(rs.getString("CurrentPrimaryHCP"));
            ta_Comments.setText(rs.getString("Comments"));
            tb_NOKName.setText(rs.getString("NextOfKin"));
            tb_NOKRelationship.setText(rs.getString("NextOfKinRelationshipToPatient"));

            JOptionPane.showMessageDialog(this, "Patient #" + pid + " loaded successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Could not find Patient ID: " + pid);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Load Error: " + e.getMessage());
        e.printStackTrace(); // Good for the console
    }
}
    

     private String[] getFieldsArray() {
    // 1. Convert Spinner date to SQL string
    java.util.Date utilDate = (java.util.Date) jSpinnerDOB.getValue();
    String formattedDate = (utilDate != null) ? new java.sql.Date(utilDate.getTime()).toString() : "";

    // 2. Return data in the order required by your Stored Procedure
    return new String[]{
        tb_LastName.getText(), tb_PrevLastName.getText(), tb_FirstName.getText(),
        tb_Address.getText(), tb_City.getText(), tb_State.getText(),
        tb_Zip.getText(), tb_Country.getText(), tb_Citizenship.getText(),
        tb_MobilePhone.getText(), tb_EmergPhone.getText(), tb_Email.getText(),
        tb_SSN.getText(), 
        formattedDate,//dob
        tb_Gender.getText(),
        tb_Ethnic.getText(), tb_MaritalStatus.getText(), tb_PrimaryHCP.getText(),
        ta_Comments.getText(), tb_NOKName.getText(), tb_NOKRelationship.getText()
    };
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanelHeader = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lbl_PatientID = new javax.swing.JLabel();
        jPanelIdentity = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tb_FirstName = new javax.swing.JTextField();
        tb_LastName = new javax.swing.JTextField();
        tb_PrevLastName = new javax.swing.JTextField();
        tb_SSN = new javax.swing.JTextField();
        jSpinnerDOB = new javax.swing.JSpinner();
        jPanelContact = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        tb_Address = new javax.swing.JTextField();
        tb_City = new javax.swing.JTextField();
        tb_State = new javax.swing.JTextField();
        tb_Zip = new javax.swing.JTextField();
        tb_Country = new javax.swing.JTextField();
        tb_MobilePhone = new javax.swing.JTextField();
        tb_Email = new javax.swing.JTextField();
        jPanelEmergKin = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        tb_EmergPhone = new javax.swing.JTextField();
        tb_NOKRelationship = new javax.swing.JTextField();
        tb_NOKName = new javax.swing.JTextField();
        jPanelDetails = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tb_Gender = new javax.swing.JTextField();
        tb_Citizenship = new javax.swing.JTextField();
        tb_PrimaryHCP = new javax.swing.JTextField();
        tb_MaritalStatus = new javax.swing.JTextField();
        tb_Ethnic = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_Comments = new javax.swing.JTextArea();
        btnNext3 = new javax.swing.JButton();
        btnBack3 = new javax.swing.JButton();
        jPanelButtons = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        lblModeStatus = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(650, 1200));

        jScrollPane2.setMinimumSize(new java.awt.Dimension(600, 1200));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(600, 1200));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelHeader.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Patient Demographics");

        lbl_PatientID.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbl_PatientID.setForeground(new java.awt.Color(0, 0, 0));
        lbl_PatientID.setText("Patient ID:");

        javax.swing.GroupLayout jPanelHeaderLayout = new javax.swing.GroupLayout(jPanelHeader);
        jPanelHeader.setLayout(jPanelHeaderLayout);
        jPanelHeaderLayout.setHorizontalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addComponent(lbl_PatientID, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelHeaderLayout.setVerticalGroup(
            jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbl_PatientID))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanelIdentity.setBackground(new java.awt.Color(255, 255, 255));
        jPanelIdentity.setForeground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Primary Identification");

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("First Name: ");
        jLabel7.setToolTipText("");

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Last Name: ");

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Previous Last Name:");

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("SS#:");

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("DOB:");

        tb_FirstName.setText("jTextField2");

        tb_LastName.setText("jTextField3");
        tb_LastName.addActionListener(this::tb_LastNameActionPerformed);

        tb_PrevLastName.setText("jTextField4");
        tb_PrevLastName.addActionListener(this::tb_PrevLastNameActionPerformed);

        tb_SSN.setText("jTextField5");

        jSpinnerDOB.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(1776057238088L), null, null, java.util.Calendar.DAY_OF_MONTH));

        javax.swing.GroupLayout jPanelIdentityLayout = new javax.swing.GroupLayout(jPanelIdentity);
        jPanelIdentity.setLayout(jPanelIdentityLayout);
        jPanelIdentityLayout.setHorizontalGroup(
            jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIdentityLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelIdentityLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tb_FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10))
                    .addGroup(jPanelIdentityLayout.createSequentialGroup()
                        .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tb_LastName)
                            .addComponent(jSpinnerDOB))
                        .addGap(186, 186, 186)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelIdentityLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_SSN)
                    .addComponent(tb_PrevLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanelIdentityLayout.setVerticalGroup(
            jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelIdentityLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(tb_FirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tb_PrevLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tb_SSN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(tb_LastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelIdentityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jSpinnerDOB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12))
        );

        jPanelContact.setBackground(new java.awt.Color(255, 255, 255));
        jPanelContact.setForeground(new java.awt.Color(0, 0, 0));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Contact & Address");

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Address:");

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("City:");

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("State:");

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Zip Code: ");

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Country: ");

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Mobile Phone: ");

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Email Address: ");

        tb_Address.setText("jTextField1");

        tb_City.setText("jTextField2");

        tb_State.setText("jTextField3");

        tb_Zip.setText("jTextField4");

        tb_Country.setText("jTextField5");

        tb_MobilePhone.setText("jTextField6");

        tb_Email.setText("jTextField7");

        jPanelEmergKin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("Emergency & Next of Kin");

        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Next of Kin: ");

        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Relationship: ");

        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Emergency Phone: ");

        tb_EmergPhone.setText("jTextField3");

        tb_NOKRelationship.setText("jTextField4");

        tb_NOKName.setText("jTextField5");

        javax.swing.GroupLayout jPanelEmergKinLayout = new javax.swing.GroupLayout(jPanelEmergKin);
        jPanelEmergKin.setLayout(jPanelEmergKinLayout);
        jPanelEmergKinLayout.setHorizontalGroup(
            jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmergKinLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addGroup(jPanelEmergKinLayout.createSequentialGroup()
                        .addGroup(jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel37)
                            .addComponent(jLabel36))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tb_NOKName, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                            .addComponent(tb_NOKRelationship)
                            .addComponent(tb_EmergPhone))))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        jPanelEmergKinLayout.setVerticalGroup(
            jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelEmergKinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(tb_NOKName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(tb_NOKRelationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelEmergKinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(tb_EmergPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelContactLayout = new javax.swing.GroupLayout(jPanelContact);
        jPanelContact.setLayout(jPanelContactLayout);
        jPanelContactLayout.setHorizontalGroup(
            jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContactLayout.createSequentialGroup()
                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelContactLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanelContactLayout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tb_Zip, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelContactLayout.createSequentialGroup()
                                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel17)
                                    .addGroup(jPanelContactLayout.createSequentialGroup()
                                        .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelContactLayout.createSequentialGroup()
                                                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel13))
                                                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(jPanelContactLayout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tb_City, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
                                                    .addGroup(jPanelContactLayout.createSequentialGroup()
                                                        .addGap(18, 18, 18)
                                                        .addComponent(tb_Address))))
                                            .addGroup(jPanelContactLayout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(tb_State, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel16)
                                            .addComponent(jLabel18))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tb_Country, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(tb_MobilePhone)
                                    .addComponent(tb_Email)))))
                    .addComponent(jPanelEmergKin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelContactLayout.setVerticalGroup(
            jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelContactLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel16)
                    .addComponent(tb_Address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_Country, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(tb_City, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_MobilePhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(tb_State, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelContactLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tb_Zip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addComponent(jPanelEmergKin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelDetails.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Personal & Medical Details");

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Gender: ");

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Citizenship: ");

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Ethnic Association:");

        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Marital Status: ");

        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Primary HCP: ");

        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Comments: ");

        tb_Gender.setText("jTextField1");

        tb_Citizenship.setText("jTextField2");

        tb_PrimaryHCP.setText("jTextField3");

        tb_MaritalStatus.setText("jTextField3");

        tb_Ethnic.setText("jTextField3");
        tb_Ethnic.addActionListener(this::tb_EthnicActionPerformed);

        ta_Comments.setColumns(20);
        ta_Comments.setRows(5);
        ta_Comments.setMinimumSize(new java.awt.Dimension(360, 100));
        ta_Comments.setPreferredSize(new java.awt.Dimension(360, 100));
        jScrollPane1.setViewportView(ta_Comments);

        btnNext3.setText("Next");
        btnNext3.addActionListener(this::btnNextActionPerformed);

        btnBack3.setText("Back");
        btnBack3.addActionListener(this::btnBackActionPerformed);

        javax.swing.GroupLayout jPanelDetailsLayout = new javax.swing.GroupLayout(jPanelDetails);
        jPanelDetails.setLayout(jPanelDetailsLayout);
        jPanelDetailsLayout.setHorizontalGroup(
            jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsLayout.createSequentialGroup()
                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelDetailsLayout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tb_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDetailsLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel3))
                            .addGroup(jPanelDetailsLayout.createSequentialGroup()
                                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tb_Citizenship, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tb_PrimaryHCP, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(25, 25, 25)
                        .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tb_Ethnic, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tb_MaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(67, Short.MAX_VALUE))
            .addGroup(jPanelDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(btnBack3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnNext3)
                        .addGap(60, 60, 60))
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(37, 37, 37))))
        );
        jPanelDetailsLayout.setVerticalGroup(
            jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDetailsLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_Gender, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tb_MaritalStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(tb_Citizenship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(tb_Ethnic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tb_PrimaryHCP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addGroup(jPanelDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnNext3))
                    .addGroup(jPanelDetailsLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnBack3)))
                .addGap(247, 247, 247))
        );

        jPanelButtons.setBackground(new java.awt.Color(255, 255, 255));

        btnNew.setText("New");
        btnNew.addActionListener(this::btnNewActionPerformed);

        btnSave.setText("Save");
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSave.addActionListener(this::btnSaveActionPerformed);

        btnEdit.setText("Edit");
        btnEdit.addActionListener(this::btnEditActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        lblModeStatus.setForeground(new java.awt.Color(0, 0, 0));
        lblModeStatus.setText("Mode: ");

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelButtonsLayout.createSequentialGroup()
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete))
                    .addGroup(jPanelButtonsLayout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnEdit))
                    .addComponent(lblModeStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblModeStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnEdit))
                .addGap(18, 18, 18)
                .addGroup(jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSave)
                    .addComponent(btnDelete)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanelContact, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(237, 237, 237)))
                    .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelIdentity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(345, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelIdentity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDetails, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(185, 185, 185))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1136, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
    // 1. Safety Check: Make sure a record is actually loaded
    String rawID = lbl_PatientID.getText().trim();
    
    // Check if label is empty or just contains the default prefix
    if (rawID.isEmpty() || rawID.equals("Patient ID:")) {
        JOptionPane.showMessageDialog(this, "No patient record is currently selected.");
        return;
    }

    // 2. Confirm Deletion (Medical records should never be deleted by accident!)
    int confirm = JOptionPane.showConfirmDialog(this,
        "Are you sure you want to mark this patient record as DELETED?\n" +
        "This will deactivate the record in the system.",
        "Confirm Soft Delete",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);

    if (confirm == JOptionPane.YES_OPTION) {
        try (Connection conn = dbUtils.ConnectToMySqlDB("root", "")) {
            
            // FIX: Strip the prefix so we only parse the numeric ID
            String pidText = rawID.replace("Patient ID:", "").trim();
            int pid = Integer.parseInt(pidText);

            // 3. Call your utility method (specific to Patient Demographics)
            int result = patientDemographicsDBUtils.deletePatient(conn, pid);

            if (result >= 0) {
                JOptionPane.showMessageDialog(this, "Record successfully deactivated.");

                // 4. Clear the form and lock it (reusing your New button logic)
                btnNewActionPerformed(null);
                setFormMode(false);
            }
        } catch (Exception e) {
            // Logs the error to console and shows the user a popup
            System.err.println("Patient Delete Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Delete Error: " + e.getMessage());
        }
    }


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        // 2. Switch the form to Edit Mode
        // This turns the fields white and makes them editable via your helper function
        setFormMode(true);

        // 3. Update the status label to show we are in an active edit session
        lblModeStatus.setText("Mode: Editing Existing Patient");

        // 4. Optional: Set focus to the first name field to start the edit
        tb_FirstName.requestFocus();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!validationUtils.isNotEmpty(tb_FirstName, "First Name") ||
            !validationUtils.isNotEmpty(tb_LastName, "Last Name") ||
            !validationUtils.isNotEmpty(tb_SSN, "SSN")) {
            return;
        }

        try (Connection conn = dbUtils.ConnectToMySqlDB("root", "")) {
            String[] data = getFieldsArray();
            int result;

            // Clean the ID from the label prefix
            String pidOnly = lbl_PatientID.getText().replace("Patient ID: ", "").trim();

            if (pidOnly.isEmpty() || pidOnly.equals("0")) {
                result = patientDemographicsDBUtils.insertPatient(conn, data);
            } else {
                int pid = Integer.parseInt(pidOnly);
                result = patientDemographicsDBUtils.updatePatient(conn, pid, data);
            }

            if (result >= 0) {
                JOptionPane.showMessageDialog(this, "Patient Record Saved!");
                setFormMode(false);
            }

        } catch (SQLException sqlEx) {
            // THIS IS THE SQL SPECIFIC ERROR
            String errorMessage = "SQL Error State: " + sqlEx.getSQLState() +
            "\nError Code: " + sqlEx.getErrorCode() +
            "\nMessage: " + sqlEx.getMessage();

            System.err.println(errorMessage); // Prints to NetBeans console in red
            JOptionPane.showMessageDialog(this, errorMessage, "Database Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "General Error: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // loop to clear everything
        for (javax.swing.text.JTextComponent comp : demographicsFields) {
            comp.setText("");
        }

        setFormMode(true);
        lbl_PatientID.setText("Patient ID: 0");
        tb_FirstName.requestFocus(); 
    }//GEN-LAST:event_btnNewActionPerformed

    private void tb_PrevLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_PrevLastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_PrevLastNameActionPerformed

    private void tb_LastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_LastNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_LastNameActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        navigationUtils.switchForm(this, new medicalHistory());
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

      navigationUtils.switchForm(this, new patientSelection());
    }//GEN-LAST:event_btnBackActionPerformed

    private void tb_EthnicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tb_EthnicActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_EthnicActionPerformed


    /**
     * @param args the command line arguments
     */
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
        java.awt.EventQueue.invokeLater(() -> new patientDemographics().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack3;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext3;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JPanel jPanelContact;
    private javax.swing.JPanel jPanelDetails;
    private javax.swing.JPanel jPanelEmergKin;
    private javax.swing.JPanel jPanelHeader;
    private javax.swing.JPanel jPanelIdentity;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinnerDOB;
    private javax.swing.JLabel lblModeStatus;
    private javax.swing.JLabel lbl_PatientID;
    private javax.swing.JTextArea ta_Comments;
    private javax.swing.JTextField tb_Address;
    private javax.swing.JTextField tb_Citizenship;
    private javax.swing.JTextField tb_City;
    private javax.swing.JTextField tb_Country;
    private javax.swing.JTextField tb_Email;
    private javax.swing.JTextField tb_EmergPhone;
    private javax.swing.JTextField tb_Ethnic;
    private javax.swing.JTextField tb_FirstName;
    private javax.swing.JTextField tb_Gender;
    private javax.swing.JTextField tb_LastName;
    private javax.swing.JTextField tb_MaritalStatus;
    private javax.swing.JTextField tb_MobilePhone;
    private javax.swing.JTextField tb_NOKName;
    private javax.swing.JTextField tb_NOKRelationship;
    private javax.swing.JTextField tb_PrevLastName;
    private javax.swing.JTextField tb_PrimaryHCP;
    private javax.swing.JTextField tb_SSN;
    private javax.swing.JTextField tb_State;
    private javax.swing.JTextField tb_Zip;
    // End of variables declaration//GEN-END:variables
}
