package ca.etsmtl.log240.financej;

import org.junit.Test;

//@RunWith(UISpec4JRunner.class)
public class AccountIntegrationTest extends FinancejAbstractTest {

    private AccountTableModel accountTableModel;
    DerbyUtils derbyUtils;

    public void setUp() throws Exception{
        super.setUp();
        accountTableModel = new AccountTableModel();
        derbyUtils = DerbyUtils.getInstance();
    }

    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Test
    public void testShouldReturnCorrectRowCount() {
        accountTableModel.DeleteAllAccounts();
        // Add some test data to the database
        accountTableModel.AddAccount("Test Account 1", "This is a test account");
        accountTableModel.AddAccount("Test Account 2", "This is another test account");
        accountTableModel.AddAccount("Te", "Savings");
        accountTableModel.AddAccount("Te", "$");
        accountTableModel.AddAccount("", "$");
        accountTableModel.AddAccount("antiquisantiquitateapeirianaperiamapeririapteiaaaaa", "$");
        accountTableModel.AddAccount("antiquisantiquitateapeirianaperiamapeririapte$", "$");
        accountTableModel.AddAccount("an", "bonis bono bonorum bonum brevi brevis breviter brute brutus cadere caecilii caeco caelo calere campum canes captet capti captiosa careat carere careret caret caritatem carum causa causae causam causas cedentem celeritas censes censet centurionum certa");
        accountTableModel.AddAccount("antiquisantiquitateapeirianaperiamapeririapteaaaaa", "bonis bono bonorum bonum brevi brevis breviter brute brutus cadere caecilii caeco caelo calere campum canes captet capti captiosa careat carere careret caret caritatem carum causa causae causam causas cedentem celeritas censes censet centurionum cer$");


        accountTableModel.DeleteAccount(1);
        // Check that the row count matches the number of accounts in the database
        assertEquals(accountTableModel.getRowCount(), 3);
    }

    public void testAccountRow(){}

    public void testAccountTableModelRowCount () {
        System.out.println(accountTableModel.getRowCount());

    }
}
