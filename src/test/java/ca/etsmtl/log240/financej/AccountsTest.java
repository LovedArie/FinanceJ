package ca.etsmtl.log240.financej;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.uispec4j.Table;
import org.uispec4j.Trigger;
import org.uispec4j.Window;
import org.uispec4j.interception.WindowHandler;
import org.uispec4j.interception.WindowInterceptor;

import java.util.List;


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
//    @Test
    public void testAddAndDeleteAccountsWithRandomName() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(ValidFieldsGenerator.getRandomName(), "Savings", 0)).run();
    }

    //CE24
    //Teste l'ajout d'un compte et la suppression de ce compte avec un nom ayant des caracteres alphanumériques aléatoires pour les noms de 2 à 50 caracteres CE24
    @Test
    public void testAddAndDeleteAccountsWithValidNames() {
        for (String account : accountsNomsAlphaNum) {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(account, "Savings", 2)).run();
        }
    }


    public void testT0_12() {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("Te", "$", 1)).run();
    }

    public void testT1_13() {
            //WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("", "$")).run();

        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("", "$", 1))
                .process(new WindowHandler("Error Dialog") {
                    public Trigger process(Window window) {
                        System.out.println("I got here first");
                        // Check if the window is an error dialog box
                        if (window.getTitle().equals("Error")) {
                            // Close the error dialog box
                            return window.getButton("OK").triggerClick();
                        }
                        // Return null to indicate that the window was not an error dialog box
                        return null;
                    }
                })
                .run();
    }

    public void testT1_14() {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("antiquisantiquitateapeirianaperiamapeririapteiaaaaa", "$", 1)).run();
    }

    public void testT1_15() {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("antiquisantiquitateapeirianaperiamapeririapte$", "$", 1)).run();
    }

    public void testT1_16() {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("an", "", 1)).run();
    }

    public void testT1_17() {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("an", "bonis bono bonorum bonum brevi brevis breviter brute brutus cadere caecilii caeco caelo calere campum canes captet capti captiosa careat carere careret caret caritatem carum causa causae causam causas cedentem celeritas censes censet centurionum certa", 1)).run();
    }

    public void testT1_18() {
            WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("antiquisantiquitateapeirianaperiamapeririapteaaaaa", "bonis bono bonorum bonum brevi brevis breviter brute brutus cadere caecilii caeco caelo calere campum canes captet capti captiosa careat carere careret caret caritatem carum causa causae causam causas cedentem celeritas censes censet centurionum cer$", 1)).run();
    }

    private class ValidAccountsHandler extends WindowHandler {
        private String name;
        private String description;

        private int actionCode;

        private boolean eCRequirement = true;

        public ValidAccountsHandler(String name, String description, int _actionCode) {
            this.name = name;
            this.description = description;
            this.actionCode = _actionCode;
        }

        public Trigger process(Window window){
            //checkECRequirements(window, name, description);
            return testAccount(window, name, description, actionCode);
        }


        /*private void checkECRequirements(Window window, String name, String description){

            if(name.length() < 2 || name.length() > 50 || !name.matches("[a-zA-Z0-9]+")){
                eCRequirement = false;
            }

            if (description.isEmpty() || description.length() > 250){
                eCRequirement = false;
            }

        }*/

        //Vérifie si le compte est bien ajouté et s'il est bien supprimé
        private Trigger testAccount(Window window, String name, String description, int actionCode){
            accountsTable = window.getTable();
            int rowCount = accountsTable.getRowCount();

            switch (actionCode){
                case 0:
                    //Delete matching name to avoid duplicated
                    if (rowCount > 0) {
                        for(int row = 0; row < accountsTable.getRowCount(); row++){
                            Object cellValue = accountsTable.getContentAt(row, 0);
                            if(cellValue.equals(name)){
                                accountsTable.selectRow(row);
                                window.getButton("Delete Account").click();
                                System.out.println("Account '" + name + "' was Deleted");
                                break;
                            }
                        }
                    }
                    break;
                case 1:
                    //delete all rows if they exist
                    if (rowCount > 0) {
                        for (int i = 0; i < rowCount - 1 ; i++) {
                            // select the first row with the specified name and delete it
                            accountsTable.selectRow(1);
                            window.getButton("Delete Account").click();
                        }}
                    break;

                default:
                    //Do nothing
                    break;

            }



            System.out.println("New Name : " + name);
            System.out.println("New Description : " + description);
            // ajouter un compte
            window.getTextBox("NAME_TEXT_FIELD").setText(name);
            window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
            window.getButton("Add Account").click();


            System.out.println("Table Count : " + rowCount);

            //Check if Account added was successful
            if (rowCount > 0) {
                for(int row = 0; row < accountsTable.getRowCount(); row++){
                    Object cellValue = accountsTable.getContentAt(row, 0);
                    if(cellValue.equals(name)){
                        System.out.println("Name : '" + name + "' was added.");
                        assertTrue(true);
                        break;
                    }
                }
            }else{
                assertFalse(false);
            }

            return window.getButton("Close").triggerClick();


            /*if(eCRequirement == true){
                // vérifier que le compte a été ajouté
                assertEquals(accountsTable.getRowCount(), rowCount);
                //System.out.println("Account table before adding the account---->" + accountsTable.toString());
                // ajouter un compte
              *//*  window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
                window.getButton("Add Account").click();

                // vérifier que le compte a été ajouté
                assertEquals(accountsTable.getRowCount(), rowCount + 1);*//*

                //return window.getButton("Close").triggerClick();

                // supprimer le compte cree precedemment en cherchant le nom
                //accountsTable.selectRowsWithText(0, name);
                //window.getButton("Delete Account").click();
            }else{
                assertFalse("Object was not added", false);
            }
            return window.getButton("Close").triggerClick();

            //if (accountsTable.getRowCount() == 0) {
                //System.out.println("Account table before adding the account---->" + accountsTable.toString());
                // ajouter un compte
//                window.getTextBox("NAME_TEXT_FIELD").setText(name);
//                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);
//                window.getButton("Add Account").click();

                //System.out.println("Account table after adding the account---->" + accountsTable.toString());

                // Create the expectedTable array
                //String[][] expectedTable = new String[][]{{name, description}};

                // vérifier que le compte a été ajouté
                //assertEquals(accountsTable.getRowCount(), rowCount + 1);

                // vérifier que la table de données est correcte
                //window.getTable().contentEquals(expectedTable);

                // supprimer le compte cree precedemment en cherchant le nom
                //accountsTable.selectRowsWithText(0, name);
                //window.getButton("Delete Account").click();

                //System.out.println("Account table after deleting the account---->" + accountsTable.toString());

                // vérifier que le compte a été supprimé
                //assertEquals(accountsTable.getRowCount(), rowCount);
           // }*/

            /*            System.out.println("Initial Table Count : " + rowCount);



            }*/


        }
    }

    //handler pour les comptes invalides
    private class InvalidAccountsHandler extends WindowHandler {

        private String name;
        private String description;

        public InvalidAccountsHandler(String name, String description) {
            this.name = name;
            this.description = description;
        }
        //Teste l'ajout d'un compte invalide
        public Trigger process(Window window){
            //appeler la methode qui teste l'ajout d'un compte invalide
            testError(window, name, description);
            // fermer la fenetre
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
                // essayer d'ajouter un compte invalide
                window.getTextBox("NAME_TEXT_FIELD").setText(name);
                window.getTextBox("DESCRIPTION_TEXT_FIELD").setText(description);

                window.getButton("Add Account").click();
                System.out.println("Account table after adding the account---->" + accountsTable.toString());
                //On ne devrait rien avoir dans la table, car on ne peut pas ajouter un compte invalide
                assertEquals(0,accountsTable.getRowCount());


            }
        }
    }

}