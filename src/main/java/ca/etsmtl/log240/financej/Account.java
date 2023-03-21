package ca.etsmtl.log240.financej;
/*
 * Account.java
 *
 * Created on March 5, 2008, 11:08 PM
 */

import java.awt.*;
import java.sql.*;
import javax.swing.table.*;
import javax.swing.*;


public class Account extends javax.swing.JDialog {
    /**
     * The type Account.
     *
     * @author rovitotv
     */

    private Connection conn = null;
    private AccountListTableModel dataModel;

    /**
     * Creates new form Account  @param parent the parent
     *
     * @param parent the parent
     * @param modal  the modal
     */

    public Account(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        AccountListTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * Set db connection.
     *
     * @param DBConn the db conn
     */
    public void SetDBConnection(Connection DBConn) {
        conn = DBConn;
        dataModel = new AccountListTableModel(conn);
        AccountListTable.setModel(dataModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        CloseButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        AccountListTable = new javax.swing.JTable();
        DeleteAccountButton = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        AddAccountButton = new javax.swing.JButton();
        NameTextField = new javax.swing.JTextField();
        NameTextField.setName("NAME_TEXT_FIELD");
        DescriptionTextField = new javax.swing.JTextField();
        DescriptionTextField.setName("DESCRIPTION_TEXT_FIELD");
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setTitle("Accounts");
        setModal(true);
        setName("AccountDialog"); // NOI18N

        CloseButton.setText("Close");
        CloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseButtonActionPerformed(evt);
            }
        });

        AccountListTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null}
                },
                new String [] {
                        "Name", "Description"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(AccountListTable);
        AccountListTable.getColumnModel().getColumn(0).setPreferredWidth(25);

        DeleteAccountButton.setText("Delete Account");
        DeleteAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteAccountButtonActionPerformed(evt);
            }
        });

        AddAccountButton.setText("Add Account");
        AddAccountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddAccountButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Name:");

        jLabel2.setText("Description:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(AddAccountButton))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(117, 117, 117)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel2))
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(NameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                                                                .addComponent(DescriptionTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE))))
                                                                .addGap(24, 24, 24))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(NameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(DescriptionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(AddAccountButton)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                        .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(279, Short.MAX_VALUE)
                                .addComponent(DeleteAccountButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CloseButton)
                                .addGap(31, 31, 31))
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(DeleteAccountButton)
                                .addComponent(CloseButton))
                                .addContainerGap())
        );

        pack();
        // Center the frame on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
    }// </editor-fold>//GEN-END:initComponents
    private void CloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_CloseButtonActionPerformed

    private void DeleteAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteAccountButtonActionPerformed
        if (AccountListTable.getSelectionModel().getLeadSelectionIndex() >= 0) {
            System.out.println("delete row:" + AccountListTable.getSelectionModel().getLeadSelectionIndex());
            dataModel.DeleteAccount(AccountListTable.getSelectionModel().getLeadSelectionIndex());
        } else { //nothing selected give error messge
            JOptionPane.showMessageDialog(this,
                    "No account selected to delete, please select an account then click the delete button.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DeleteAccountButtonActionPerformed

    private void AddAccountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddAccountButtonActionPerformed
        int ReturnCode;

        ReturnCode = dataModel.AddAccount(NameTextField.getText(), DescriptionTextField.getText());
        if (ReturnCode == 0) {
            NameTextField.setText("");
            DescriptionTextField.setText("");
        } else {
            JOptionPane.showMessageDialog(this,
                    "Error Adding account to database.  Make sure the account name you specified does not already exist or is empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_AddAccountButtonActionPerformed

    /**
     * Main.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Account dialog = new Account(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable AccountListTable;
    private javax.swing.JButton AddAccountButton;
    private javax.swing.JButton CloseButton;
    private javax.swing.JButton DeleteAccountButton;
    private javax.swing.JTextField DescriptionTextField;
    private javax.swing.JTextField NameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

/**
 * The type Account list table model.
 */
class AccountListTableModel extends AbstractTableModel {

    private String[] columnNames = {"Name", "Description"};
    private Connection conn = null;

    /**
     * Instantiates a new Account list table model.
     *
     * @param DBConn the db conn
     */
    public AccountListTableModel(Connection DBConn) {
        conn = DBConn;
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        ResultSet AccountResult;
        Statement s;

        if (conn != null) {
            try {
                s = conn.createStatement();
                AccountResult = s.executeQuery("select count(name) from account");
                while (AccountResult.next()) {
                    return AccountResult.getInt(1);
                }
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in AccountListTableModel getRowCount");
                e.printStackTrace();
            }
        }

        return 0;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        ResultSet AccountResult;
        Statement s;
        int CurrentRow = 0;

        if (conn != null) {
            try {
                s = conn.createStatement();
                AccountResult = s.executeQuery("select * from account order by name");
                while (AccountResult.next()) {
                    if (CurrentRow == row) {
                        if (col == 0) {
                            return AccountResult.getString(1);
                        } else if (col == 1) {
                            return AccountResult.getString(2);
                        }
                    }
                    CurrentRow++;
                }
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in AccountListTableModel getValueAt");
                e.printStackTrace();
            }
        }
        return "";
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.


        if (col == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void setValueAt(Object value, int row, int col) {
        String SQLString;

        String AccountName;
        try {
            AccountName = (String) getValueAt(row, 0);
            Statement s = conn.createStatement();
            SQLString = "update account set description ='" + (String) value + "' where name = '" + AccountName + "'";
            System.out.println(SQLString);
            s.execute(SQLString);

            fireTableCellUpdated(row, col);
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown: in setValueAt in Account.java");
            e.printStackTrace();
        }
    }

    /**
     * Delete account.
     *
     * @param row the row
     */
    public void DeleteAccount(int row) {
        Statement s;
        String AccountName;
        String SQLString;

        if (conn != null) {
            try {
                AccountName = (String) getValueAt(row, 0);
                s = conn.createStatement();
                SQLString = "DELETE FROM account WHERE name = '" + AccountName + "'";
                System.out.println(SQLString);
                s.executeUpdate(SQLString);
                fireTableDataChanged();
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in AccountListTableModel DeleteAccount");
                e.printStackTrace();
            }
        }
    }

    /**
     * Add account int.
     *
     * @param Name        the name
     * @param Description the description
     * @return the int
     */
    public int AddAccount(String Name, String Description) {
        int ErrorCode = 0;
        PreparedStatement psInsert;

        try {
            if(Name.isEmpty()==false){
                psInsert = conn.prepareStatement("insert into account(name, description) values(?,?)");
                psInsert.setString(1, Name);
                psInsert.setString(2, Description);

                psInsert.executeUpdate();
                fireTableRowsInserted(getRowCount() + 1, getRowCount() + 1);
            } else
                throw new Throwable();
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown: AddAccount");
            e.printStackTrace();
            ErrorCode = 1;
        }

        return ErrorCode;
    }
}
