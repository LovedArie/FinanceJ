package ca.etsmtl.log240.financej;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.*;
import org.uispec4j.interception.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


//Test account avec des entrées valides
public class AccountsTest extends FinancejAbstractTest {
    private Table accountsTable;
    private Trigger ret;

    private List<String> accountsNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
    private List<String> descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();

    @Before
    protected void setUp() throws Exception {
        super.setUp();
    }
    @After
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    @AfterClass
    public void tearDownClass() throws Exception {
        //closing connection to database
        exitButton.click();
    }

    //CE24
    //Test pour ajouter un compte avec un nom valide
    @Test
    public void testAddAndDeleteAccountsWithRandomName() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(ValidFieldsGenerator.getRandomName(),"Savings")).run();
    }

    //CE24
    //Teste l'ajout d'un compte et la suppression de ce compte avec un nom ayant des caracteres alphanumériques aléatoires pour les noms de 2 à 50 caracteres CE24
    @Test
    public void testAddAndDeleteAccountsWithValidNames() throws Exception {
        for (String account : accountsNomsAlphaNum) {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(account,"Savings")).run();
        }

    }



    //Teste l'ajout d'un compte et la suppression avec une description vide
    @Test
    public void testAddAndDeleteAccountsWithEmptyDescription() throws Exception {
        Random random = new Random();
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(accountsNomsAlphaNum.get(random.nextInt(49)),"")).run();
    }

    //Teste l'ajout d'un compte et la suppression avec une description de 1 à 250 caracteres
    @Test
    public void testAddAndDeleteAccountsWithValidDescriptions()throws Exception {

        Random random = new Random();
        for (String desc : descriptionsValides) {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(accountsNomsAlphaNum.get(random.nextInt(49)),desc)).run();
        }
    }

    @Test
    public void testAddAccount() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("Te","$AAAAA")).run();
    }



    //Teste l'ajout d'un compte et la suppression avec une description de 251 caracteres

//    @Test
//    public void testAddAndDeleteAccountsWithBlankName() throws Exception {
//        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("","$")).run();
//
//    }
//
//
//    @Test
//    public void testAddAndDeleteAccountsWithInvalidName() throws Exception {
//        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("Aantiquis antiquitate apeirian aperiam aperiri aptei","$")).run();
//    }

    private String charGenerator(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        return sb.toString();
    }


    //Teste l'ajout d'un compte et la suppression avec un nom de 51 caracteres

    //Teste l'ajout d'un compte et la suppression avec un nom de 1 caractere

    //Teste l'ajout d'un compte et la suppression avec un nom vide







private class ValidAccountsHandler extends WindowHandler {
    private String name;
    private String description;

    public ValidAccountsHandler(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Trigger process(Window window) throws Exception {
        testAccount(window, name, description);
        ret = window.getButton("Close").triggerClick();
        return ret;
    }

    //Vérifie si le compte est bien ajouté et s'il est bien supprimé
    private void testAccount(Window window, String name, String description) throws Exception {
        accountsNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();;
        descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();

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

            // Create the expectedTable array
            String[][] expectedTable = new String[][]{{name, description}};

            // vérifier que le compte a été ajouté
            assertEquals(accountsTable.getRowCount(), rowCount + 1);

            // vérifier que la table de données est correcte
            window.getTable().contentEquals(expectedTable);

            // supprimer le compte cree precedemment en cherchant le nom
            accountsTable.selectRowsWithText(0, name);
            window.getButton("Delete Account").click();

            System.out.println("Account table after deleting the account---->" + accountsTable.toString());

            // vérifier que le compte a été supprimé
            assertEquals(accountsTable.getRowCount(), rowCount);
        }
    }
}






}