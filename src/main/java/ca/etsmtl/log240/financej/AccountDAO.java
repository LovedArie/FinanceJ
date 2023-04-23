package ca.etsmtl.log240.financej;

import javax.swing.table.AbstractTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class AccountDAO extends AbstractTableModel {
    DerbyUtils derbyUtils = DerbyUtils.getInstance();

    public int getRowCount() {
        ResultSet AccountResult;
        Statement statement;

        if (derbyUtils.getConnection() != null) {
            try {
                statement = derbyUtils.getConnection().createStatement();
                AccountResult = statement.executeQuery("select count(name) from account");
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

    public Object getValueAt(int row, int col) {
        ResultSet AccountResult;
        Statement statement;
        int CurrentRow = 0;

        if (derbyUtils.getConnection() != null) {
            try {
                statement = derbyUtils.getConnection().createStatement();
                AccountResult = statement.executeQuery("select * from account order by name");
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

    public void setValueAt(Object value, int row, int col) {
        String SQLString;

        String AccountName;
        try {
            AccountName = (String) getValueAt(row, 0);
            Statement statement = derbyUtils.getConnection().createStatement();
            SQLString = "update account set description ='" + (String) value + "' where name = '" + AccountName + "'";
            System.out.println(SQLString);
            statement.execute(SQLString);

            fireTableCellUpdated(row, col);
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown: in setValueAt in AccountDialog.java");
            e.printStackTrace();
        }
    }

    public void DeleteAccount(int row) {
        Statement statement;
        String AccountName;
        String SQLString;

        if (derbyUtils.getConnection() != null) {
            try {
                AccountName = (String) getValueAt(row, 0);
                statement = derbyUtils.getConnection().createStatement();
                SQLString = "DELETE FROM account WHERE name = '" + AccountName + "'";
                System.out.println(SQLString);
                statement.executeUpdate(SQLString);
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

        if (derbyUtils.getConnection() != null) {
            try {
                System.out.println("AccountDAO line 102 -- Get Row Count : " + getRowCount());
                s = derbyUtils.getConnection().createStatement();
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

    public int AddAccount(String Name, String Description) {
        int ErrorCode = 0;
        PreparedStatement psInsert;
        Statement psSelect;

        System.out.print("We will now begin the process of adding an account : \n" + "Name.length()>1 : " );
        System.out.println(Name.length() > 1);
        System.out.print("Name.matches(\"[a-zA-Z0-9]+\") : ");
        System.out.println(Name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$"));
        System.out.print("Name.length() <= 50 : ");
        System.out.println(Name.length() <= 50);

        try {
            if(Name.length()>1 && Name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$") && Name.length() <= 50 && !Description.isEmpty() && Description.length() <= 250) {
                psInsert = derbyUtils.getConnection().prepareStatement("insert into account(name, description) values(?,?)");
                psInsert.setString(1, Name);
                psInsert.setString(2, Description);

                psSelect = derbyUtils.getConnection().createStatement();
                ResultSet resultSet = psSelect.executeQuery("Select count(*) from account");

                System.out.println("Line 428 -- " + resultSet.next());
                int beforeAddingAccount = resultSet.getInt(1);
                System.out.println("AccountDialog.java Line 426 -- This is the value of Before Adding Account : " + beforeAddingAccount);
                psInsert.executeUpdate();

                resultSet = psSelect.executeQuery("Select count(*) from account");
                resultSet.next();
                int afterAddingAccount = resultSet.getInt(1);
                System.out.println("AccountDialog.java Line 430 -- This is the value of after Adding Account : " + afterAddingAccount);

                System.out.println("New account object was added: " + Name);
                fireTableRowsInserted(getRowCount() + 1, getRowCount() + 1);
            } if (Name.length()<2 || !Name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$") || Name.length() > 50) {
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
