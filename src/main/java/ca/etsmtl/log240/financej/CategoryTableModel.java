package ca.etsmtl.log240.financej;

public class CategoryTableModel extends CategoryDAO {

    private String[] columnNames = {"Name", "Description", "Budget"};


    /**
     * Instantiates a new Category list table model.
     *
     */
    public CategoryTableModel() {

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
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




}
