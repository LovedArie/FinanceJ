package ca.etsmtl.log240.financej;
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

    private List<String> accountsNomsAlphaNum= new ValidAccountsHandler().generateValidAccountNames();
    private List<String> descriptionsValides = new ValidAccountsHandler().generateValidDescriptions();

    @Before
    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    //CE24
    //Test pour ajouter un compte avec un nom valide
    @Test
    public void testAddAndDeleteAccountsWithRandomName() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler(new ValidAccountsHandler().getRandomName(),"Savings")).run();
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

    @Test
    public void testAddAndDeleteAccountsWithBlankName() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("","$")).run();

    }


    @Test
    public void testAddAndDeleteAccountsWithInvalidName() throws Exception {
        WindowInterceptor.init(accountsButton.triggerClick()).process(new ValidAccountsHandler("Aantiquis antiquitate apeirian aperiam aperiri aptei","$")).run();
    }

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

    public ValidAccountsHandler() {

    }

    public Trigger process(Window window) throws Exception {
        testAccount(window, name, description);
        ret = window.getButton("Close").triggerClick();
        return ret;
    }

    //Vérifie si le compte est bien ajouté et s'il est bien supprimé
    private void testAccount(Window window, String name, String description) throws Exception {
        accountsNomsAlphaNum= generateValidAccountNames();;
        descriptionsValides = generateValidDescriptions();

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

    public String getRandomName() {
        String[] firstNames = {"Emma", "Liam", "Olivia", "Noah", "Ava", "Ethan", "Sophia", "Lucas", "Mia", "Mason", "Isabella", "Logan", "Charlotte", "Elijah", "Amelia", "Caleb", "Harper", "Aiden", "Abigail", "Gabriel", "Emily", "Benjamin", "Evelyn", "Owen", "Elizabeth", "William", "Avery", "Michael", "Sofia", "Alexander", "Ella", "Daniel", "Madison", "Matthew", "Scarlett", "Jackson", "Victoria", "Sebastian", "Grace", "Henry", "Chloe", "Samuel", "Aria", "David", "Lily", "Wyatt", "Zoe"};
        String[] lastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "King", "Wright", "Scott", "Green", "Baker", "Adams", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Sullivan", "Russell", "Ortiz", "Jensen", "Kim"};
        Random random = new Random();
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        return firstName + " " + lastName;
    }

    //Create a list of accounts names between 2 and 50 characters with alphanumeric characters
    public List<String> generateValidAccountNames() {
        List<String> names = new ArrayList<>();

        for (int length = 2; length <= 50; length++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                //Append random alphanumeric character to name of account
                if (ThreadLocalRandom.current().nextBoolean()) {
                    sb.append((char) ThreadLocalRandom.current().nextInt('a', 'z' + 1));
                } else {
                    sb.append((char) ThreadLocalRandom.current().nextInt('0', '9' + 1));
                }
            }
            names.add(sb.toString());
        }

//            System.out.println("List of valid account names---->" + names.toString());
        return names;
    }


    //Create a list of descriptions between 1 and 250 characters with any printable characters
    public List<String> generateValidDescriptions() {
        List<String> descriptions = new ArrayList<>();

        for (int length = 1; length <= 250; length++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                //Append random printable character for description of account
                char randomChar = (char) ThreadLocalRandom.current().nextInt(32, 127); // limit to ASCII printable characters
                sb.append(randomChar);
            }
            descriptions.add(sb.toString());
        }

//            System.out.println("List of valid account descriptions---->" + descriptions.toString());
        return descriptions;
    }
}






}