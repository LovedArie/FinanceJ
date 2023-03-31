package ca.etsmtl.log240.financej;
import org.uispec4j.*;
import org.uispec4j.interception.*;
import org.uispec4j.Button;
import org.uispec4j.Table; import org.uispec4j.UISpec4J;
import org.uispec4j.UISpecTestCase; import org.uispec4j.Window;
import org.uispec4j.interception.MainClassAdapter;

public abstract class FinancejAbstractTest extends UISpecTestCase {
    protected Table ledgerTable;
    protected Button ledgerButton;
    protected Button categoriesButton;

    protected Button accountsButton;
    protected Button reportsButton;
    protected Button exitButton;
    protected Table accountsTable;
    protected Table categoriesTable;


    static {
        UISpec4J.init();
    }

    protected void setUp() throws Exception {
        super.setUp();
        setAdapter(new MainClassAdapter(FinanceJ.class));
        Window window = getMainWindow();
        accountsButton = window.getButton("Accounts");
        categoriesButton = window.getButton("Categories");
        exitButton = window.getButton("Exit");
        UISpec4J.setWindowInterceptionTimeLimit(100);
    }

    // Handler for the accounts window
    private class AccountsHandler extends WindowHandler {
        public Trigger process(Window window) throws Exception {
            exitButton = window.getButton("Exit");
            accountsButton = window.getButton("Accounts");
            System.out.println("AccountsHandler");
            return accountsButton.triggerClick();
        }
    }

    private class CategoriesHandler extends WindowHandler {
        @Override
        public Trigger process(Window window) throws Exception {
            exitButton = window.getButton("Exit");
            categoriesButton = window.getButton("Categories");
            System.out.println("CategoriesHandler");
            return categoriesButton.triggerClick();
        }
    }

    //closing connection to database
    protected void tearDown() throws Exception {
//        exitButton.click();
        super.tearDown();
    }
}