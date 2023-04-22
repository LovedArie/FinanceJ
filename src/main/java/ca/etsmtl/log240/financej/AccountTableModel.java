package ca.etsmtl.log240.financej;

public class AccountTableModel extends AccountDAO {



    private String[] columnNames = {"Name", "Description"};

    //AccountDAO accountDAO = new AccountDAO();

    /**
     * Instantiates a new Account list table model.
     *
     */
    public AccountTableModel() {

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return super.getRowCount();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return super.getValueAt(row, col);
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
        super.setValueAt(value, row, col);
    }

    /**
     * Delete account.
     *
     * @param row the row
     */
    public void DeleteAccount(int row) {
        super.DeleteAccount(row);
    }

    public void DeleteAllAccounts() {
        super.DeleteAllAccounts();
    }

    /**
     * Add account int.
     *
     * @param Name        the name
     * @param Description the description
     * @return the int
     */
    public int AddAccount(String Name, String Description) {
        return super.AddAccount(Name, Description);
    }

}
