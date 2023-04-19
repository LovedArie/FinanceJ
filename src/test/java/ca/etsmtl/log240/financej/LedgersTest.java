package ca.etsmtl.log240.financej;

import org.junit.AfterClass;
import org.uispec4j.*;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

import java.util.Date;
import java.util.List;


public class LedgersTest extends FinancejAbstractTest {
    private Table ledgerTable;

    private List<Date> datesValides = ValidFieldsGenerator.getValidRandomDate();
    private Trigger ret;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        //closing connection to database
        exitButton.click();
    }


//    @Test
//    public void test() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", 200, "te", "Te")).run();
//    }

    public void testT0_20(){
        WindowInterceptor.init(accountsButton.triggerClick()).process(new WindowHandler() {
            @Override
            public Trigger process(Window window) throws Exception {
                System.out.println("What about here");

                Table accountTable = window.getTable();
                int rowCount = accountTable.getRowCount();

                if (accountTable.getRowCount() > 0) {
                    System.out.println("Account row Count : " + accountTable.getRowCount());

//                    System.out.println("This is a value : " + accountTable.getContentAt(3,0));
//                    Object cellValue = accountTable.getContentAt(0, 0);
                    for(int row = 0; row < rowCount - 1; row ++){
                        Object cellValue = accountTable.getContentAt(row, 0);
                        System.out.println("Testing : " + row);
                        if(cellValue.equals("Te")){
                            accountTable.selectRow(row);
                            window.getButton("Delete Account").click();
                            System.out.println("Account '" + "Te" + "' was Deleted");
                            break;
                        }
                    }
                }
                System.out.println("Before Adding");
                window.getTextBox("NAME_TEXT_FIELD").setText("Te");
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText("Te");
                window.getButton("Add Account").click();
                System.out.println("After Adding");

                accountTable.getRowCount();
                System.out.println("this is the row count of account table : " + accountTable.getRowCount());

//                for (int currentRow = 0; currentRow < accountTable.getRowCount(); currentRow ++){
//                    System.out.println(accountTable.getContentAt(currentRow, 0));
//                }
                return window.getButton("Close").triggerClick();
            }
        }).run();

        WindowInterceptor.init(categoriesButton.triggerClick()).process(new WindowHandler() {
            @Override
            public Trigger process(Window window) throws Exception {

                Table categoryTable = window.getTable();
                if (categoryTable.getRowCount() > 0) {
                    for(int row = 0; row < categoryTable.getRowCount(); row++){
                        Object cellValue = categoryTable.getContentAt(row, 0);
                        if(cellValue.equals("Te")){
                            categoryTable.selectRow(row);
                            window.getButton("Delete Category").click();
                            System.out.println("Account '" + "Te" + "' was Deleted");
                            break;
                        }
                    }
                }

                window.getTextBox("NAME_TEXT_FIELD").setText("Te");
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText("Te");
                window.getTextBox("BUDGET_TEXT_FIELD").setText("100000000");

                window.getButton("Add Category").click();

                categoryTable.getRowCount();
                System.out.println("this is the row count of account table : " + categoryTable.getRowCount());

                for (int currentRow = 0; currentRow < categoryTable.getRowCount(); currentRow ++){
                    System.out.println(categoryTable.getContentAt(currentRow, 0));
                }


                return window.getButton("Close").triggerClick();
            }
        }).run();
        System.out.println("Last Test");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "te", false, "$", "Te", -1000000.00D, "Te")).run();
    }

//    public void testT0_21(){
//
//    }
//
//    public void testT0_22(){
//
//    }
//
//    public void testT0_23(){
//
//    }
//
//    public void testT0_24(){
//
//    }
//
//    public void testT0_25(){
//
//    }
//
//    public void testT0_26(){
//
//    }
//
//    public void testT0_27(){
//
//    }
//
//    public void testT0_28(){
//
//    }
//
//    public void testT0_29(){
//
//    }
//
//    public void testT0_30(){
//
//    }
//
//    public void testT0_31(){
//
//    }
//
//    public void testT0_32(){
//
//    }
//
//    public void testT0_33(){
//
//    }
//
//    public void testT0_34(){
//
//    }
//
//    public void testT0_35(){
//
//    }
//
//    public void testT0_36(){
//
//    }
//
//    public void testT0_37(){
//
//    }
//
//    public void testT0_38(){
//
//    }
//
//    public void testT0_39(){
//
//    }
//
//    public void testT0_40(){
//
//    }
//
//    public void testT0_41(){
//
//    }
//
//    public void testT0_42(){
//
//    }
//
//    public void testT0_43(){
//
//    }
//
//    public void testT0_44(){
//
//    }
//
//    public void testT0_45(){
//
//    }
//
//    public void testT0_46(){
//
//    }
//
//    public void testT0_47(){
//
//    }
//
//    public void testT0_48(){
//
//    }
//
//    public void testT0_49(){
//
//    }
//
//    public void testT0_50(){
//
//    }
//
//    public void testT0_51(){
//
//    }

    //T1-61
