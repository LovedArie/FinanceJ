package ca.etsmtl.log240.financej;
import org.junit.After;
import org.junit.Before;
import org.uispec4j.*;
import org.uispec4j.interception.*;

public class AccountInvalideTest extends FinancejAbstractTest {
    private Table accountsTable;
    private Trigger ret;


    protected void setUp() throws Exception {
        super.setUp();
    }


    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    public void test() throws Exception {
        WindowInterceptor.init(loginButton.triggerClick()).process(new TestInValideAccountWindow()).run();
        WindowInterceptor.init(loginButton.triggerClick()).process(new TestInValideAccountWindow()).run();

    }

    public void test2() throws Exception {
        WindowInterceptor.init(loginButton.triggerClick()).process(new TestInValideAccountWindow()).run();
        WindowInterceptor.init(loginButton.triggerClick()).process(new TestInValideAccountWindow()).run();

    }




    public class TestInValideAccountWindow extends WindowHandler {


        //Les tests sont effectués dans cette méthode
        public Trigger process(Window window) throws Exception {
            exitButton = window.getButton("Exit");
            accountsButton = window.getButton("Accounts");

            //TESTE ACCOUNTS NAMES INVALIDES

            //Teste l'ajout d'un compte avec un nom vide
            WindowInterceptor.init(accountsButton.triggerClick()).process
                    (new InvalidAccountsAssertions("LLLL","Savings")).run();

            //Teste l'ajout d'un compte quand le nom du compte dépasse 50 caractères

            //Teste l'ajout d'un compte avec un nom qui contient des caractères spéciaux

            //TESTE ACCOUNTS DESCRIPTIONS INVALIDES

//            ret = exitButton.triggerClick();
//            return ret;
            return window.getButton("Cancel").triggerClick();
        }

    }



    private class InvalidAccountsAssertions extends WindowHandler {
        private String name;
        private String description;

        public InvalidAccountsAssertions(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Trigger process(Window window) throws Exception {
            testAccount(window, name, description);
            ret = window.getButton("Delete Account").triggerClick();
            return ret;
        }

        //Vérifie si le compte est bien ajouté et s'il est bien supprimé
        private void testAccount(Window window, String name, String description) throws Exception {

            accountsTable = window.getTable();
            int rowCount = accountsTable.getRowCount();

            // delete all rows if they exist
            if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) {
                    // select the first row with the specified name and delete it
                    accountsTable.selectRow(i);
                    window.getButton("Delete Account").click();
                }
                rowCount = accountsTable.getRowCount();
            }

            if (accountsTable.getRowCount() == 0) {
                System.out.println("Account table before adding the account---->" + accountsTable.toString());
                // ajouter un compte
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                window.getButton("Add Account").click();

                System.out.println("Account table after adding the account---->" + accountsTable.toString());

                // vérifier que la table de données est vide
//                window.getTable().isEmpty();



                System.out.println("Account table after deleting the account---->" + accountsTable.toString());

            }
        }
    }
}
