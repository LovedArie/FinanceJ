package ca.etsmtl.log240.financej;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class AccountDAOUnitTest{

    private AccountDAO accountDAO;

    @Before
    public void setUp() {
        accountDAO = new AccountDAO() {
            @Override
            public int getColumnCount() {
                return 0;
            }
        };
    }

    @Test
    public void testGetRowCount() {
        int expectedRowCount = 2; //assuming there are 2 rows in the account table
        int actualRowCount = accountDAO.getRowCount();
        assertEquals(expectedRowCount, actualRowCount);
    }

    @Test
    public void testGetValueAt() {
        String expectedAccountName = "Account 1";
        String expectedDescription = "Description 1";
        Object actualAccountName = accountDAO.getValueAt(0, 0);
        Object actualDescription = accountDAO.getValueAt(0, 1);
        assertEquals(expectedAccountName, actualAccountName);
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testSetValueAt() {
        String expectedDescription = "New description";
        accountDAO.setValueAt(expectedDescription, 0, 1);
        Object actualDescription = accountDAO.getValueAt(0, 1);
        assertEquals(expectedDescription, actualDescription);
    }

    @Test
    public void testDeleteAccount() {
        int initialRowCount = accountDAO.getRowCount();
        accountDAO.DeleteAccount(0);
        int expectedRowCount = initialRowCount - 1;
        int actualRowCount = accountDAO.getRowCount();
        assertEquals(expectedRowCount, actualRowCount);
    }

    @Test
    public void testDeleteAllAccounts() {
        accountDAO.DeleteAllAccounts();
        int expectedRowCount = 0;
        int actualRowCount = accountDAO.getRowCount();
        assertEquals(expectedRowCount, actualRowCount);
    }

    @Test
    public void testAddAccount() {
        int initialRowCount = accountDAO.getRowCount();
        accountDAO.AddAccount("Account 4", "");
        accountDAO.AddAccount("", "Description 3");
        accountDAO.AddAccount("Account 3", "Description 3");
        int expectedRowCount = initialRowCount + 1;
        int actualRowCount = accountDAO.getRowCount();
        assertEquals(expectedRowCount, actualRowCount);
    }

}