//    @Test
//    public void testAddAndDeleteLedgersWithValidDate() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000/01/31", "$", 1, "te", "Te")).run();
//    }

    //T1-62 //TODO : Fix this test with amount
//    @Test
//    public void testAddAndDeleteLedgersWithInvalidAmount() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "te", "Te")).run();
//    }

    //T1-63
//    @Test
//    public void testAddAndDeleteLedgersWithValidAmount() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "careret caret caritatem carum causa causae causam i", "Te")).run();
//    }

    //T1-64
//    @Test
//    public void testAddAndDeleteLedgersWithInvalidPayee() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "careret caret caritatem carum causa causae causam i%", "Te")).run();
//    }

    //T1-65
//    @Test
//    public void testAddAndDeleteLedgersWithInvalidDescription() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$ confirmatur confirmavit conflixisse conformavit congressus congue coniuncta coniunctione conquirendae conquisitis conscientia conscientiam consectetuer consectetur consecutionem consecutus consedit consentaneum consentientis consentinis consequamur c$", -10000000000000d, "careret caret caritatem carum causa causae causam i", "Te")).run();
//    }

    //T1-66
 //   @Test
//    public void testAddAndDeleteLedgersWithValidDescription() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "", -10000000000000d, "Te", "Te")).run();
//    }

    //T1-67
//    @Test
//    public void testAddAndDeleteLedgersWithEmptyCategory() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "Te", "")).run();
//    }

    //T1-68
//    @Test
//    public void testAddAndDeleteLedgersWithEmptyAmount() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", Double.NaN, "Te", "Te")).run();
//    }

    //T1-69
//    @Test
//    public void testAddAndDeleteLedgersWithInvalidAmountChar() throws Exception {
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "Te", "Te")).run();
//    }



    private class ValidLedgerHandler extends WindowHandler {
        private String date;
        private String Payee;
        private String description;
        private Boolean rec;
        private String category;
        private double amount;
        private String account;

        public ValidLedgerHandler(String date, String Payee, Boolean _rec, String description, String category, double amount, String _account) {
            this.date = date;
            this.Payee = Payee;
            this.description = description;
            this.rec = _rec;
            this.amount = amount;
            this.category = category;
            this.account = _account;
        }

        public Trigger process(Window window) throws Exception {
            categoriesTable = window.getTable();


            TextBox dateTextBox = window.getInputTextBox("DATE_TEXT_FIELD");
            TextBox payeeTextBox = window.getInputTextBox("PAYEE_TEXT_FIELD");
            TextBox descriptionTextBox = window.getInputTextBox("DESCRIPTION_TEXT_FIELD");
            CheckBox recCheckBox = window.getCheckBox();
            ComboBox categoryComboBox = window.getComboBox("CATEGORY_COMBO_BOX");
            TextBox amountTextBox = window.getInputTextBox("AMOUNT_TEXT_FIELD");
            ComboBox accountCombo = window.getComboBox("ACCOUNTS_COMBO_BOX");


            dateTextBox.setText(date);
            payeeTextBox.setText(Payee);
            descriptionTextBox.setText(description);
            if(rec){
                recCheckBox.select();
            }else{
                recCheckBox.unselect();
            }
            int beforeAdding = categoriesTable.getRowCount();

            amountTextBox.setText(String.valueOf(amount));
            categoryComboBox.select(category);
            accountCombo.select(account);
            System.out.println("Line 347, before adding transaction");
            window.getButton("Add Transaction").click();
            System.out.println("Line 348, after adding transaction");

            int afterAdding = categoriesTable.getRowCount();
            if(beforeAdding < afterAdding){
                assert true;
            }else{
                assert false;
            }

            ret =window.getButton("Close").triggerClick();
            return ret;
        }
    }
}
