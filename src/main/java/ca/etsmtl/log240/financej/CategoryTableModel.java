package ca.etsmtl.log240.financej;

import javax.swing.table.AbstractTableModel;
import java.io.IOException;
import java.sql.*;

public class CategoryTableModel extends AbstractTableModel {

    private String[] columnNames = {"Name", "Description", "Budget"};
    DerbyUtils derbyUtils;

    /**
     * Instantiates a new Category list table model.
     *
     */
    public CategoryTableModel() {
        derbyUtils = DerbyUtils.getInstance();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        ResultSet AccountResult;
        Statement s;

        if (derbyUtils.getConnection() != null) {
            try {
                s = derbyUtils.getConnection().createStatement();
                AccountResult = s.executeQuery("select count(name) from category");
                while (AccountResult.next()) {
                    return AccountResult.getInt(1);
                }
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in CategoryListTableModel getRowCount");
                e.printStackTrace();
            }
        }

        return 0;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        ResultSet CategoryResult;
        Statement s;
        int CurrentRow = 0;

        if (derbyUtils.getConnection() != null) {
            try {
                s = derbyUtils.getConnection().createStatement();
                CategoryResult = s.executeQuery("select * from category order by name");
                while (CategoryResult.next()) {
                    if (CurrentRow == row) {
                        if (col == 0) {
                            return CategoryResult.getString(1);
                        } else if (col == 1) {
                            return CategoryResult.getString(2);
                        } else if (col == 2) {
                            return CategoryResult.getFloat(3);
                        }
                    }
                    CurrentRow++;
                }
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in CategoryListTableModel getValueAt");
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
        String SQLString = "";

        String CategoryName;
        try {
            CategoryName = (String) getValueAt(row, 0);
            Statement s = derbyUtils.getConnection().createStatement();
            if (col == 1) {
                SQLString = "update category set description ='" + (String) value + "' where name = '" + CategoryName + "'";
            } else if (col == 2) {
                String StrValue;

                StrValue = value.toString();
                SQLString = "update category set budget = " + StrValue + " where name = '" + CategoryName + "'";
            }
            System.out.println(SQLString);
            s.execute(SQLString);

            fireTableCellUpdated(row, col);
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown: in setValueAt in Category.java");
            e.printStackTrace();
        }
    }

    /**
     * Delete category.
     *
     * @param row the row
     */
    public void DeleteCategory(int row) {
        Statement s;
        String CategoryName;
        String SQLString;

        if (derbyUtils.getConnection() != null) {
            try {
                CategoryName = (String) getValueAt(row, 0);
                s = derbyUtils.getConnection().createStatement();
                SQLString = "DELETE FROM category WHERE name = '" + CategoryName + "'";
                System.out.println(SQLString);
                s.executeUpdate(SQLString);
                fireTableDataChanged();
            } catch (Throwable e) {
                System.out.println(" . . . exception thrown: in CategoryTableModel DeleteAccount");
                e.printStackTrace();
            }
        }
    }

    /**
     * Add category int.
     *
     * @param Name        the name
     * @param Description the description
     * @param budget      the budget
     * @return the int
     */
    public int AddCategory(String Name, String Description, String budget) {
        int ErrorCode = 0;
        PreparedStatement psInsert;


        try {
            double _budget = Double.parseDouble(budget);
            if (Name.isEmpty() == false && Name.length() >= 2 && Name.length() <= 50 && Name.matches("^[A-Za-z0-9 _]*[A-Za-z0-9][A-Za-z0-9 _]*$")
                    && Description.isEmpty() == false && Description.length() >= 1
                    && Description.length() <= 250 && _budget >= -100000000000000.00 && _budget <= 1000000000000.00) {
                psInsert = derbyUtils.getConnection().prepareStatement("insert into category(name, description, budget) values(?,?,?)");
                psInsert.setString(1, Name);
                psInsert.setString(2, Description);
                psInsert.setDouble(3, _budget);
                psInsert.executeUpdate();
                fireTableRowsInserted(getRowCount() + 1, getRowCount() + 1);
            } else
                throw new Throwable("Nom/Description/Budget ne correspondant pas aux critères");
        } catch (SQLException e) {
            System.out.println(". . . SQL Exception thrown: AddCategory" + "\n" + e.getMessage());
            ErrorCode = 1;
        } catch (IOException e) {
            System.out.println(". . . IO Exception thrown: AddCategory" + "\n" + e.getMessage());
            ErrorCode = 1;
        } catch (NumberFormatException e) {
            System.out.println(". . . NumberFormatException thrown: AddCategory" + "\n" + e.getMessage());
            ErrorCode = 1;
        } catch (Throwable e) {
            System.out.println(" . . . exception thrown: AddCategory");
            System.out.println(e.getMessage());
            ErrorCode = 1;
        }

        return ErrorCode;

    }
}
