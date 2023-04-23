package ca.etsmtl.log240.financej;

import org.junit.Test;

import static org.junit.Assert.*;
public class AccountTableModelUnitTest {

    @Test
    public void testGetColumnCount() {
        AccountTableModel model = new AccountTableModel();
        int columnCount = model.getColumnCount();
        assertEquals(2, columnCount);
    }

    @Test
    public void testGetRowCount() {
        AccountTableModel model = new AccountTableModel();
        int rowCount = model.getRowCount();
        //TODO: add assertion based on expected value
    }

    @Test
    public void testGetColumnName() {
        AccountTableModel model = new AccountTableModel();
        String columnName1 = model.getColumnName(0);
        String columnName2 = model.getColumnName(1);
        assertEquals("Name", columnName1);
        assertEquals("Description", columnName2);
    }

    @Test
    public void testGetValueAt() {
        AccountTableModel model = new AccountTableModel();
        Object value = model.getValueAt(0, 0);
        //TODO: add assertion based on expected value
    }

    @Test
    public void testGetColumnClass() {
        AccountTableModel model = new AccountTableModel();
        Class columnClass = model.getColumnClass(0);
        //TODO: add assertion based on expected value
    }

    @Test
    public void testIsCellEditable() {
        AccountTableModel model = new AccountTableModel();
        boolean cellEditable = model.isCellEditable(0, 0);
        assertFalse(cellEditable);
        cellEditable = model.isCellEditable(0, 1);
        assertTrue(cellEditable);
    }

    @Test
    public void testSetValueAt() {
        AccountTableModel model = new AccountTableModel();
        model.setValueAt("new value", 0, 0);
        Object value = model.getValueAt(0, 0);
        //TODO: add assertion based on expected value
    }

    @Test
    public void testDeleteAccount() {
        AccountTableModel model = new AccountTableModel();
        model.DeleteAccount(0);
        int rowCount = model.getRowCount();
        //TODO: add assertion based on expected value
    }

    @Test
    public void testDeleteAllAccounts() {
        AccountTableModel model = new AccountTableModel();
        model.DeleteAllAccounts();
        int rowCount = model.getRowCount();
        //TODO: add assertion based on expected value
    }

    @Test
    public void testAddAccount() {
        AccountTableModel model = new AccountTableModel();
        int accountCount = model.getRowCount();
        model.AddAccount("test account", "test description");
        int newAccountCount = model.getRowCount();
        assertEquals(accountCount + 1, newAccountCount);
    }
}
