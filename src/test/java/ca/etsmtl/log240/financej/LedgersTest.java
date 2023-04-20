package ca.etsmtl.log240.financej;

import org.junit.AfterClass;
import org.uispec4j.*;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;


public class LedgersTest extends FinancejAbstractTest {
    private Table ledgerTable;

//    private List<Date> datesValides = ValidFieldsGenerator.getValidRandomDate();
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

    public void creatingDummyObjectforOtherModule(String name){
        WindowInterceptor.init(accountsButton.triggerClick()).process(new WindowHandler() {
            @Override
            public Trigger process(Window window) throws Exception {

                Table accountTable = window.getTable();
                int rowCount = accountTable.getRowCount();

                if (accountTable.getRowCount() > 0) {
                    System.out.println("Account row Count : " + accountTable.getRowCount());

//                    System.out.println("This is a value : " + accountTable.getContentAt(3,0));
//                    Object cellValue = accountTable.getContentAt(0, 0);
                    for(int row = 0; row < rowCount; row ++){
                        Object cellValue = accountTable.getContentAt(row, 0);
                        System.out.println("Testing : " + row + "\nCell Name : " + cellValue + "\nInput Name : " + name);
                        if(cellValue.equals(name)){
                            accountTable.selectRow(row);
                            window.getButton("Delete Account").click();
                            System.out.println("Account '" + name + "' was Deleted");
                            break;
                        }
                    }
                }
                System.out.println("Before Adding");
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(name);
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
                        if(cellValue.equals(name)){
                            categoryTable.selectRow(row);
                            window.getButton("Delete Category").click();
                            System.out.println("Account '" + name + "' was Deleted");
                            break;
                        }
                    }
                }

                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(name);
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
    }
    public void testT0_20(){

        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "te", false, "$", "Te", -1000000.00D, "Te")).run();

    }

    public void testT0_21(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-02-29", "consequi consequuntur consetetur consilia consilio", true, "albucius alia aliae aliam alias aliena alienae alienum alienus alii alios aliqua aliquam aliquando aliquem aliquet aliquid aliquip aliquo aliquod aliquos aliquyam aliter aliud allevatio alliciat allicit altera alteram alterum amaret amarissimam amet%", "Te", 100000000000.00, "Te")).run();

    }

    public void testT0_22(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-02-29", "consequi consequuntur consetetur consilia consilio", true, "albucius alia aliae aliam alias aliena alienae alienum alienus alii alios aliqua aliquam aliquando aliquem aliquet aliquid aliquip aliquo aliquod aliquos aliquyam aliter aliud allevatio alliciat allicit altera alteram alterum amaret amarissimam amet%", "Te", 100000000000.00, "Te")).run();
    }

    public void testT0_23(){

        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-02-29", "Te", false, "A", "Te", 3400000000.00, "Te")).run();

    }
//
    public void testT0_24(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-02-01", "Te", false, "A", "Te", 3400000000.0000, "Te")).run();
    }
//
    public void testT0_25(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2001-02-28", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_26(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-03-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_27(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-03-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_28(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-04-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_29(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-04-30", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_30(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-05-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_31(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-05-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_32(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-06-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_33(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-06-30", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_34(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-07-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_35(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-07-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_36(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-08-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_37(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-08-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_38(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-09-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_39(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-09-30", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_40(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-10-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_41(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-10-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_42(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-11-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_43(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-11-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_44(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-12-01", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT0_45(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-12-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_46(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_47(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-02-30", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_48(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2001-02-29", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_49(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-03-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_50(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-04-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_51(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-05-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_52(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-06-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_53(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-07-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_54(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-08-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_55(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-09-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_56(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-10-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_57(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-11-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_58(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-12-32", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_59(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_60(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-Janvier-31", "Te", false, "A", "Te", 1, "Te")).run();
    }

    public void testT1_61(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000/01/31", "Te", false, "A", "Te", 1, "Te")).run();
    }
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

//    public void testT1_70(){
//        creatingDummyObjectforOtherModule("Te");
//        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "Te", false, "A", "Te", 100000000000c, "Te")).run();
//    }

    public void testT1_71(){
        creatingDummyObjectforOtherModule("Te");
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "Te", false, "$", "Te", -1000000000000.00, "Te")).run();
    }



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
            ledgerTable = window.getTable();

            if (ledgerTable.getRowCount() > 0) {
                for (int i = 0; i < ledgerTable.getRowCount() - 1 ; i++) {
                    // select the first row with the specified name and delete it
                    ledgerTable.selectRow(1);
                    window.getButton("Delete Transaction").click();
                }}

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
            System.out.println("Line 345, did we get here?");
            int beforeAdding = ledgerTable.getRowCount();

            amountTextBox.setText(String.valueOf(amount));
            categoryComboBox.select(category);
            accountCombo.select(account);
            System.out.println("Line 347, before adding transaction");
            window.getButton("Add Transaction").click();
            System.out.println("Line 348, after adding transaction");

            int afterAdding = ledgerTable.getRowCount();
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
