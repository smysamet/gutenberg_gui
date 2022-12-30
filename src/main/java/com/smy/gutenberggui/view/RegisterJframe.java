package com.smy.gutenberggui.view;

import com.smy.gutenberggui.model.User;
import com.smy.gutenberggui.util.DbHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sam
 */
public class RegisterJframe extends javax.swing.JFrame {

    private DbHelper dbHelper;

    /**
     * Creates new form Login
     */
    public RegisterJframe(DbHelper dbHelper) {
        initComponents();
        this.dbHelper = dbHelper;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        emailJlabel = new javax.swing.JLabel();
        emailJTextField = new javax.swing.JTextField();
        passwordJLabel = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        registerJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        againJPasswordField = new javax.swing.JPasswordField();
        againPasswordJLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Register");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(750, 460));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        emailJlabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        emailJlabel.setText("Email:");
        jPanel1.add(emailJlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        emailJTextField.setBackground(new java.awt.Color(187, 187, 187));
        emailJTextField.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        emailJTextField.setForeground(new java.awt.Color(60, 63, 65));
        jPanel1.add(emailJTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, 388, -1));

        passwordJLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        passwordJLabel.setText("Password:");
        jPanel1.add(passwordJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, -1));

        jPasswordField1.setBackground(new java.awt.Color(187, 187, 187));
        jPasswordField1.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(60, 63, 65));
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, 390, -1));

        registerJButton.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        registerJButton.setText("Register");
        registerJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerJButtonActionPerformed(evt);
            }
        });
        jPanel1.add(registerJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 330, 140, 60));

        jLabel1.setFont(new java.awt.Font("Liberation Sans", 0, 48)); // NOI18N
        jLabel1.setText("Gutenberg GUI");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 40, -1, -1));

        againJPasswordField.setBackground(new java.awt.Color(187, 187, 187));
        againJPasswordField.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        againJPasswordField.setForeground(new java.awt.Color(60, 63, 65));
        jPanel1.add(againJPasswordField, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 390, -1));

        againPasswordJLabel.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        againPasswordJLabel.setText("Again Password:");
        jPanel1.add(againPasswordJLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void registerJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerJButtonActionPerformed
        
        
        if(this.emailJTextField.getText().isBlank() || this.emailJTextField.getText().isEmpty() || this.emailJTextField.getText() == null){
            JOptionPane.showMessageDialog(null,
                        "Please enter a valid email adress.",
                        "Wrong email adress.",
                        JOptionPane.WARNING_MESSAGE);

                return;
        }
        
        if(!new String(this.jPasswordField1.getPassword()).equals(new String(this.againJPasswordField.getPassword()))){
            JOptionPane.showMessageDialog(null,
                        "Password are not matching!",
                        "Error: passwords!",
                        JOptionPane.WARNING_MESSAGE);

                return;
        }
        
        Connection connection = null;
        try {
            String sql = "Select * from users Where LOWER(email)='" + this.emailJTextField.getText().toLowerCase()
                    + "'";
            connection = this.dbHelper.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null,
                        "There is an email already registered with : " + this.emailJTextField.getText() + ".",
                        "Email Already Taken",
                        JOptionPane.WARNING_MESSAGE);

                return;
            } else {
                connection = this.dbHelper.getConnection();
                String sqlRegister = "INSERT INTO users (`email`, `password`) VALUES ('" + this.emailJTextField.getText() + "', '" + new String(this.jPasswordField1.getPassword()) + "');";
                PreparedStatement statement1 = connection.prepareStatement(sqlRegister);
                statement1.execute();

                String sql1 = "Select * from users Where LOWER(email)='" + this.emailJTextField.getText().toLowerCase()
                        + "'";
                connection = this.dbHelper.getConnection();
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement.executeQuery(sql);
                resultSet2.next();
                User user = new User(this.emailJTextField.getText(), dbHelper);
                user.setId(resultSet2.getInt("id"));
                MainFrame mainFrame = new MainFrame(user, dbHelper);
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
                this.setVisible(false);
                this.dispose();

            }
        } catch (SQLException ex) {
            Logger.getLogger(RegisterJframe.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(RegisterJframe.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_registerJButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField againJPasswordField;
    private javax.swing.JLabel againPasswordJLabel;
    private javax.swing.JTextField emailJTextField;
    private javax.swing.JLabel emailJlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JLabel passwordJLabel;
    private javax.swing.JButton registerJButton;
    // End of variables declaration//GEN-END:variables
}
