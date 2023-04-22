package ca.etsmtl.log240.financej;

import javax.swing.table.AbstractTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountTableModel extends AbstractTableModel {



    private String[] columnNames = {"Name", "Description"};
    private Connection conn = null;

    /**
     * Instantiates a new Account list table model.
     *
     * @param DBConn the db conn
     */
    public AccountTableModel(Connection DBConn) {
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

    public void DeleteAllAccounts() {
        Statement s;
        String SQLString;

        if (conn != null) {
            try {
                s = conn.createStatement();
                SQLString = "DELETE FROM account";
                System.out.println(SQLString);
                s.executeUpdate(SQLString);
                fireTableDataChanged();
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in AccountListTableModel DeleteAllAccounts");
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
        Statement psSelect;
        try {
            if(Name.length()>1 && Name.matches("[a-zA-Z0-9]+") && Name.length() <= 50 && !Description.isEmpty() && Description.length() <= 250) {
                psInsert = conn.prepareStatement("insert into account(name, description) values(?,?)");
                psInsert.setString(1, Name);
                psInsert.setString(2, Description);

                psSelect = conn.createStatement();
                ResultSet resultSet = psSelect.executeQuery("Select count(*) from account");

                System.out.println("Line 428 -- " + resultSet.next());
                int beforeAddingAccount = resultSet.getInt(1);
                System.out.println("Account.java Line 426 -- This is the value of Before Adding Account : " + beforeAddingAccount);
                psInsert.executeUpdate();

                resultSet = psSelect.executeQuery("Select count(*) from account");
                resultSet.next();
                int afterAddingAccount = resultSet.getInt(1);
                System.out.println("Account.java Line 430 -- This is the value of after Adding Account : " + afterAddingAccount);

                System.out.println("New account object was added: " + Name);
                fireTableRowsInserted(getRowCount() + 1, getRowCount() + 1);
            } if (Name.length()<2 || !Name.matches("[a-zA-Z0-9]+") || Name.length() > 50) {
                throw new Throwable("name is less than 2 characters , contains illegal characters or is too long");
            } if (Description.isEmpty() || Description.length() > 250) {
                throw new Throwable("description is empty or too long");
            }
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown: AddAccount");
            System.out.println(e.getMessage());
            ErrorCode = 1;
        }

        return ErrorCode;
    }

}
