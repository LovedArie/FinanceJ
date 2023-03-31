package ca.etsmtl.log240.financej;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.*;
import org.uispec4j.interception.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


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


    @Test
    public void test() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", 200, "te", "Te")).run();
    }

    //T1-61
    @Test
    public void testAddAndDeleteLedgersWithValidDate() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000/01/31", "$", 1, "te", "Te")).run();
    }

    //T1-62 //TODO : Fix this test with amount
    @Test
    public void testAddAndDeleteLedgersWithInvalidAmount() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "te", "Te")).run();
    }

    //T1-63
    @Test
    public void testAddAndDeleteLedgersWithValidAmount() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "careret caret caritatem carum causa causae causam i", "Te")).run();
    }

    //T1-64
    @Test
    public void testAddAndDeleteLedgersWithInvalidPayee() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "careret caret caritatem carum causa causae causam i%", "Te")).run();
    }

    //T1-65
    @Test
    public void testAddAndDeleteLedgersWithInvalidDescription() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$ confirmatur confirmavit conflixisse conformavit congressus congue coniuncta coniunctione conquirendae conquisitis conscientia conscientiam consectetuer consectetur consecutionem consecutus consedit consentaneum consentientis consentinis consequamur c$", -10000000000000d, "careret caret caritatem carum causa causae causam i", "Te")).run();
    }

    //T1-66
    @Test
    public void testAddAndDeleteLedgersWithValidDescription() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "", -10000000000000d, "Te", "Te")).run();
    }

    //T1-67
    @Test
    public void testAddAndDeleteLedgersWithEmptyCategory() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "Te", "")).run();
    }

    //T1-68
    @Test
    public void testAddAndDeleteLedgersWithEmptyAmount() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", Double.NaN, "Te", "Te")).run();
    }

    //T1-69
    @Test
    public void testAddAndDeleteLedgersWithInvalidAmountChar() throws Exception {
        WindowInterceptor.init(ledgerButton.triggerClick()).process(new ValidLedgerHandler("2000-01-31", "$", -10000000000000d, "Te", "Te")).run();
    }

    private class ValidLedgerHandler extends WindowHandler {
        private String date;
        private String description;
        private double amount;
        private String Payee;
        private String category;

        public ValidLedgerHandler(String date, String description, double amount, String Payee, String category) {
            this.date = date;
            this.description = description;
            this.amount = amount;
            this.Payee = Payee;
            this.category = category;
        }

        public Trigger process(Window window) throws Exception {
            TextBox dateTextBox = window.getInputTextBox("DATE_TEXT_FIELD");
            TextBox payeeTextBox = window.getInputTextBox("PAYEE_TEXT_FIELD");
            TextBox descriptionTextBox = window.getInputTextBox("DESCRIPTION_TEXT_FIELD");
            TextBox amountTextBox = window.getInputTextBox("AMOUNT_TEXT_FIELD");
            ComboBox categoryComboBox = window.getComboBox("CATEGORY_COMBO_BOX");


            dateTextBox.setText(date);
            payeeTextBox.setText(Payee);
            descriptionTextBox.setText(description);
            amountTextBox.setText(String.valueOf(amount));
            categoryComboBox.select(category);
            window.getButton("Add Transaction").click();

            assertEquals(1,categoriesTable.getRowCount());

            ret =window.getButton("Close").triggerClick();
            return ret;
        }
    }
}
