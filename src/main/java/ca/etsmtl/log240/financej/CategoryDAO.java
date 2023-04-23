package ca.etsmtl.log240.financej;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class CategoryDAO extends AbstractTableModel {
    DerbyUtils derbyUtils;

    public CategoryDAO(){
        derbyUtils = DerbyUtils.getInstance();
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



}
