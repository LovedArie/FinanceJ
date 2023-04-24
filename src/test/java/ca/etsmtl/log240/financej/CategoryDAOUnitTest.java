package ca.etsmtl.log240.financej;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CategoryDAOUnitTest {

    private CategoryDAO categoryDAO;

    @Before
    public void setUp() {
        categoryDAO = new CategoryDAO() {
            @Override
            public int getColumnCount() {
                return 0;
            }
            // Override abstract methods if needed for testing
        };
    }

    @Test
    public void testGetRowCount() {
        int rowCount = categoryDAO.getRowCount();
        assertEquals(0, rowCount);
    }

    @Test
    public void testGetValueAt() {
        Object value = categoryDAO.getValueAt(0, 0);
        assertEquals("", value);
    }

    @Test
    public void testSetValueAt() {
        CategoryDAO categoryDAO = new CategoryDAO() {
            @Override
            public int getColumnCount() {
                return 0;
            }
            // Implement any abstract methods or overrides as necessary
        };
        // Insert some test data into the database for this test
        // ...

        // Test updating the description column of a category
        int row = 0;
        int col = 1;
        String newValue = "New description";
        categoryDAO.setValueAt(newValue, row, col);

        // Check that the value was updated in the database
        // ...

        // Test updating the budget column of a category
        row = 1;
        col = 2;
        double newBudget = 500.0;
        categoryDAO.setValueAt(newBudget, row, col);

        // Check that the value was updated in the database
        // ...
    }

    @Test
    public void testDeleteCategory() {
        CategoryDAO categoryDAO = new CategoryDAO() {
            @Override
            public int getColumnCount() {
                return 0;
            }
            // Implement any abstract methods or overrides as necessary
        };
        // Insert some test data into the database for this test
        // ...

        // Test deleting a category
        int row = 0;
        categoryDAO.DeleteCategory(row);

        // Check that the category was removed from the database
        // ...
    }

    @Test
    public void testAddCategory() {
        int initialRowCount = categoryDAO.getRowCount();
        categoryDAO.AddCategory("testName", "testDescription", "100.0");
        int newRowCount = categoryDAO.getRowCount();
        assertEquals(initialRowCount + 1, newRowCount);
        // TODO: Write assertions to verify that the new category was added to the database and to the model
    }
}
