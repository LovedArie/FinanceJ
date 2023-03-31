package ca.etsmtl.log240.financej;

import org.junit.*;
import org.uispec4j.Table;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;
import java.util.List;
import java.util.Random;


public class AccountsTest extends FinancejAbstractTest {
    private Table accountsTable;
    private Trigger ret;

    private List<String> accountsNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
    private List<String> descriptionsValides = ValidFieldsGenerator.generateValidDescriptions();

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
//        exitButton.click();
    }

    @AfterClass
    public void tearDownAll() throws Exception {
        exitButton.click();
    }

    //CE24
    //Test pour ajouter un compte avec un nom valide
    @Test
    public void testAddAndDeleteAccountsWithRandomName() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(ValidFieldsGenerator.getRandomName(), "Savings")).run();
    }

    //CE24
    //Teste l'ajout d'un compte et la suppression de ce compte avec un nom ayant des caracteres alphanumériques aléatoires pour les noms de 2 à 50 caracteres CE24
    @Test
    public void testAddAndDeleteAccountsWithValidNames() {
        for (String account : accountsNomsAlphaNum) {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(account, "Savings")).run();
        }

    }



    //Teste l'ajout d'un compte et la suppression avec une description de 1 à 250 caracteres
    @Test
    public void testAddAndDeleteAccountsWithValidDescriptions(){

        Random random = new Random();
        for (String desc : descriptionsValides) {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(accountsNomsAlphaNum.get(random.nextInt(49)), desc)).run();
        }
    }

    //TO-12
    //Teste l'ajout d'un compte et la suppression avec un nom et une description valide
    @Test
    public void testT0_12(){
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("Te", "$AAAAA")).run();
    }

    //T1-13
    //Teste l'ajout d'un compte avec un espace vide pour le nom
    @Test
    public void testT1_13() {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new InvalidAccountsHandler(" ","$")).run();
    }

    //T1-14
    //Teste l'ajout d'un compte avec un nom invalide, car le nom est trop long (51 caracteres)
    @Test
    public void testT1_14() {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new InvalidAccountsHandler("antiquisantiquitateapeirianaperiamapeririapteiaaaaa","$")).run();
    }



    //T1-15 Teste l'ajout d'un compte avec un nom invalide, car il y a un caractere special
    @Test
    public void testT1_15() {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new InvalidAccountsHandler("antiquisantiquitateapeirianaperiamapeririapte$","$")).run();
    }


    //T1-16
    //Teste l'ajout d'un compte et la suppression avec une description vide
    @Test
    public void testT1_16(){
        WindowInterceptor.init(accountsButton.triggerClick()).process(new InvalidAccountsHandler("An", "")).run();
    }

    //T1-17
    //Teste l'ajout d'un compte avec une description trop longue (251 caracteres)
    @Test
    public void testT1_17(){
        String descTooLong = "bonis bono bonorum bonum brevi brevis breviter brute " +
                "brutus cadere caecilii caeco caelo calere campum canes captet capti " +
                "captiosa careat carere careret caret caritatem carum causa causae causam " +
                "causas cedentem celeritas censes censet centurionum certa";
        WindowInterceptor.init(accountsButton.triggerClick()).process(new InvalidAccountsHandler("An", descTooLong)).run();
    }


    //T0-18 Teste l'ajout d'un compte avec une description de taille maximale et un nom de taille maximale
    @Test
    public void testT0_18(){
        String desc = "bonis bono bonorum bonum brevi brevis breviter brute brutus cadere caecilii caeco caelo calere campum canes captet capti captiosa careat carere careret caret caritatem carum causa causae causam causas cedentem celeritas censes censet centurionum cer$";
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("antiquisantiquitateapeirianaperiamapeririapteaaaaa", desc)).run();
    }

    private class ValidAccountsHandler extends WindowHandler {
        private String name;
        private String description;

        public ValidAccountsHandler(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public Trigger process(Window window){
            testAccount(window, name, description);
            ret = window.getButton("Close").triggerClick();
            return ret;
        }

        //Vérifie si le compte est bien ajouté et s'il est bien supprimé
        private void testAccount(Window window, String name, String description){
            accountsNomsAlphaNum = ValidFieldsGenerator.generateValidAccountNames();
            ;
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

    private class InvalidAccountsHandler extends WindowHandler {
        private String name;
        private String description;

        public InvalidAccountsHandler(String name, String description) {
            this.name = name;
            this.description = description;
        }
        public Trigger process(Window window){
            testError(window, name, description);
            ret = window.getButton("Close").triggerClick();
            return ret;
        }
        private void testError(Window window, String name, String description){
            accountsTable = window.getTable();
            int rowCount = accountsTable.getRowCount();

            // delete all rows if they exist
            if (rowCount > 0) {
                for (int i = 0; i < rowCount; i++) {
                    // select the first row with the specified name and delete it
                    accountsTable.selectRow(i);
                    window.getButton("Delete Account").triggerClick();
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
                //On ne devrait rien avoir dans la table, car on a pas ajouter le compte
                assertEquals(0,accountsTable.getRowCount());


            }
        }
    }

}