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

    public int getRowcount(){
        return super.getRowCount();
    }

    public Object getValueAt(int row, int col){
        return super.getValueAt(row, col);
    }

    public void setValueAt(Object value, int row, int col){
        super.setValueAt(value, row, col);
    }

    public void DeleteCategory(int row){
        super.DeleteCategory(row);
    }

    public int AddCategory(String Name, String Description, String budget){
        return super.AddCategory(Name, Description, budget);
    }




}
