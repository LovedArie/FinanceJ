package ca.etsmtl.log240.financej;

import org.junit.Test;

import static com.groupdocs.conversion.internal.c.a.ms.NUnit.Framework.msAssert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CategoryTableModelUnitTest {
    private CategoryTableModel categoryTableModel = new CategoryTableModel();

    @Test
    public void testGetColumnCount() {
        assertEquals(3, categoryTableModel.getColumnCount());
    }

    @Test
    public void testGetColumnName() {
        assertEquals("Name", categoryTableModel.getColumnName(0));
        assertEquals("Description", categoryTableModel.getColumnName(1));
        assertEquals("Budget", categoryTableModel.getColumnName(2));
    }

    @Test
    public void testGetColumnClass() {
        assertEquals(String.class, categoryTableModel.getColumnClass(0));
        assertEquals(String.class, categoryTableModel.getColumnClass(1));
        assertEquals(String.class, categoryTableModel.getColumnClass(2));
    }

    @Test
    public void testIsCellEditable() {
        assertFalse(categoryTableModel.isCellEditable(0, 0));
        assertTrue(categoryTableModel.isCellEditable(0, 1));
        assertTrue(categoryTableModel.isCellEditable(0, 2));
    }

    @Test
    public void testGetRowCount() {
        assertEquals(0, categoryTableModel.getRowcount());
        categoryTableModel.AddCategory("Test Name", "Test Description", "10");
        assertEquals(1, categoryTableModel.getRowcount());
    }

    @Test
    public void testGetValueAt() {
        categoryTableModel.AddCategory("Test Name", "Test Description", "10");
        assertEquals("Test Name", categoryTableModel.getValueAt(0, 0));
        assertEquals("Test Description", categoryTableModel.getValueAt(0, 1));
        assertEquals("10", categoryTableModel.getValueAt(0, 2));
    }

    @Test
    public void testSetValueAt() {
        categoryTableModel.AddCategory("Test Name", "Test Description", "10");
        categoryTableModel.setValueAt("New Name", 0, 0);
        assertEquals("New Name", categoryTableModel.getValueAt(0, 0));
    }

    @Test
    public void testDeleteCategory() {
        categoryTableModel.AddCategory("Test Name", "Test Description", "10");
        assertEquals(1, categoryTableModel.getRowcount());
        categoryTableModel.DeleteCategory(0);
        assertEquals(0, categoryTableModel.getRowcount());
    }

    @Test
    public void testAddCategory() {
        int initialRowCount = categoryTableModel.getRowcount();
        categoryTableModel.AddCategory("Test Name", "Test Description", "10");
        assertEquals(initialRowCount + 1, categoryTableModel.getRowcount());
    }

}
