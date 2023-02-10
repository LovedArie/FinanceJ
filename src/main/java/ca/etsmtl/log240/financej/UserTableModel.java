package ca.etsmtl.log240.financej;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class UserTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;
    private ArrayList<User> users;
    private String[] columnNames = { "Username", "Password", "Role" };

    public UserTableModel(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public int getRowCount() {
        return users.size();
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return user.getUsername();
            case 1:
                return user.getPassword();
            case 2:
                return user.getRole();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}