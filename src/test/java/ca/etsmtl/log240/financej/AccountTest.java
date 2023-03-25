package ca.etsmtl.log240.financej;
import org.uispec4j.*;
import org.uispec4j.interception.*;
import org.junit.Test;
public class AccountTest extends FinancejAbstractTest {
    private Table accountsTable;
    private Trigger ret;
    protected Button accountsButton;
    public void testAddingAndDeletingAccount() throws Exception {
        /* Voici comment traiter une fenêtre modale avec uispec4j.
         * Voir "Intercepting windows and dialogs" dans la documentation en ligne.
         */
        WindowInterceptor.init(loginButton.triggerClick()).process(new WindowHandler() {
            public Trigger process(Window window) {
                exitButton = window.getButton("Exit");
                accountsButton = window.getButton("Accounts");
                WindowInterceptor.init(accountsButton.triggerClick()).process(new WindowHandler() {
                    @Override
                    public Trigger process(Window window) throws Exception {
                        accountsTable = window.getTable();
                        int initialRowCount = accountsTable.getRowCount();
    // ajouter un compte
                        window.getTextBox("NAME_TEXT_FIELD").setText("Jo Bleault");
                        window.getTextBox("DESCRIPTION_TEXT_FIELD").setText("Savings");
                        window.getButton("Add Account").click();
                        assertEquals(accountsTable.getRowCount(), initialRowCount+1);
    // supprimer le compte cree precedemment en cherchant le nom
                        accountsTable.selectRowsWithText(0, "Jo Bleault");
                        window.getButton("Delete Account").click();
                        assertEquals(accountsTable.getRowCount(), initialRowCount);
    // retourner un "trigger" qui ferme la fenêtre modale
                        ret = window.getButton("Close").triggerClick();
                        return window.getButton("Close").triggerClick();
                    }
                }).run();
                return ret;
            }
        }).run();
    }
    public void testAddingEmptyAccount() throws Exception {
        setUp();
        Window window = getMainWindow();
        System.out.println(window.getTitle());
        WindowInterceptor.init(accountsButton.triggerClick()).process(new WindowHandler() {
            @Override
            public Trigger process(Window window) throws Exception {
                accountsTable = window.getTable();
                int initialRowCount = accountsTable.getRowCount();
                // ajouter un compte
                window.getTextBox("NAME_TEXT_FIELD").setText("");
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText("Savings");
                window.getButton("Add Account").click();
                assertEquals(accountsTable.getRowCount(), initialRowCount);
                // retourner un "trigger" qui ferme la fenêtre modale
                ret = window.getButton("Close").triggerClick();
                return window.getButton("Close").triggerClick();
            }
        }).run();
    }
    protected void tearDown() throws Exception {
        exitButton.click();
        super.tearDown();
    }
}